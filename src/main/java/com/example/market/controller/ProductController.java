package com.example.market.controller;

import com.example.market.model.AvailableModel;
import com.example.market.model.Product;
import com.example.market.repository.ProductRepository;
import com.example.market.service.AvailableModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AvailableModelService availableModelService; // Ваш сервис для работы с доступными моделями

    @GetMapping("/home")
    public String getAllProducts(Model model) {
        List<Product> productList = productRepository.findAll();

        for (Product product : productList) {
            List<AvailableModel> availableModels = availableModelService.getAvailableModelsByProductId(product.getId());
            product.setAvailableModels(availableModels);
        }

        System.out.println("Number of products: " + productList.size());
        model.addAttribute("products", productList);
        return "home"; // Без слэша в начале
    }
}
