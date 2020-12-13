package pl.michal.Shop.service.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.michal.Shop.converters.OrdersConverters;
import pl.michal.Shop.model.OrderLine;
import pl.michal.Shop.model.User;
import pl.michal.Shop.model.views.TopUserCoinsResponse;
import pl.michal.Shop.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PricingServiceImp implements PricingService {

    private OrdersConverters ordersConverters;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EntityManagerFactory emf;

    @Value("${commission}")
    private String commission;

    @Value("${coins.divider}")
    private String coinsDivider;

    @Override
    public User addCoinToUser(OrderLine orderLine) {

        User user = orderLine.getOrderId().getUser();

        user.addCoins(orderLine.getPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity())).divide(new BigDecimal(coinsDivider)));
        userRepository.save(user);
        return user;
    }

    @Override
    public User addCommission(OrderLine orderLine) {


        User user = orderLine.getOrderId().getUser();


        user.addCommission(orderLine.getPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity())
                .multiply(new BigDecimal(commission))));
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> topCoinsUser() {

        List<User> result = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("select us" +
                        " from User us" +
                        " order by us.coins  desc  "
                , User.class
        );


        query.setMaxResults(10);

        result = query.getResultList();
        // result = query.getResultList().subList(result.size() - 10, result.size());


        return result;

    }

    @Override
    public List<User> topCommissionUser() {
        List<User> result = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("select us" +
                        " from User us" +
                        " order by us.commission  desc  "
                , User.class
        );


        query.setMaxResults(10);

        result = query.getResultList();
        return result;
    }

    @Override
    public BigDecimal applicationProfits() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<BigDecimal> query = em.createQuery("select sum (ord.commission) from Order ord"
                , BigDecimal.class
        );
        BigDecimal result = query.getSingleResult();
        return result;
    }


}
