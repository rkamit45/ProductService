package com.capstone.productservice.services;

import com.capstone.productservice.exceptions.CategoryNotFoundException;
import com.capstone.productservice.exceptions.ProductNotFoundException;
import com.capstone.productservice.models.Product;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProduct(long productID) throws ProductNotFoundException;
    Product addProduct(Product product) throws CategoryNotFoundException;
    Product updateProduct(Product product) throws CategoryNotFoundException;
    void deleteProduct(long productId) throws ProductNotFoundException;
    default Product updateProduct(Product product, long productId) throws ProductNotFoundException{
        return product;
    }
}
