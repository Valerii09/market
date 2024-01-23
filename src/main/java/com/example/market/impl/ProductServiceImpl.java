package com.example.market.impl;

import com.example.market.model.Product;
import com.example.market.repository.ProductRepository;
import com.example.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalExistingProduct = productRepository.findById(id);

        if (optionalExistingProduct.isPresent()) {
            Product existingProduct = optionalExistingProduct.get();
            // Обновляем существующий продукт данными из updatedProduct
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setManufacturerCountry(updatedProduct.getManufacturerCountry());
            existingProduct.setManufacturerName(updatedProduct.getManufacturerName());
            // Другие поля, которые вы хотите обновить

            // Сохраняем обновленный продукт
            productRepository.save(existingProduct);
        }
    }



    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
