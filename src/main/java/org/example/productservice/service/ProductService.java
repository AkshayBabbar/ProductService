package org.example.productservice.service;

import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product updateProductById(Long id, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long id, Product product);

    Product addProduct(Product product);

    void deleteProductById(Long id) throws ProductNotFoundException;
}
