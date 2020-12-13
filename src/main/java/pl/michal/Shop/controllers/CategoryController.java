package pl.michal.Shop.controllers;

import org.springframework.web.bind.annotation.*;
import pl.michal.Shop.model.Category;
import pl.michal.Shop.service.category.CategoryService;

@RestController
@RequestMapping(path = "/categories")

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category create(@RequestParam String name) {
        return categoryService.add(name);

    }

    @GetMapping
    public Category findByName(@RequestParam String name) {
        return categoryService.findByName(name);

    }

}
