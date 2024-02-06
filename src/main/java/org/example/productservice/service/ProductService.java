package org.example.productservice.service;

import java.util.List;

public interface ProductService {

    public String getProductById(Long id);

    public List<String> getAllProducts();

    public String getProductByCategory(String Category);

}
