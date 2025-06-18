package com.capstone.productservice.controllers;

import com.capstone.productservice.dtos.ExceptionDto;
import com.capstone.productservice.dtos.ProductRequestDto;
import com.capstone.productservice.dtos.ProductResponseDto;
import com.capstone.productservice.exceptions.CategoryNotFoundException;
import com.capstone.productservice.exceptions.ProductNotFoundException;
import com.capstone.productservice.models.Product;
import com.capstone.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(//@Qualifier("selfProductService")
                             ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable long id) throws ProductNotFoundException {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(convertProductToProductResponseDto(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto) throws CategoryNotFoundException {
        Product product = convertProductRequestDtoToProduct(productRequestDto);
        product = productService.addProduct(product);
        return new ResponseEntity<>(convertProductToProductResponseDto(product), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto productRequestDto) throws CategoryNotFoundException {
        Product product = convertProductRequestDtoToProduct(productRequestDto);
        Product productCreated = productService.updateProduct(product);

        if(!Objects.equals(productCreated.getId(), product.getId())) {
            return new ResponseEntity<>(convertProductToProductResponseDto(productCreated), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(convertProductToProductResponseDto(product), HttpStatus.OK);
    }


    @PatchMapping
    public ResponseEntity<ProductResponseDto> updateProductPrice(@RequestBody ProductRequestDto productRequestDto) throws ProductNotFoundException {
        Product product = convertProductRequestDtoToProduct(productRequestDto);
        Product productUpdated = productService.updateProduct(product, productRequestDto.getProductId());
        return new ResponseEntity<>(convertProductToProductResponseDto(productUpdated), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductId(id);
        productResponseDto.setDescription("Deleted product with id " + id);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException exception) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exception.printStackTrace();
        exceptionDto.setMessage("Handling exception from Controller class");
        exceptionDto.setResolutionDetails(exception.getMessage());
        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ProductResponseDto convertProductToProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductId(product.getId());
        productResponseDto.setName(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        return productResponseDto;
    }

    private Product convertProductRequestDtoToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        if(productRequestDto.getProductId() != null) {
            product.setId(productRequestDto.getProductId());
        }
        product.setTitle(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setCategory(productRequestDto.getCategory());
        return product;
    }
}
