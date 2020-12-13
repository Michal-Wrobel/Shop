package pl.michal.Shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.michal.Shop.model.Category;
import pl.michal.Shop.model.OrderLine;
@Repository
public interface OrderLinesRepository extends CrudRepository<OrderLine,Long> {
}
