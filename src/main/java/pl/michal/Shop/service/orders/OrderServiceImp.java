package pl.michal.Shop.service.orders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.michal.Shop.converters.OrdersConverters;
import pl.michal.Shop.model.*;
import pl.michal.Shop.model.views.OrderResponse;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.Shop.DTOs.CreateOrderLineRequest;
import pl.michal.Shop.DTOs.CreateOrderRequest;

import pl.michal.Shop.model.views.OrderlineView;
import pl.michal.Shop.model.views.TopCommissionUserResponse;
import pl.michal.Shop.model.views.TopUserCoinsResponse;
import pl.michal.Shop.repositories.*;
import pl.michal.Shop.service.pricing.PricingService;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {


    private final
    EntityManagerFactory emf;

    private final
    OrderRepository orderRepository;

    private final
    OrderLinesRepository orderLinesRepository;

    private final
    UserRepository userRepository;

    private final
    ProductRepository productRepository;

    private final
    PurchasedProductByUserRepository purchasedProductByUserRepository;

    private final
    PricingService pricingService;

    private OrdersConverters ordersConverters;


    public OrderServiceImp(EntityManagerFactory emf, OrderRepository orderRepository, OrderLinesRepository orderLinesRepository, UserRepository userRepository, ProductRepository productRepository, PurchasedProductByUserRepository purchasedProductByUserRepository, PricingService pricingService, OrdersConverters ordersConverters) {
        this.emf = emf;
        this.orderRepository = orderRepository;
        this.orderLinesRepository = orderLinesRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.purchasedProductByUserRepository = purchasedProductByUserRepository;
        this.pricingService = pricingService;
        this.ordersConverters = ordersConverters;
    }


    @Override
    public OrderResponse getOrderResponse(Long orderId) {

        Order order = orderRepository.findById(orderId).get();
        List<OrderLine> orderLines = getOrderLinesByOrderId(orderId);

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId().toString());
        orderResponse.setUserId(order.getUser().getId().toString());
//TODO napraw- chyba zmienilez ze statycznej na normalna i dlatego jest problem
        orderResponse.setOrderlineViews(orderLines.stream()
                .map(orderLine -> ordersConverters.fromOrderLineToOrderView(orderLine)).collect(Collectors.toList()));
        Double totalSum = orderResponse.getOrderlineViews()
                .stream()
                .map(x -> Double.parseDouble(String.valueOf(x.getAmount())) * x.getPrice())
                .reduce((x1, x2) -> x1 + x2)
                .get();
//
        orderResponse.setTotalSum(totalSum);

        return orderResponse;
    }

    @Override
    public OrderlineView getOrderLineResponse(OrderLine orderLine) {
        OrderlineView orderlineView = new OrderlineView();
        orderlineView.setProductTitle(orderLine.getProductId().getTitle());
        orderlineView.setAmount(Math.toIntExact(orderLine.getQuantity()));
        orderlineView.setPrice(Double.parseDouble(String.valueOf(orderLine.getProductId().getPrice())));
        return orderlineView;
    }

    @Override

    public Order create(Long userId, CreateOrderLineRequest createOrderLineRequest) {

        CreateOrderRequest createOrderRequest = ordersConverters.fromCreateOrderLineRequestToCreateOrderRequest(createOrderLineRequest, userId);

        Order order = ordersConverters.fromOrderFromCreateRequestToOrder(createOrderRequest);
        Long orderID = orderRepository.save(order).getId();
        createOrderRequest.setOrderID(orderID);


        //    CreateOrderLineRequest createOrderLineRequest = new CreateOrderLineRequest(order.getId(), productId, quantity);
        updateOrderWithNewOrderLine(order.getId(), userId, createOrderLineRequest);


        order = orderRepository.findById(orderID).get();

        return order;

    }


    @Override
    public OrderLine updateOrderWithNewOrderLine(Long orderId, Long userID, CreateOrderLineRequest createOrderLineRequest) {
        //TODO use Spring batch


        OrderLine newOrderLine = ordersConverters.fromCreateOrderLineRequestToOrderLine(createOrderLineRequest, orderId);
        orderLinesRepository.save(newOrderLine);
        pricingService.addCoinToUser(newOrderLine);
        pricingService.addCommission(newOrderLine);


        Product updatedProduct = newOrderLine.getProductId();
        User updatedUser = newOrderLine.getOrderId().getUser();
        PurchasedProductByUser purchasedProductByUser = new PurchasedProductByUser(updatedUser, updatedProduct);

        purchasedProductByUserRepository.save(purchasedProductByUser);

        return newOrderLine;

    }

    public List<TopUserCoinsResponse> topCoinsUser() {


        List<User> users = pricingService.topCoinsUser();
        List<TopUserCoinsResponse> usersView = ordersConverters.fromTopUsersToTopUserCoinsView(users);

        return usersView;
    }

    @Override
    public List<TopCommissionUserResponse> topCommissionUser() {
        List<User> users = pricingService.topCommissionUser();
        List<TopCommissionUserResponse> usersView = ordersConverters.fromTopUsersToTopUserCommissionView(users);

        return usersView;
    }

    @Override
    public BigDecimal applicationProfits() {
        return pricingService.applicationProfits();
    }


    @Override
    public List<Order> getAll() {
        return Lists.newArrayList(orderRepository.findAll());

    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public List<Order> remove(Order order) {
        return null;
    }

    @Override
    public List<Order> removeAll() {
        return null;
    }


    private List<OrderLine> getOrderLinesByOrderId(Long orderId) {

        List<OrderLine> result;
        EntityManager em = emf.createEntityManager();
        TypedQuery<OrderLine> query = em.createQuery("select orl" +
                        " from Order ord inner join OrderLine orl" +
                        " on ord.id= orl.orderId.id" +
                        " where ord.id = " + "'" + orderId + "'"
                , OrderLine.class
        );

        result = query.getResultList();
        return result;

    }


}
