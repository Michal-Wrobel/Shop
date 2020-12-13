package pl.michal.Shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.michal.Shop.model.Product;
import pl.michal.Shop.model.User;
@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
}
