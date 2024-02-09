package org.example.productservice.service;

import org.example.productservice.dto.FakeStoreProductDTO;
import org.example.productservice.exception.NoProductFoundException;
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
    private final String specifiedProductUrl = "https://fakestoreapi.com/products/{id}";

    @Autowired
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) throws NoProductFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(specifiedProductUrl, FakeStoreProductDTO.class, id);
        if (responseEntity.getBody() == null) {
            //throw exception
            throw new NoProductFoundException("Product not found for id: " + id);
        }
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
    public void updateProductById() {

    }

    @Override
    public Product addProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.postForEntity(genericProductUrl,
                        getFakeStoreProductDTOFromProduct(product), FakeStoreProductDTO.class);

        return getProductFromFakeStoreProductDTO(responseEntity.getBody());
    }
    private Product getProductFromFakeStoreProductDTO(FakeStoreProductDTO fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDesc(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setPrice(fakeStoreProductDto.getPrice());

        return product;
    }

    private FakeStoreProductDTO getFakeStoreProductDTOFromProduct(Product product){
        FakeStoreProductDTO fakeStoreProductDto = new FakeStoreProductDTO();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDesc());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        return fakeStoreProductDto;
    }

}