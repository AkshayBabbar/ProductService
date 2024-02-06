package org.example.productservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public String getProductById(Long id) {
        return "Product from service: " + id;
    }

    @Override
    public List<String> getAllProducts() {
        return null;
    }

    @Override
    public String getProductByCategory(String Category) {
        return null;
    }
}
