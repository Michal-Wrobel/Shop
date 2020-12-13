package pl.michal.Shop.repositories;


import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.michal.Shop.DTOs.CreateOrderLineRequest;
import pl.michal.Shop.DTOs.CreateOrderRequest;
import pl.michal.Shop.DTOs.CreateProductRequest;
import pl.michal.Shop.jsonConverters.JsonConverter;
import pl.michal.Shop.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
// H2 springboot databa- zeby nie operowac na tej samej bazie co podstawowa apka- zapytaj jak skonczysz ordery

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLinesRepository orderLinesRepository;

    @Autowired
    private UserRepository userRepository;

    String FILENAME = "ProductsTest";

    @Before
    public void setUp() {
        orderLinesRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();
         categoryRepository.deleteAll();
        create1000Products();
        create10Orders();
        //   upload1000Products();
    }

    @Test
    public void dlaFormalnosci() {
        System.out.println("Test wystartowal");
    }

    public void create10Orders() {
        createPureOrders();
        createOrderLines();

    }

    private void createOrderLines() {
        List<Product> products = Lists.newArrayList(productRepository.findAll());
        List<Order> orders = Lists.newArrayList(orderRepository.findAll());
        Random rnd = new Random();

        for (int i = 0; i < products.size(); i++) {
            int orderRandomId = rnd.nextInt(orders.size());
            int quantity = rnd.nextInt(1000);
            OrderLine orderLine = new OrderLine(orders.get(orderRandomId), products.get(i), Long.valueOf(quantity));
            orderLinesRepository.save(orderLine);
        }

        //    public OrderLine(Order orderId, Product productId, Long quantity) {

    }


    private List<Order> createPureOrders() {
        Random rnd = new Random();
        List<Order> orders = new ArrayList<>();
        List<User> users = Lists.newArrayList(userRepository.findAll());
        for (int i = 0; i < users.size(); i++) {
            int clientNumber = rnd.nextInt(users.size());
            Order order = new Order(users.get(clientNumber), LocalDateTime.now(), new ArrayList<>());
            orderRepository.save(order);
            orders.add(order);
        }
        return orders;
    }


    public void create1000Products() {
        List<User> clientList = createClientsIDs();
        List<CreateProductRequest> pureProducts = createPureProducts();
        List<Category> categories = createCategories();

        saveCategories(categories);
        List<CreateProductRequest> productsRequests = createProductsRequests(clientList, pureProducts, categories);
        List<Product> fullProducts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Category category = categoryRepository.findByName(productsRequests.get(i).getCategoryName()).get();

            Product product = new Product(
                    productsRequests.get(i).getTitle()
                    , productsRequests.get(i).getDescription()
                    , productsRequests.get(i).getPrice()
                    , productsRequests.get(i).getCustomerId()
                    , category

            );
            productRepository.save(product);

            fullProducts.add(product);


        }

