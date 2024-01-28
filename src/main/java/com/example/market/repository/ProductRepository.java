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
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name) AND p.categoryId = :categoryId")
    List<Product> findByCategoryIdAndNameContaining( Long categoryId, @Param("name") String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchQuery, '%'))")
    List<Product> findByNameContaining(@Param("searchQuery") String searchQuery);

}