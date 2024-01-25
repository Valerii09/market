package com.example.market.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<AvailableModel> availableModels;
    public List<AvailableModel> getAvailableModels() {
        return availableModels;
    }

    public void setAvailableModels(List<AvailableModel> availableModels) {
        this.availableModels = availableModels;
    }

    @JoinColumn(name = "category_id")
    private Long categoryId;
    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Column(name = "manufacturer_country")
    private String manufacturerCountry;

    @Column(name = "online_order_availability")
    private Boolean onlineOrderAvailability;

    @Column(name = "installment_option")
    private Boolean installmentOption;

    @Column(name = "name", unique = false)
    private String name;

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
    @JsonProperty("categoryId")
    public Long getCategory() {
        return categoryId;
    }
    @JsonProperty("categoryId")
    public void setCategory(Long category) {
        this.categoryId = category;
    }

}