//        MyConverter myConverter = new MyConverter(FILENAME);
//        myConverter.toJson(fullProducts);

    }

    public void upload1000Products() {
        MyConverter myConverter = new MyConverter(FILENAME);
        List<Product> productList = myConverter.fromJson().get();
        productList.stream().forEach(
                pr -> productRepository.save(pr)
        );


    }


    private List<User> createClientsIDs() {
        User user1 = new User("Michal", "Kowalski", null, null);
        User user2 = new User("Pawel", "Bak", null, null);
        User user3 = new User("Slawomir", "Zajac", null, null);
        User user4 = new User("katarzyna", "Sikora", null, null);
        User user5 = new User("Michalina", "Sojka", null, null);

        List<User> clientList = new ArrayList<>();
        clientList.add(user1);
        clientList.add(user2);
        clientList.add(user3);
        clientList.add(user4);
        clientList.add(user5);

        clientList.stream().forEach((x -> userRepository.save(x)));

        return clientList;
    }

    private List<Category> createCategories() {

        List<Category> categories = new ArrayList();
        Category category1 = new Category("BOOKS");
        Category category2 = new Category("COSMETICS");
        Category category3 = new Category("AGD");
        Category category4 = new Category("RTV");
        Category category5 = new Category("ELECTRONICS");

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);


        return categories;
    }

    private void saveCategories(List<Category> categories) {
        categories.stream().forEach(ct -> categoryRepository.save(ct));
    }


    private List<CreateProductRequest> createProductsRequests(List<User> clientList, List<CreateProductRequest> products, List<Category> categories) {

        Random rnd = new Random();
        List<CreateProductRequest> createProductRequests = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int productNumber = rnd.nextInt(products.size());
            int clientNumber = rnd.nextInt(clientList.size());
            int categoryNumber = rnd.nextInt(categories.size());

            CreateProductRequest createProductRequest = new CreateProductRequest(
                    products.get(productNumber).getTitle()
                    , products.get(productNumber).getDescription()
                    , products.get(productNumber).getPrice()
                    , clientList.get(clientNumber).getId()
                    , categories.get(categoryNumber).getName()
            );
            createProductRequests.add(createProductRequest);
        }

//    public CreateProductRequest(String title, String description, BigDecimal price, Long customerId, String categoryName) {

        return createProductRequests;
    }


//    private List<Product> createProductsWithCustomer(List<Long> clientList, List<CreateProductRequest> products,List<Category> categories) {
//        Random rnd = new Random();
//
//        List<Product> fullProducts = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++) {
//            int productNumber = rnd.nextInt(products.size());
//            int clientNumber = rnd.nextInt(clientList.size());
//            CreateProductRequest createProductRequest = products.get(productNumber);
//            Product product = new Product(createProductRequest.getTitle()
//                    , createProductRequest.getDescription()
//                    , createProductRequest.getPrice()
//                    ,createProductRequest.getCustomerId()
//                    ,createProductRequest.getCategoryName()
//                   // , clientList.get(clientNumber)
//            );
//
//            //  productRepository.save(product);
////    public Product(String title, String description, BigDecimal price, Long userId, Category category) {
//
//            fullProducts.add(product);
//        }
    //       return fullProducts;
    //  }

    private List<CreateProductRequest> createPureProducts() {
        List<CreateProductRequest> products = new ArrayList<>();

        CreateProductRequest product1 = new CreateProductRequest("Pralka", "Biala", BigDecimal.valueOf(1000), null, null);
        CreateProductRequest product2 = new CreateProductRequest("Kuchenka", "Niezawodna", BigDecimal.valueOf(200), null, null);
        CreateProductRequest product3 = new CreateProductRequest("Slol", "Stolowy", BigDecimal.valueOf(666), null, null);
        CreateProductRequest product4 = new CreateProductRequest("Krzeslo", "Wygodne", BigDecimal.valueOf(77), null, null);
        CreateProductRequest product5 = new CreateProductRequest("Okno", "Plastikowe", BigDecimal.valueOf(55), null, null);
        CreateProductRequest product6 = new CreateProductRequest("Grzejnik", "Zeliwny", BigDecimal.valueOf(73), null, null);
        CreateProductRequest product7 = new CreateProductRequest("Doniczka", "Na kwiaty", BigDecimal.valueOf(73), null, null);
        CreateProductRequest product8 = new CreateProductRequest("Obraz", "Picasso", BigDecimal.valueOf(92), null, null);
        CreateProductRequest product9 = new CreateProductRequest("Telefon", "Flagowiec", BigDecimal.valueOf(94), null, null);
        CreateProductRequest product10 = new CreateProductRequest("Laptop", "15 calo", BigDecimal.valueOf(150), null, null);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
        return products;
    }


    private class MyConverter extends JsonConverter<List<Product>> {

        public MyConverter(String FILENAME) {
            super(FILENAME);
        }

    }

}