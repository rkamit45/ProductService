package com.capstone.productservice.services;

import com.capstone.productservice.exceptions.CategoryNotFoundException;
import com.capstone.productservice.exceptions.ProductNotFoundException;
import com.capstone.productservice.models.Category;
import com.capstone.productservice.models.Product;
import com.capstone.productservice.repositories.CategoryRepository;
import com.capstone.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Service(value="selfProductService")
public class SelfProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long productId) throws ProductNotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(productId,"Product not found for productId : " + productId));
    }

    @Override
    public Product addProduct(Product product) throws CategoryNotFoundException {
        Category category = product.getCategory();
        if (category == null) {
            throw new CategoryNotFoundException("Product can't be created without category, Please try again with category.");
        }

        Optional<Category> optionalCategory = categoryRepository.findByTitle(category.getTitle());
        if (optionalCategory.isEmpty()) {
            //There's no category in the DB with the given title.
            //Create a category object and save it in the DB.
            category = categoryRepository.save(category);
        }else {
            category = optionalCategory.get();
        }
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws CategoryNotFoundException {

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isEmpty()) {
            return addProduct(product);
        }

        Category category = product.getCategory();
        if (category == null) {
            throw new CategoryNotFoundException("Product can't be modified without category, Please try again with category.");
        }

        Optional<Category> optionalCategory;
        if(category.getId()==null){
            optionalCategory = categoryRepository.findByTitle(category.getTitle());
        }else {
            optionalCategory = categoryRepository.findById(category.getId());
        }

        if (optionalCategory.isPresent()) {
            category = optionalCategory.get();
        } else {
            category = categoryRepository.save(category);
        }

        product.setCategory(category);

        Product productDb = optionalProduct.get();
        productDb.setTitle(product.getTitle());
        productDb.setPrice(product.getPrice());
        productDb.setDescription(product.getDescription());
        productDb.setCategory(product.getCategory());

        return productRepository.save(productDb);
    }

    @Override
    public void deleteProduct(long productId) throws ProductNotFoundException {
        Product product = getProduct(productId);
        product.setIsDeleted(true);
        productRepository.save(product);
    }

}
