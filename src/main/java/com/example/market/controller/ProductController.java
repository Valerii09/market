package com.example.market.controller;

import com.example.market.model.AvailableModel;
import com.example.market.model.Product;
import com.example.market.repository.ProductRepository;
import com.example.market.service.AvailableModelService;
import com.example.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Product>> filterProductsByCategory(@RequestParam(required = false) Long categoryId) {
        try {
            List<Product> products;
            if (categoryId != null && categoryId != -1) {
                // Если categoryId задан и не равен -1, фильтруем по категории
                products = productService.getProductsByCategoryId(categoryId);
            } else {
                // Если categoryId не задан или равен -1, получаем все продукты
                products = productService.getAllProducts();
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            // Обработка ошибок, если необходимо
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/filterProductsByName")
    @ResponseBody
    public ResponseEntity<List<Product>> filterProductsByName(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String searchQuery
    ) {
        try {
            List<Product> products;

            if (categoryId != null && categoryId != -1) {
                // Если categoryId задан и не равен -1, ищем по категории
                if (searchQuery != null && !searchQuery.isEmpty()) {
                    // Если есть поисковый запрос, ищем и по имени
                    products = productService.searchProductsByCategoryIdAndName(categoryId, searchQuery);
                } else {
                    // Иначе получаем все продукты по категории
                    products = productService.getProductsByCategoryId(categoryId);
                }
            } else {
                // Если categoryId не задан или равен -1, получаем все продукты
                if (searchQuery != null && !searchQuery.isEmpty()) {
                    // Если есть только поисковый запрос, ищем по имени
                    products = productService.searchProductsByName(searchQuery);
                } else {
                    // Иначе получаем все продукты
                    products = productService.getAllProducts();
                }
            }

            return ResponseEntity.ok(products);
        } catch (Exception e) {
            // Обработка ошибок, если необходимо
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/api/deleteProduct/{productId}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        try {
            // Пример удаления продукта из репозитория
            productRepository.deleteById(productId);

            // Возвращаем успешный JSON-ответ
            return ResponseEntity.ok("{\"message\":\"Product deleted successfully\"}");
        } catch (Exception e) {
            // Обработка ошибок, если необходимо
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error deleting product\"}");
        }
    }

    @PutMapping("/api/updateProduct/{productId}")
    @ResponseBody
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        try {
            // Получаем существующий продукт из репозитория
            Product existingProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

            // Обновляем данные существующего продукта
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setManufacturerName(updatedProduct.getManufacturerName());
            existingProduct.setManufacturerCountry(updatedProduct.getManufacturerCountry());
            existingProduct.setOnlineOrderAvailability(updatedProduct.getOnlineOrderAvailability());
            existingProduct.setInstallmentOption(updatedProduct.getInstallmentOption());
            existingProduct.setName(updatedProduct.getName());

            // Сохраняем обновленный продукт в репозитории
            productRepository.save(existingProduct);

            return ResponseEntity.ok("Product updated successfully");
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/api/getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        System.out.println("Received request to get product by id: " + productId);

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}



