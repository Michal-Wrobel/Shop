package pl.michal.Shop.service.products;

import pl.michal.Shop.DTOs.CreateProductRequest;
import pl.michal.Shop.model.Product;
import pl.michal.Shop.model.views.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(CreateProductRequest createProductRequest);

    List<ProductResponse> getAll();

    Product update(Product product);

    List<Product> remove(Product product);
    List<Product> removeAll();
}
