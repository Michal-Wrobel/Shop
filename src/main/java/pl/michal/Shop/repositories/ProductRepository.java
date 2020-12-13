package pl.michal.Shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.michal.Shop.model.Product;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {


}
