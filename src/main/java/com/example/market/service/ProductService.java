package com.example.market.service;

import com.example.market.model.Product;
import com.example.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public List<Product> searchProductsByCategoryAndName(Long categoryId, String searchQuery) {
        return productRepository.findByCategoryIdAndNameContaining(categoryId, searchQuery);
    }

    public List<Product> searchProductsByName(String searchQuery) {
        return productRepository.findByNameContaining(searchQuery);
    }
    public List<Product> searchProductsByCategoryIdAndName(Long categoryId, String searchQuery) {
        return productRepository.findByCategoryIdAndNameContaining(categoryId, searchQuery);
    }


}
