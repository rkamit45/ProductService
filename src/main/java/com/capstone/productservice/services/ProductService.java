package com.capstone.productservice.services;

import com.capstone.productservice.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProduct(long id);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(long id);
}
