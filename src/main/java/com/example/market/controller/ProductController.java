package com.example.market.controller;

import com.example.market.model.AvailableModel;
import com.example.market.model.Product;
import com.example.market.repository.ProductRepository;
import com.example.market.service.AvailableModelService;
import com.example.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    private AvailableModelService availableModelService;

    @GetMapping("/home")
    public String getAllProducts(@RequestParam(required = false) Long categoryId, Model model) {
        List<Product> productList;

        if (categoryId != null) {
            // Если categoryId задан, загрузить только продукты для выбранной категории
            productList = productRepository.findByCategoryId(categoryId);
        } else {
            // В противном случае загрузить все продукты
            productList = productRepository.findAll();
        }

        for (Product product : productList) {
            List<AvailableModel> availableModels = availableModelService.getAvailableModelsByProductId(product.getId());
            product.setAvailableModels(availableModels);
        }

        System.out.println("Number of products: " + productList.size());
        model.addAttribute("products", productList);
        return "home";
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

        // Возвращаем JSON-ответ с идентификатором сохраненного продукта и идентификатором категории
        return ResponseEntity.ok("{\"productId\":" + product.getId() + ", \"categoryId\":" + product.getCategory() + "}");
    }

    @GetMapping("/filterProducts")
    @ResponseBody
    public ResponseEntity<List<Product>> filterProductsByCategory(@RequestParam Long categoryId) {
        try {
            List<Product> products = productService.getProductsByCategoryId(categoryId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            // Обработка ошибок, если необходимо
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}



