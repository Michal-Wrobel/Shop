package pl.michal.Shop.DTOs;

import java.time.LocalDateTime;
import java.util.Set;


public class CreateOrderRequest {

    private LocalDateTime created;
    private Long userId;

    private Long orderID;
    private Long productId;
    private Long quantity;

    public CreateOrderRequest(Long userId, Long orderID, Long productId, Long quantity) {
        this.userId = userId;
        this.orderID = orderID;
        this.productId = productId;
        this.quantity = quantity;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
