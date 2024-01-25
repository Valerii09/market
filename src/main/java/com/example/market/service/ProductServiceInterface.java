package com.example.market.service;

import com.example.market.model.Product;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
    void updateProduct(Long id, Product updatedProduct);
    void deleteProduct(Long id);
}