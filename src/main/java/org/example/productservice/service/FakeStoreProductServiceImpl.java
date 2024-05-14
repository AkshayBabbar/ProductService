package org.example.productservice.service;

import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.CategoryRepository;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("FakeProductService")
@Primary
public class FakeStoreProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Fetch the product with the given id from DB.
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(id, "Product not found");
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public String getProductByCategory(String Category) {
        return null;
    }

    @Override
    public Product updateProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        //Before saving the Product object in the DB, save the category object.

        Category category = product.getCategory();

        if (category.getId() == null) {
            //we need to save the category
//            Category savedCategory = categoryRepository.save(category);
//            product.setCategory(savedCategory);
        } else {
            //we should check if the category id is valid or not.
        }

        Product savedProduct = productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(savedProduct.getCategory().getId());
        Category category1 = optionalCategory.get();
        savedProduct.setCategory(category1);
        return savedProduct;
    }

    @Override
    public Product deleteProductById(Long id) throws ProductNotFoundException {
        return null;
    }


}