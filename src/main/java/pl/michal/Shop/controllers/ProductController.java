package pl.michal.Shop.controllers;

import org.springframework.web.bind.annotation.*;
import pl.michal.Shop.DTOs.CreateProductRequest;
import pl.michal.Shop.model.Product;
import pl.michal.Shop.model.views.ProductResponse;
import pl.michal.Shop.service.products.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {


        return productService.create(createProductRequest);

    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }
}
