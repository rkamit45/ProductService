package com.capstone.productservice.repositories;

import com.capstone.productservice.models.Category;
import com.capstone.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Select * from Product where id = ?
    @Override
    Optional<Product> findById(Long productId);

    //iphone
    // Select * from products where lower(title) Like '%iphone%';
    List<Product> findAllByTitleContainingIgnoreCase(String title);

    //find all the products where price >= 100 and price <= 200
    List<Product> findByPriceBetween(Double price1, Double price2);

    // Select * from products where category_id = category.id;
    List<Product> findByCategory(Category category);

    //Select * from products where catgory_id = categoryId;
    List<Product> findAllByCategory_Id(Long categoryId);
    //Join Query
    List<Product> findAllByCategory_Title(String categoryTitle);

    @Query(value = "Select p.title from Product p where p.id=:productId")
    Optional<String> findProductTitleById(Long productId);

    // update + insert = upsert
    Product save(Product product);

    @Override
    void deleteById(Long productId);
}
