package com.Madasamy.driveluxe.model;

import java.math.BigDecimal;

/**
 * Represents a car entity with various details.
 */
public final class Car {

    /** Unique identifier for the car. */
    private Long carId;

    /** Brand of the car. */
    private String brand;

    /** Model of the car. */
    private String model;

    /** Variant of the car model. */
    private String variant;

    /** Type of fuel used by the car. */
    private String fuelType;

    /** Manufacturing year of the car. */
    private int year;

    /** Price of the car. */
    private BigDecimal price;

    /** Quantity of the car in stock. */
    private int stockQuantity;

    /** Image URL of the car. */
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

    /**
     * Private constructor for Car using Builder.
     *
     * @param builder the builder instance
     */
    private Car(final Builder builder) {
        this.carId = builder.builderCarId;
        this.brand = builder.builderBrand;
        this.model = builder.builderModel;
        this.variant = builder.builderVariant;
        this.fuelType = builder.builderFuelType;
        this.year = builder.builderYear;
        this.price = builder.builderPrice;
        this.stockQuantity = builder.builderStockQuantity;
        this.imageUrl = builder.builderImageUrl;
    }

    /** @return the car ID */
    public Long getCarId() {

        return carId;
    }

    /** @return the brand */
    public String getBrand() {
        return brand;
    }

    /** @return the model */
    public String getModel() {
        return model;
    }

    /** @return the variant */
    public String getVariant() {
        return variant;
    }

    /** @return the fuel type */
    public String getFuelType() {
        return fuelType;
    }

    /** @return the year */
    public int getYear() {
        return year;
    }

    /** @return the price */
    public BigDecimal getPrice() {
        return price;
    }

    /** @return the stock quantity */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /** @return the image URL */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Builder class for Car.
     */
    public static class Builder {

        /** Car ID. */
        private Long builderCarId;

        /** Brand of the car. */
        private String builderBrand;

        /** Model of the car. */
        private String builderModel;

        /** Variant of the car. */
        private String builderVariant;

        /** Fuel type of the car. */
        private String builderFuelType;

        /** Manufacturing year. */
        private int builderYear;

        /** Price of the car. */
        private BigDecimal builderPrice;

        /** Quantity in stock. */
        private int builderStockQuantity;

        /** Image URL. */
        private String builderImageUrl;

        /**
         * Sets the car ID.
         *
         * @param carId the car ID
         * @return the builder
         */
        public Builder withCarId(final Long carId) {
            this.builderCarId = carId;
            return this;
        }

        /**
         * Sets the brand.
         *
         * @param brand the brand
         * @return the builder
         */
        public Builder withBrand(final String brand) {
            this.builderBrand = brand;
            return this;
        }

        /**
         * Sets the model.
         *
         * @param model the model
         * @return the builder
         */
        public Builder withModel(final String model) {
            this.builderModel = model;
            return this;
        }

        /**
         * Sets the variant.
         *
         * @param variant the variant
         * @return the builder
         */
        public Builder withVariant(final String variant) {
            this.builderVariant = variant;
            return this;
        }

        /**
         * Sets the fuel type.
         *
         * @param fuelType the fuel type
         * @return the builder
         */
        public Builder withFuelType(final String fuelType) {
            this.builderFuelType = fuelType;
            return this;
        }

        /**
         * Sets the year.
         *
         * @param year the year
         * @return the builder
         */
        public Builder withYear(final int year) {
            this.builderYear = year;
            return this;
        }

        /**
         * Sets the price.
         *
         * @param price the price
         * @return the builder
         */
        public Builder withPrice(final BigDecimal price) {
            this.builderPrice = price;
            return this;
        }

        /**
         * Sets the stock quantity.
         *
         * @param stockQuantity the stock quantity
         * @return the builder
         */
        public Builder withStockQuantity(final int stockQuantity) {
            this.builderStockQuantity = stockQuantity;
            return this;
        }

        /**
         * Sets the image URL.
         *
         * @param imageUrl the image URL
         * @return the builder
         */
        public Builder withImageUrl(final String imageUrl) {
            this.builderImageUrl = imageUrl;
            return this;
        }

        /**
         * Builds and returns the Car instance.
         *
         * @return the built Car object
         */
        public Car build() {
            return new Car(this);
        }
    }
}
