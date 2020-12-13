package pl.michal.Shop.DTOs;

import javax.persistence.Column;
import java.math.BigDecimal;

public class CreateProductRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private Long customerId;
    private String categoryName;


    public CreateProductRequest(String title, String description, BigDecimal price, Long customerId, String categoryName) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.customerId = customerId;
        this.categoryName = categoryName;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getCustomerId() {
        return customerId;
    }


    public String getCategoryName() {
        return categoryName;
    }
}
