package com.capstone.productservice.controllers;

import com.capstone.productservice.models.Product;
import com.capstone.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Product getProduct(@PathVariable long id) {
        return productService.getProduct(id);
    }
}
