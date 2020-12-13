package pl.michal.Shop.controllers;

import org.springframework.validation.annotation.Validated;
import pl.michal.Shop.DTOs.CreateOrderRequest;
import pl.michal.Shop.model.User;
import pl.michal.Shop.model.views.OrderResponse;
import pl.michal.Shop.model.views.OrderlineView;
import org.springframework.web.bind.annotation.*;
import pl.michal.Shop.DTOs.CreateOrderLineRequest;
import pl.michal.Shop.model.Order;
import pl.michal.Shop.model.OrderLine;
import pl.michal.Shop.model.views.TopCommissionUserResponse;
import pl.michal.Shop.model.views.TopUserCoinsResponse;
import pl.michal.Shop.service.orders.OrderService;
import pl.michal.Shop.service.pricing.PricingService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;
    private static Long orderID;
    private static Long userId;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

    @GetMapping
    public Long logIn(@RequestParam Long userID) {
        userId = userID;

        return userID;
    }


    @PostMapping
    //    public OrderResponse create(@RequestParam Long userID, @RequestParam Long productId, @RequestParam Long quantity) {
    //  public OrderResponse create(@RequestBody  CreateOrderRequest createOrderRequest) {
    public OrderResponse create(@RequestBody CreateOrderLineRequest createOrderLineRequest) {
        Order order = orderService.create(userId, createOrderLineRequest);
        orderID = order.getId();
        return orderService.getOrderResponse(orderID);

    }

    @PostMapping(path = "/{orderLines}")
    public OrderlineView createOrderLine(@RequestBody CreateOrderLineRequest createOrderLineRequest) {
        //  OrderLine orderLine = orderService.updateOrderWithNewOrderLine(orderID, userId, createOrderRequest);
        OrderLine orderLine = orderService.updateOrderWithNewOrderLine(orderID, userId, createOrderLineRequest);
        return orderService.getOrderLineResponse(orderLine);
    }

    @GetMapping(path = "/getUser")
    public OrderResponse getOrderDetails(@RequestParam Long orderId) {

        return orderService.getOrderResponse(orderId);
    }

    @GetMapping(path = "/getUser/getCoins")
    public List<TopUserCoinsResponse> topCoinsUser() {

        //TODO zapytaj o widoki- o kolejnosc wyswietlonych pol
        return orderService.topCoinsUser();

    }

    @GetMapping(path = "/getUser/getCommission")
    public List<TopCommissionUserResponse> topCommissionUser() {
        return orderService.topCommissionUser();
    }

    @GetMapping(path = "/getCommission")
    public BigDecimal totalCommissionFromApplication() {
        return orderService.applicationProfits();
    }




//top 10 userow z najwieksza iloscia coinsow
//top 10 userow z najwieksza prowizja
//ile w ciagu miesiaca aplikacja zarobila na prowizjach


}