package pl.michal.Shop.converters;

import com.example.types.TopCommissionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.michal.Shop.DTOs.CreateOrderLineRequest;
import pl.michal.Shop.DTOs.CreateOrderRequest;
import pl.michal.Shop.model.Order;
import pl.michal.Shop.model.OrderLine;
import pl.michal.Shop.model.Product;
import pl.michal.Shop.model.User;
import pl.michal.Shop.model.views.OrderlineView;
import pl.michal.Shop.model.views.TopCommissionUserResponse;
import pl.michal.Shop.model.views.TopUserCoinsResponse;
import pl.michal.Shop.repositories.OrderRepository;
import pl.michal.Shop.repositories.ProductRepository;
import pl.michal.Shop.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersConverters {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public OrdersConverters() {
    }

//    public OrdersConverters(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//        this.orderRepository = orderRepository;
//    }

    public OrderLine fromCreateOrderRequestToOrderLine(CreateOrderRequest createOrderRequest) {
        Product product = productRepository.findById(createOrderRequest.getProductId()).orElseThrow(() -> new IllegalArgumentException("NO such  product ID"));
        Order order = orderRepository.findById(createOrderRequest.getOrderID()).orElseThrow(() -> new IllegalArgumentException("NO such  order ID"));


        OrderLine orderLine = new OrderLine(
                order,
                product,
                createOrderRequest.getQuantity()
        );
        return orderLine;
    }
//        CreateOrderRequest createOrderRequest=ordersConverters.fromCreateOrderLineRequestToCreateOrderRequest(createOrderLineRequest)

    public CreateOrderRequest fromCreateOrderLineRequestToCreateOrderRequest(CreateOrderLineRequest createOrderLineRequest, Long userId) {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(userId, null, createOrderLineRequest.getProductId(), createOrderLineRequest.getQuantity());

        return createOrderRequest;
    }

    public Order fromOrderFromCreateRequestToOrder(CreateOrderRequest createOrderRequest) {

        User user = userRepository
                .findById(createOrderRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("No such user ID"));

        Order order = new Order(user, LocalDateTime.now(), new ArrayList<>());

        return order;
    }

    public OrderLine fromCreateOrderLineRequestToOrderLine(CreateOrderLineRequest createOrderLineRequest, Long orderId) {
        Order order = null;
        if (orderId != null) {
            order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("NO such  order ID"));
        }

        Product product = productRepository.findById(createOrderLineRequest.getProductId()).orElseThrow(() -> new IllegalArgumentException("NO such  product ID"));


        OrderLine orderLine = new OrderLine(
                order,
                product,
                createOrderLineRequest.getQuantity()
        );

        return orderLine;
    }


    public OrderlineView fromOrderLineToOrderView(OrderLine orderLine) {
        OrderlineView orderlineView = new OrderlineView();

        orderlineView.setAmount(Math.toIntExact(orderLine.getQuantity()));
        orderlineView.setProductTitle(orderLine.getProductId().getTitle());
        orderlineView.setPrice(Double.parseDouble(String.valueOf(orderLine.getPrice())));

        return orderlineView;
    }


    public List<TopUserCoinsResponse> fromTopUsersToTopUserCoinsView(List<User> users) {

        List<TopUserCoinsResponse> topUsersCoinsViews = new ArrayList<>();

        users.stream().forEach(user -> {
            TopUserCoinsResponse topUserCoinsResponse = new TopUserCoinsResponse();
            topUserCoinsResponse.setId(String.valueOf(user.getId()));
            topUserCoinsResponse.setName(user.getFirstName());
            topUserCoinsResponse.setLastName(user.getLastName());
            topUserCoinsResponse.setTotalCoins(Double.parseDouble(String.valueOf(user.getCoins())));


            topUsersCoinsViews.add(topUserCoinsResponse);
        });

        return topUsersCoinsViews;
    }

    public List<TopCommissionUserResponse> fromTopUsersToTopUserCommissionView(List<User> users) {

        List<TopCommissionUserResponse> topUsersCommissionViews = new ArrayList<>();
        users.stream().forEach(user -> {

            TopCommissionUserResponse topCommissionUserResponse = new TopCommissionUserResponse();
            topCommissionUserResponse.setId(String.valueOf(user.getId()));
            topCommissionUserResponse.setName(user.getFirstName());
            topCommissionUserResponse.setLastName(user.getLastName());
            topCommissionUserResponse.setTotalCommission(Double.parseDouble(String.valueOf(user.getCommission())));
            topUsersCommissionViews.add(topCommissionUserResponse);
        });
        return topUsersCommissionViews;
    }
}



