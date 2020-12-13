package pl.michal.Shop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order_Lines")

public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order orderId;

    private BigDecimal price;
    // dodja price- niewazne na razie ze w produkcie jest- jest niewydajne i tak samo product title
    @ManyToOne()
    @JoinColumn(name = "productId")
    private Product productId;

    private Long quantity;

    public OrderLine() {
    }

    public OrderLine(Order orderId, Product productId, Long quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = productId.getPrice();
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(id, orderLine.id) &&
                Objects.equals(orderId, orderLine.orderId) &&
                Objects.equals(price, orderLine.price) &&
                Objects.equals(productId, orderLine.productId) &&
                Objects.equals(quantity, orderLine.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, price, productId, quantity);
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", price=" + price +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
