package pl.michal.Shop.service.pricing;

import org.springframework.stereotype.Repository;
import pl.michal.Shop.model.OrderLine;
import pl.michal.Shop.model.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PricingService {

    User addCoinToUser(OrderLine orderLine);

    User addCommission(OrderLine orderlines);

    List<User> topCoinsUser();

    List<User> topCommissionUser();

    BigDecimal applicationProfits();

}
