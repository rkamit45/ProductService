package com.capstone.productservice.services;

import com.capstone.productservice.dtos.ProductDto;
import com.capstone.productservice.models.Category;
import com.capstone.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.baseUrl = "https://fakestoreapi.com/products/";
    }

    @Override
    public List<Product> getProducts() {
        ResponseEntity<ProductDto[]> productDtos = restTemplate.getForEntity(baseUrl, ProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto : productDtos.getBody()) {
            products.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return products;
    }

    @Override
    public Product getProduct(long id) {
        String url = this.baseUrl + id;
        ResponseEntity<ProductDto> responseEntity  =restTemplate.getForEntity(url, ProductDto.class);
        ProductDto productDto = responseEntity.getBody();
        return convertFakeStoreProductDtoToProduct(productDto);
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        ProductDto productDto = convertProductToFakeStoreProductDto(product);
        ResponseEntity<ProductDto> productDtoResponseEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, new HttpEntity<>(productDto), ProductDto.class);
        ProductDto updatedProductDto = productDtoResponseEntity.getBody();
        return convertFakeStoreProductDtoToProduct(updatedProductDto);
    }

    @Override
    public void deleteProduct(long id) {

    }

    private Product convertFakeStoreProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private ProductDto convertProductToFakeStoreProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getTitle());
        productDto.setDescription(productDto.getDescription());
        productDto.setImage(product.getImageUrl());
        return productDto;
    }
}
