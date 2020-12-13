package pl.michal.Shop.service.products;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.Shop.DTOs.CreateProductRequest;
import pl.michal.Shop.model.Category;
import pl.michal.Shop.model.Product;
import pl.michal.Shop.model.views.ProductResponse;
import pl.michal.Shop.repositories.CategoryRepository;
import pl.michal.Shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductResponse create(CreateProductRequest createProductRequest) {

        // Categoria musi byc najpierw stworzona - kategoria ma byc enumem- i ma byc walidacja
        // Bedziemy robic mappery - bedzie biblioteka i obiekty DTO
        Category category;
        Optional<Category> checkCategory = categoryRepository.findByName(createProductRequest.getCategoryName());
        if (!checkCategory.isPresent()) {
            category = new Category(createProductRequest.getCategoryName());
            categoryRepository.save(category).getName();

        } else {
            category = categoryRepository.findByName(createProductRequest.getCategoryName()).get();
        }
        Product product = new Product(createProductRequest.getTitle()
                , createProductRequest.getDescription()
                , createProductRequest.getPrice()
                , createProductRequest.getCustomerId()
                , category);
        productRepository.save(product);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(String.valueOf(product.getId()));
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(Double.parseDouble(String.valueOf(product.getPrice())));
        productResponse.setSalesId(String.valueOf(product.getSalesId()));
        productResponse.setCreationDate(String.valueOf(product.getCreationDate()));
        productResponse.setUpdatedDate(String.valueOf(product.getUpdatedDate()));
        return productResponse;
    }

    //    public Product(String title, String description, BigDecimal price, Category category) {
    @Override
    public List<ProductResponse> getAll() {

        List<ProductResponse> productResponses = new ArrayList<>();
        Lists.newArrayList(productRepository.findAll()).stream().forEach(product ->
                {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(String.valueOf(product.getId()));
                    productResponse.setDescription(product.getDescription());
                    productResponse.setPrice(Double.parseDouble(String.valueOf(product.getPrice())));
                    productResponse.setSalesId(String.valueOf(product.getSalesId()));
                    productResponse.setCreationDate(String.valueOf(product.getCreationDate()));
                    productResponse.setUpdatedDate(String.valueOf(product.getUpdatedDate()));
                    productResponses.add(productResponse);
                }
        );
        return productResponses;
    }

    @Override
    public Product update(Product product) {

        if (product.getId() == null) {
            throw new IllegalArgumentException("Product should have id");
        }
        Long productID = productRepository.save(product).getId();
        return productRepository.findById(productID).get();

    }

    @Override
    public List<Product> remove(Product product) {

        productRepository.delete(product);
        return Lists.newArrayList(productRepository.findAll());
    }

    @Override
    public List<Product> removeAll() {
        productRepository.deleteAll();
        return Lists.newArrayList(productRepository.findAll());
    }







}
