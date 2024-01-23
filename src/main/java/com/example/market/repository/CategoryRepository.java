package com.example.market.repository;


import com.example.market.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Здесь можно добавить кастомные методы, если необходимо
}