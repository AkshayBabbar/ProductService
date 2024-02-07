package com.example.productservicejanbatch24.controllers;


import org.example.productservice.models.Product;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService) {
        this.productService = productService;
    }

//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/{id}")
    public Product getProductbyId(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<String> getAllProducts() {
        return Collections.emptyList();
    }

//    public String getProductByCategory(String category) {
//
//    }

}

/**
 * www.xyz.com/api/......
 * Endpoint is nothing but a combination of Domain Name + Path of Entity API
 */

/**
 * 1. GetProductById(Id)
 * 2. GetAllProducts
 * 3. UpdateProductById()
 * 4. DeleteProduct(id)
 * 5. AddProduct()
 */

/**
 * 1 Constructor Injection
 * 2. Feild Injection
 * 3. Setter Injection
 */