package com.example.market.controller;

import com.example.market.model.AvailableModel;
import com.example.market.model.Product;
import com.example.market.repository.ProductRepository;
import com.example.market.service.AvailableModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AvailableModelService availableModelService;

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

    @PostMapping("/api/saveProduct")
    @ResponseBody

    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        System.out.println("Received product data: " + product);
        // Пример сохранения продукта в репозитории
        productRepository.save(product);

        // Пример получения доступных моделей для продукта
        List<AvailableModel> availableModels = product.getAvailableModels();
        if (availableModels != null) {
            for (AvailableModel availableModel : availableModels) {
                availableModel.setProduct(product);
            }
        }

        // Возвращаем JSON-ответ, например, с идентификатором сохраненного продукта
        return ResponseEntity.ok("{\"categoryId\":" + product.getCategory() + "}");
    }


}