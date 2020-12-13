package pl.michal.Shop.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private BigDecimal commission;
    @Column(name = "coins", nullable = false, columnDefinition = "decimal default 200")
    private BigDecimal coins;
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST/*, fetch = FetchType.EAGER*/)
    private Set<Order> orders;


    @OneToMany(mappedBy = "user")
    List<PurchasedProductByUser> purchasedProductByUser;



    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public User() {
    }

    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Set<Order> getOrders() {
        return orders;
    }


    public void addCoins(BigDecimal newCoins) {
        this.coins = coins.add(newCoins);

    }

    public void addCommission(BigDecimal newCommission) {
        this.commission = commission.add(newCommission);

    }

    public BigDecimal getCoins() {
        return coins;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}


