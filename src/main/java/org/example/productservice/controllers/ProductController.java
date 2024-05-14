package org.example.productservice.controllers;


import org.example.productservice.commons.AuthCommons;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

//localhost:8080/products
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private AuthCommons authCommons;

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
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
        List<Product> productList = productService.getAllProducts();
        Product product = new Product();
        product.setId(4L);
        product.setTitle("macbook pro");
        productList.add(product);
        return productList;
    }

    @DeleteMapping("/{id}")
    public Product deleteProductbyId(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    //createProduct
    //deleteProduct
    //updateProduct -> Partial Update (PATCH)
    //replaceProduct -> Replace (PUT)

    @PutMapping("{id}")
    public Product updateProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.updateProductById(id);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Void> handleSomeException() {
        return null;
    }

}