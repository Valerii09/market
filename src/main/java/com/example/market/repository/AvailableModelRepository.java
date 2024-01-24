package com.example.market.repository;

import com.example.market.model.AvailableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableModelRepository extends JpaRepository<AvailableModel, Long> {
    List<AvailableModel> findByProductId(Long productId);
}

