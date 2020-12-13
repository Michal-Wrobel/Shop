package pl.michal.Shop.service.category;

import pl.michal.Shop.model.Category;

public interface CategoryService {
    Category add(String name);

    Category findByName(String name);

}
