package org.example.productservice.controllers;


import org.example.productservice.exception.NoProductFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductbyId(@PathVariable("id") Long id) throws NoProductFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public Product deleteProductbyId(@PathVariable("id") Long id) throws NoProductFoundException {
        return productService.deleteProductById(id);
    }

    @PutMapping("{id}")
    public Product updateProductById(@PathVariable("id") Long id) throws NoProductFoundException{
        return productService.updateProductById(id);
    }

}

/**
 * www.xyz.com/api/......
 * Endpoint is nothing but a combination of Domain Name + Path of Entity API
 * <p>
 * 1. GetProductById(Id)
 * 2. GetAllProducts
 * 3. UpdateProductById()
 * 4. DeleteProduct(id)
 * 5. AddProduct()
 * <p>
 * 1 Constructor Injection
 * 2. Feild Injection
 * 3. Setter Injection
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