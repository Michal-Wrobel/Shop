package pl.michal.Shop.service.orders;

import pl.michal.Shop.DTOs.CreateOrderRequest;
import pl.michal.Shop.model.User;
import pl.michal.Shop.model.views.OrderResponse;
import pl.michal.Shop.model.views.OrderlineView;
import pl.michal.Shop.DTOs.CreateOrderLineRequest;
import pl.michal.Shop.model.Order;
import pl.michal.Shop.model.OrderLine;
import pl.michal.Shop.model.views.TopCommissionUserResponse;
import pl.michal.Shop.model.views.TopUserCoinsResponse;

import java.math.BigDecimal;
import java.util.List;


public interface OrderService {

    Order create(Long userId, CreateOrderLineRequest createOrderLineRequest);

    //  Order updateOrderWithNewOrderLine(Order order, CreateOrderLineRequest createOrderLineRequest);

    OrderLine updateOrderWithNewOrderLine(Long orderId, Long userID, CreateOrderLineRequest createOrderLineRequest);

    OrderResponse getOrderResponse(Long orderID);

    List<Order> getAll();

    Order update(Order order);

    List<Order> remove(Order order);

    List<Order> removeAll();

    public OrderlineView getOrderLineResponse(OrderLine orderLine);
    List<TopUserCoinsResponse> topCoinsUser();

    List<TopCommissionUserResponse> topCommissionUser();

    BigDecimal applicationProfits();
}
