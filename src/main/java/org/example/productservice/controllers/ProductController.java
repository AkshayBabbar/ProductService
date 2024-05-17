package org.example.productservice.controllers;


import org.example.productservice.commons.AuthCommons;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.dto.UserDTO;
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
    private final AuthCommons authCommons;

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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        return  new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/all/{token}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String token) {
        UserDTO userDTO = authCommons.validateToken(token);
        if(userDTO == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Product> productList = productService.getAllProducts();
//        Product product = new Product();
//        product.setId(4L);
//        product.setTitle("macbook pro");
//        productList.add(product);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProductbyId(@PathVariable("id") Long id) throws ProductNotFoundException {
        return;
    }

    //createProduct
    //deleteProduct
    //updateProduct -> Partial Update (PATCH)
    //replaceProduct -> Replace (PUT)

    @PutMapping("{id}")
    public Product updateProductById(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProductById(id,product);
    }

    @PutMapping("/products/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Void> handleSomeException() {
        return null;
    }

}