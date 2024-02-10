package org.example.productservice.thirdPartyClient;

import org.example.productservice.dto.FakeStoreProductDTO;
import org.example.productservice.exception.NoProductFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;
    private final String getProductUrl = "https://fakestoreapi.com/products/1";
    private final String genericProductUrl = "https://fakestoreapi.com/products";
    
    @Value("${fakestore.api.url}")
    private final String specificProductUrl;

    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
        specificProductUrl = null;
    }

    public FakeStoreProductDTO getProductById(Long id) throws NoProductFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO.class, id);
        if (responseEntity.getBody() == null) {
            //throw exception
            throw new NoProductFoundException("Product not found for id: " + id);
        }

        return responseEntity.getBody();
    }

    public FakeStoreProductDTO[] getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDTO[].class);
        return responseEntity.getBody();
    }

    public String getProductByCategory(String Category) {
        return null;
    }

    public FakeStoreProductDTO deleteProductById(Long id) throws NoProductFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        if (responseEntity.getBody() == null) {
            //throw exception
            throw new NoProductFoundException("Product not found for id: " + id);
        }

        return responseEntity.getBody();
    }

    public void updateProductById() {

    }

    public FakeStoreProductDTO addProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(genericProductUrl, fakeStoreProductDTO, FakeStoreProductDTO.class);
        return responseEntity.getBody();
    }

}
