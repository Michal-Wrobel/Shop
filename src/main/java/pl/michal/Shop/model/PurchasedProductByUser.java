package pl.michal.Shop.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Purchased_Products_By_User")
public class PurchasedProductByUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;



    public PurchasedProductByUser(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    ;
}
//TODO
//dodac do produktu - kupione przez
//10:00
//2. Dodac komentarze do produktu
//
//
//
//
//
//10:04
//** zastanowic sie jak dodac zdjecia
//10:05
//pricingService