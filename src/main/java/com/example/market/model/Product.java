package com.example.market.model;

import jakarta.persistence.*;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Category category;
    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Column(name = "manufacturer_country")
    private String manufacturerCountry;

    @Column(name = "online_order_availability")
    private Boolean onlineOrderAvailability;

    @Column(name = "installment_option")
    private Boolean installmentOption;

    @Column(name = "name")
    private String name;

    @Column(name = "additional_data", columnDefinition = "json")
    private String additionalData;

    @Column(name = "available_models ", columnDefinition = "json")
    private String availableModels ;

    public String getName() {
        return name;
    }
    // Геттеры, сеттеры и другие методы
}