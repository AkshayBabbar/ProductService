package org.example.productservice.controllers;


import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//localhost:8080/products
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductbyId(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
//        if (product == null) {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            return responseEntity;
//        }
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;

//        ResponseEntity<Product> responseEntity = null;
//        try {
//            Product product = productService.getProductById(id);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (ArithmeticException e) {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        //Controllers should be as light as possible.
//        return responseEntity;
        //throw new RuntimeException("Something went wrong");
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public Product deleteProductbyId(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    @PutMapping("{id}")
    public Product updateProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
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