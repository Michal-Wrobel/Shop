package pl.michal.Shop;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.michal.Shop.DTOs.CreateOrderLineRequest;
import pl.michal.Shop.DTOs.CreateOrderRequest;
import pl.michal.Shop.model.Order;
import pl.michal.Shop.model.OrderLine;
import pl.michal.Shop.model.Product;
import pl.michal.Shop.model.User;
import pl.michal.Shop.repositories.OrderLinesRepository;
import pl.michal.Shop.repositories.OrderRepository;
import pl.michal.Shop.repositories.ProductRepository;
import pl.michal.Shop.repositories.UserRepository;
import pl.michal.Shop.service.orders.OrderService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)


public class CreateOrderTest {
    @Autowired
    EntityManagerFactory emf;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLinesRepository orderLinesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Before
    public void setUp() {

        List<Order> orders = Lists.newArrayList(orderRepository.findAll())

                .stream()
                .filter(ord -> ord.getCreated() != null)
                .filter(ord -> ord.getCreated().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

        orders.forEach(order -> orderRepository.delete(order));

        List<Order> newOrders = createManyOrders();
        addOrderLinesToExistingOrders(newOrders);
    }


    @Test
    public void dlaFormalnosci() {

    }

    public List<Order> createManyOrders() {

        List<Product> products = Lists.newArrayList(productRepository.findAll());
        List<User> users = Lists.newArrayList(userRepository.findAll());

        Long productId;
        Long userId;
        Order newOrder;
        Long quantity;
        List<Order> newOrders = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            userId = getRandomUser(users);
            productId = getRandomProduct(products);
            quantity = getRandomQuantity();
            CreateOrderLineRequest createOrderLineRequest = new CreateOrderLineRequest(productId, quantity);

            newOrder = createOneOrder(userId, createOrderLineRequest);

            newOrders.add(newOrder);
        }

        return newOrders;
    }


    public void addOrderLinesToExistingOrders(List<Order> newOrders) {
        List<Product> products = Lists.newArrayList(productRepository.findAll());

        Long productId;
        Order randomOrder;
        Long quantity;
        for (int i = 0; i < 100; i++) {


            productId = getRandomProduct(products);
            randomOrder = getRandomOrder(newOrders);

            quantity = getRandomQuantity();
            CreateOrderLineRequest createOrderLineRequest = new CreateOrderLineRequest(productId, quantity);
            orderService.updateOrderWithNewOrderLine(randomOrder.getId(), randomOrder.getUser().getId(), createOrderLineRequest);

        }


    }

    private Order getRandomOrder(List<Order> newOrders) {
        Random rnd = new Random();
        int randomUserPosition = rnd.nextInt(newOrders.size());
        return newOrders.get(randomUserPosition);

    }

    private Long getRandomQuantity() {
        Random rnd = new Random();
        return Long.valueOf(rnd.nextInt(1000));


    }

    private Long getRandomUser(List<User> users) {
        Random rnd = new Random();
        int randomUserPosition = rnd.nextInt(users.size());
        return users.get(randomUserPosition).getId();

    }

    private Long getRandomProduct(List<Product> products) {
        Random rnd = new Random();
        int randomProductsPosition = rnd.nextInt(products.size());
        return products.get(randomProductsPosition).getId();

    }

    private Order createOneOrder(Long userId, CreateOrderLineRequest createOrderLineRequest) {
        Order order = orderService.create(userId, createOrderLineRequest);
        order.setCreated(LocalDateTime.now().plus(90, ChronoUnit.DAYS));

        orderRepository.save(order);
        return order;

    }


    public List<OrderLine> getOrderLinesByOrderId(Long orderId) {


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
