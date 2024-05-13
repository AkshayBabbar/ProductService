package org.example.productservice.service;

import org.example.productservice.dto.FakeStoreProductDTO;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.thirdPartyClient.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService {

    private FakeStoreClient fakeStoreClient;
    private RestTemplate restTemplate;

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setTitle(dto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Call FakeStore API here to get the Product with given id.
        FakeStoreProductDTO fakeStoreProductDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDTO.class);
        //1st param -> URL
        //2nd param -> Response
        if (fakeStoreProductDTO == null) {
            throw new ProductNotFoundException(id, "Product not found with " + id + " not found");
//            return null;

        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDTO> fakeStoreProductDTOs = restTemplate.getForObject("https://fakestoreapi.com/products/", List.class);
        List<Product> response = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDto : fakeStoreProductDTOs) {
            response.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }

        return response;
    }

    @Override
    public String getProductByCategory(String Category) {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) throws ProductNotFoundException {
        return getProductFromFakeStoreProductDTO(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public Product updateProductById(Long id) throws ProductNotFoundException {
        return getProductFromFakeStoreProductDTO(fakeStoreClient.updateProductById(id));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }


    @Override
    public Product replaceProduct(Long id, Product product)  {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setImage(product.getImage());
        fakeStoreProductDTO.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDTO response =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreDtoToProduct(response);
    }

    @Override
    public Product addProduct(Product product) {
        return getProductFromFakeStoreProductDTO(fakeStoreClient.addProduct(getFakeStoreProductDTOFromProduct(product)));
    }

    private Product getProductFromFakeStoreProductDTO(FakeStoreProductDTO fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setPrice(fakeStoreProductDto.getPrice());

        return product;
    }

    private FakeStoreProductDTO getFakeStoreProductDTOFromProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDto = new FakeStoreProductDTO();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        return fakeStoreProductDto;
    }

}