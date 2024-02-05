package org.example.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProductById(Long id) {
        return "Product Fetched with id " + id;
    }

    @GetMapping()
    public List<String> getAllProducts() {
        return Collections.emptyList();
    }

//    @GetMapping
//    public String getProductByCategory(String Category) {

//    }

}
