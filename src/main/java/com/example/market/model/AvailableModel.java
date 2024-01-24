package com.example.market.model;

import jakarta.persistence.*;


@Entity
@Table(name = "available_models")
public class AvailableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "name")
    private String name;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private String size;
    @Column(name = "price")
    private Double price;
    @Column(name = "category")
    private String category;
    @Column(name = "technology")
    private String technology;
    @Column(name = "dustbin_capacity")
    private Integer dustbinCapacity;
    @Column(name = "modes_count")
    private Integer modesCount;
    @Column(name = "doors_count")
    private Integer doorsCount;
    @Column(name = "compressor_type")
    private String compressorType;
    @Column(name = "memory")
    private String memory;
    @Column(name = "chambers_count")
    private Integer chambersCount;
    @Column(name = "processor_type")
    private String processorType;


    @Column(name = "availability")
    private Boolean availability;

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    // Другие геттеры и сеттеры

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    // Другие геттеры и сеттеры для остальных полей

    // Вы также можете добавить equals, hashCode и toString по необходимости
}
