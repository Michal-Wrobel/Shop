package pl.michal.Shop.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.michal.Shop.model.PurchasedProductByUser;

@Repository
public interface PurchasedProductByUserRepository extends CrudRepository<PurchasedProductByUser, Long> {
}
