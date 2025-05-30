package com.Madasamy.driveluxe.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime bookingDate = LocalDateTime.now();

    @Column(nullable = true)
    private String imageUrl;

    public enum BookingStatus {
        SUBMITTED,
        CONFIRMED,
        CANCELLED,
        DELIVERED
    }

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus = BookingStatus.SUBMITTED;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    //  Foreign Key Mapping
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)

    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)

    private User user;

    //  Constructors
    public Booking() {}

    public Booking(Car car, User user, String customerName, String email, String phoneNumber, String address, String imageUrl) {
        this.car = car;
        this.user = user;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.bookingDate = LocalDateTime.now();
        this.imageUrl = imageUrl;
        this.bookingStatus = BookingStatus.SUBMITTED;
    }

    //  Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
