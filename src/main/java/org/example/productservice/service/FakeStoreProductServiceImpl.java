package org.example.productservice.service;

import org.example.productservice.dto.FakeStoreProductDTO;
import org.example.productservice.exception.NoProductFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.thirdPartyClient.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService {

    private FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProductById(Long id) throws NoProductFoundException {
        return getProductFromFakeStoreProductDTO(fakeStoreClient.getProductById(id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new LinkedList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreClient.getAllProducts()) {
            productList.add(getProductFromFakeStoreProductDTO(fakeStoreProductDTO));
        }
        return productList;
    }

    @Override
    public String getProductByCategory(String Category) {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) throws NoProductFoundException {
        return getProductFromFakeStoreProductDTO(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public void updateProductById() {

    }

    @Override
    public Product addProduct(Product product) {
        return getProductFromFakeStoreProductDTO(fakeStoreClient.addProduct(getFakeStoreProductDTOFromProduct(product)));
    }

    private Product getProductFromFakeStoreProductDTO(FakeStoreProductDTO fakeStoreProductDto) {
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

    private FakeStoreProductDTO getFakeStoreProductDTOFromProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDto = new FakeStoreProductDTO();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDesc());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        return fakeStoreProductDto;
    }

}