package org.example.productservice.service;

import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);
    List<Product> getAllProducts();
    String getProductByCategory(String Category);
    void deleteProductById();
    void addProduct();
    void updateProductById();
}
