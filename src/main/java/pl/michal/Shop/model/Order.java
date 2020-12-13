package pl.michal.Shop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//TODO dodaj status orderu- metoda delete() nie usuwa orderu tylko go canceluje
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime created;


//  @Column(name = "commission", nullable = false, columnDefinition = "decimal default 0")
    private BigDecimal commission;
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderLine> orderLines;


    //TODO tymczasowe
    private BigDecimal totalSum;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Order() {
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public Order(User user, LocalDateTime created, List<OrderLine> orderLines) {
        this.user = user;
        this.created = created;
        this.orderLines = orderLines;
        this.commission= BigDecimal.valueOf(0);
        this.totalSum= BigDecimal.valueOf(0);
        // this.commission=orderLines.stream().map(x->BigDecimal.valueOf(x.getQuantity()*x.getPrice()))
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission() {
        this.commission = BigDecimal.valueOf(999);

    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addOrderLine(OrderLine orderLine) {

        orderLines.add(orderLine);

        orderLine.setOrderId(this);

    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", commission=" + commission +
                ", orderLines=" + orderLines +
                ", totalSum=" + totalSum +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(created, order.created) &&
                Objects.equals(commission, order.commission) &&
                Objects.equals(orderLines, order.orderLines) &&
                Objects.equals(totalSum, order.totalSum) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, commission, orderLines, totalSum, user);
    }


}
