package org.example.productservice.controllers;

import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * 1. Get ProductById(ID)
 * 2. GetAllProducts()
 * 3. UpdateProductById()
 * 4. DeletePrduct(id)
 * 5. AddProduct()
 */

/**
 *  1.Field Injection
 *  2.Setter Injection
 *  3.Constructor Injection
 */

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<String> getAllProducts() {
        return Collections.emptyList();
    }


//    @GetMapping
//    public String getProductByCategory(String Category) {

//    }

    }
