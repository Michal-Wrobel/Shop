package pl.michal.Shop.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "commision")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }
}
