package com.example.market.service;

import com.example.market.model.Product;
import com.example.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(Product product) {
        // Можно добавить логику валидации данных перед сохранением, если необходимо
        productRepository.save(product);
    }
}