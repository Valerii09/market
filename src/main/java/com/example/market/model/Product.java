package com.example.market.model;

import jakarta.persistence.*;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    public Boolean getOnlineOrderAvailability() {
        return onlineOrderAvailability;
    }

    public void setOnlineOrderAvailability(Boolean onlineOrderAvailability) {
        this.onlineOrderAvailability = onlineOrderAvailability;
    }

    public Boolean getInstallmentOption() {
        return installmentOption;
    }

    public void setInstallmentOption(Boolean installmentOption) {
        this.installmentOption = installmentOption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getAvailableModels() {
        return availableModels;
    }

    public void setAvailableModels(String availableModels) {
        this.availableModels = availableModels;
    }
}
