package com.Madasamy.driveluxe.model;

import jakarta.persistence.*;

// decimal value
import java.math.BigDecimal;
import java.time.LocalDate;


// Maps to 'cars' table in MySQL
@Entity
@Table(name = "cars")
public class Car {


    @Id            //  primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // Auto-incremented ID by database
    @Column(name="car_id")
    private int carId;


    @Column(nullable = false, length = 100)
    private String brand;

    @Column(nullable = false, length = 100)
    private String model;

    @Column(nullable = false, length = 100)
    private String variant;

    @Column(name = "fuel_type", nullable = false)
    private String fuelType;


    @Column(nullable = false)
    private int year;

    //  total 10 numbers , after (.) 2 numbers
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stockQuantity;

    @Column(nullable = false, length = 255)
    private String imageUrl;

    //  cannnot be updated
    @Column(name = "created_at", nullable = false, updatable = false)
    private final LocalDate createdAt = LocalDate.now();

    //  default Constructors
    public Car() {
    }


    //  parameterized constructor
    public Car(String brand, String model, String variant, int year, BigDecimal price, int stockQuantity, String imageUrl, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.variant = variant;
        this.fuelType = fuelType;
        this.year = year;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }

    //  Getters and Setters
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }


    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }


}
