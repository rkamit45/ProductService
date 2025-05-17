package com.capstone.productservice.services;

import com.capstone.productservice.exceptions.ProductNotFoundException;
import com.capstone.productservice.models.Product;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProduct(long id) throws ProductNotFoundException;
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(long id);
    default Product updateProduct(Product product, long id) throws ProductNotFoundException{
        return product;
    }
}
