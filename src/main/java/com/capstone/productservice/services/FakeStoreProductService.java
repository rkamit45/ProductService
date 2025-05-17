package com.capstone.productservice.services;

import com.capstone.productservice.dtos.FakeStoreProductDto;
import com.capstone.productservice.exceptions.ProductNotFoundException;
import com.capstone.productservice.models.Category;
import com.capstone.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
        ResponseEntity<FakeStoreProductDto[]> productDtos = restTemplate.getForEntity(baseUrl, FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : productDtos.getBody()) {
            products.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return products;
    }

    @Override
    public Product getProduct(long productId) throws ProductNotFoundException {
       // throw new RuntimeException("Something went wrong");

        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProductDto(productId);
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException(productId,"Product with id " + productId + " doesn't exist.");
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }

    private FakeStoreProductDto getFakeStoreProductDto(long productId) {
        String url = this.baseUrl + productId;
        ResponseEntity<FakeStoreProductDto> responseEntity  =restTemplate.getForEntity(url, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(fakeStoreProductDto), FakeStoreProductDto.class);
        fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Product product) {
        FakeStoreProductDto productDto = convertProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> productDtoResponseEntity = null;
        if(getFakeStoreProductDto(product.getId())==null){
            productDtoResponseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(productDto), FakeStoreProductDto.class);
        }else{
            productDtoResponseEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, new HttpEntity<>(productDto), FakeStoreProductDto.class);
        }
        FakeStoreProductDto updatedProductDto = productDtoResponseEntity.getBody();
        return convertFakeStoreProductDtoToProduct(updatedProductDto);
    }

    @Override
    public Product updateProduct(Product product, long productId) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProductDto(productId);
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException(productId,"Product with id " + productId + " doesn't exist.");
        }else{
            if(product.getTitle()!=null && !product.getTitle().isEmpty()){
                fakeStoreProductDto.setTitle(product.getTitle());
            }
            if(product.getDescription()!=null && !product.getDescription().isEmpty()){
                fakeStoreProductDto.setDescription(product.getDescription());
            }
            if(product.getPrice()!=null && product.getPrice()>0){
                fakeStoreProductDto.setPrice(product.getPrice());
            }
            if(product.getCategory()!=null){
                fakeStoreProductDto.setCategory(product.getCategory().getTitle());
            }
            if(product.getImageUrl()!=null && !product.getImageUrl().isEmpty()){
                fakeStoreProductDto.setImage(product.getImageUrl());
            }
        }

        ResponseEntity<FakeStoreProductDto> productDtoResponseEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, new HttpEntity<>(fakeStoreProductDto), FakeStoreProductDto.class);
        fakeStoreProductDto = productDtoResponseEntity.getBody();
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    @Override
    public void deleteProduct(long id) {

    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDto productDto = new FakeStoreProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImageUrl());
        return productDto;
    }
}
