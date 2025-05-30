package com.Madasamy.driveluxe.dto;

public class BookingDTO {
    private int id;
    private final String customerName;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final Integer carId;
    private final String bookingDate;
    private final String imageUrl;
    private final String bookingStatus;



    // Constructor to initialize all fields
    public BookingDTO(int id, String customerName, String email, String phoneNumber, String address, Integer carId, String bookingDate, String imageUrl, String bookingStatus) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.carId = carId;
        this.bookingDate = bookingDate;
        this.imageUrl = imageUrl;
        this.bookingStatus = bookingStatus;

    }


    // Getters for the fields (required for Spring to convert the object to JSON)
    public String getCustomerName() {
        return customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getCarId() {
        return carId;
    }

    public String getBookingDate() {
        return bookingDate;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }


}
