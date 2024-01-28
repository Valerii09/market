package com.example.market.repository;

import com.example.market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.categoryId = :categoryId, p.manufacturerName = :manufacturerName, " +
            "p.manufacturerCountry = :manufacturerCountry, p.onlineOrderAvailability = :onlineOrderAvailability, " +
            "p.installmentOption = :installmentOption, p.name = :name WHERE p.id = :productId")
    void updateProductById(@Param("productId") Long productId,
                           @Param("categoryId") Long categoryId,
                           @Param("manufacturerName") String manufacturerName,
                           @Param("manufacturerCountry") String manufacturerCountry,
                           @Param("onlineOrderAvailability") Boolean onlineOrderAvailability,
                           @Param("installmentOption") Boolean installmentOption,
                           @Param("name") String name);

    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByCategoryIdAndNameContaining(Long categoryId, String searchQuery);
    List<Product> findByNameContaining(String searchQuery);

}