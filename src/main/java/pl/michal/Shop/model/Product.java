package pl.michal.Shop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
//TODo ma byc pole String z uzytkownikami ktorzy to kupili ( ich user ids )
//TODO jak klient wejdzie w podsumowanie swojego zamowienia- to ma mu rowniez zwrocic dane sprzedawcy
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long salesId;  // id sprzedawcy np - ,yli mi sie z klientem
    @Column()
    private String title;
    @Column()
    private String description;
    @Column()
    private BigDecimal price;
    @Column()
    private LocalDateTime creationDate;
    @Column()
    private LocalDateTime updatedDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoryId")
    private Category categoryId;

    //dodac do produktu - kupione przez

    @OneToMany(mappedBy = "product")
    List<PurchasedProductByUser> purchasedProductByUser;

    public Product() {
    }

    public Product(String title, String description, BigDecimal price, Long salesId, Category category) {
        this.salesId = salesId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.categoryId = category;
    }

    public Long getSalesId() {
        return salesId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
