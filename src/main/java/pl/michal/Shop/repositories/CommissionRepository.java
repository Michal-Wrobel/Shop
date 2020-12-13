package pl.michal.Shop.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.michal.Shop.model.Category;
import pl.michal.Shop.model.Commission;

public interface CommissionRepository extends CrudRepository<Commission,Long> {
}
