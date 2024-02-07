package org.example.productservice.service;

import org.example.productservice.dto.FakeStoreProductDTO;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;
    private final String getProductUrl = "https://fakestoreapi.com/products/1";
    private final String genericProductUrl = "https://fakestoreapi.com/products";

    @Autowired
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDTO.class);
        return getProductFromFakeStoreProductDTO(responseEntity.getBody());
    }

    @Override
    public List<Product> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDTO[].class);
        List<Product> productList = new LinkedList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : responseEntity.getBody()) {
            productList.add(getProductFromFakeStoreProductDTO(fakeStoreProductDTO));
        }
        return productList;
    }

    @Override
    public String getProductByCategory(String Category) {
        return null;
    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void addProduct() {

    }

    @Override
    public void updateProductById() {

    }

    public Product getProductFromFakeStoreProductDTO(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setDesc(fakeStoreProductDTO.getDescription());
        product.setTitle(fakeStoreProductDTO.getTitle());
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        product.setPrice(fakeStoreProductDTO.getPrice());
        return product;
    }
}