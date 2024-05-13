package org.example.productservice.service;

import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    String getProductByCategory(String Category);
    Product updateProductById(Long id) throws ProductNotFoundException;

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);

    Product addProduct(Product product);

    Product deleteProductById(Long id) throws ProductNotFoundException;
}
