package com.example.market.model;

import jakarta.persistence.*;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "availability")
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "technology")
    private String technology;

    @Column(name = "dust_bag_volume")
    private String dustBagVolume;

    @Column(name = "doors_count")
    private Integer doorsCount;

    @Column(name = "compressor_type")
    private String compressorType;

    @Column(name = "memory")
    private String memory;

    @Column(name = "cameras_count")
    private Integer camerasCount;

    @Column(name = "processor_type")
    private String processorType;
    public String getName() {
        return name;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    // Геттеры, сеттеры и другие методы
}