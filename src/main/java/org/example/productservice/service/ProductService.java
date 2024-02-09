package org.example.productservice.service;

import org.example.productservice.exception.NoProductFoundException;
import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws NoProductFoundException;
    List<Product> getAllProducts();
    String getProductByCategory(String Category);
    void deleteProductById();
    void updateProductById();

    Product addProduct(Product product);
}
