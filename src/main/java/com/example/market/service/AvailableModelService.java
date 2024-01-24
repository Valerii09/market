package com.example.market.service;

import com.example.market.model.AvailableModel;
import com.example.market.repository.AvailableModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableModelService {

    @Autowired
    private AvailableModelRepository availableModelRepository; // Замените на ваш репозиторий

    public List<AvailableModel> getAvailableModelsByProductId(Long productId) {
        return availableModelRepository.findByProductId(productId);
    }

    // Другие методы, если необходимо
}
