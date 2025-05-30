package com.Madasamy.driveluxe.model;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    /**
     * Primary key identifier for the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Hidden
    private int id;
    /**
     * customerName.
     */
    private String customerName;
    /**
     * User's email address.
     */
    private String email;

    /**
     * User's phone number.
     */
    private String phoneNumber;

    /**
     * User's physical address.
     */
    private String address;

    /**
     * Date and time when the booking was made. Defaults to current time.
     */
    private LocalDateTime bookingDate = LocalDateTime.now();

    /**
     * URL of the image associated with the entity. Can be null.
     */
    @Column(nullable = true)
    private String imageUrl;

    public enum BookingStatus {

        /**
         * Booking has been submitted by the user but not yet confirmed.
         */
        SUBMITTED,

        /**
         * Booking has been confirmed.
         */
        CONFIRMED,

        /**
         * Booking was cancelled by the user or system.
         */
        CANCELLED,

        /**
         * Booking was delivered or completed.
         */
        DELIVERED
    }

    /**
     * Current status of the booking.
     * Stored as a string in the database. Defaults to SUBMITTED.
     */
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus = BookingStatus.SUBMITTED;

    /**
     * Reason provided for booking cancellation.
     */
    @Column(name = "cancellation_reason")
    private String cancellationReason;

    //  Foreign Key Mapping
    /**
     * The car associated with the booking.
     */
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)

    private Car car;

    /**
     * The user who made the booking.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)

    private User user;
    /**
     * Constructs a new Booking with the given details.
     *
     * @param bookingCar the car being booked
     * @param bookingUser the user making the booking
     * @param bookingcustomerName the name of the customer
     * @param bookingemail the customer's email address
     * @param bookingphoneNumber the customer's phone number
     * @param bookingaddress the customer's address
     * @param bookingimageUrl optional image URL for the booking
     */
    public Booking(
            final Car bookingCar,
            final User bookingUser,
            final String bookingcustomerName,
            final String bookingemail,
            final String bookingphoneNumber,
            final String bookingaddress,
            final String bookingimageUrl) {

        this.car = bookingCar;
        this.user = bookingUser;
        this.customerName = bookingcustomerName;
        this.email = bookingemail;
        this.phoneNumber = bookingphoneNumber;
        this.address = bookingaddress;
        this.bookingDate = LocalDateTime.now();
        this.imageUrl = bookingimageUrl;
        this.bookingStatus = BookingStatus.SUBMITTED;
    }



    /**
     * Gets the booking ID.
     *
     * @return the ID of the booking
     */
    public final int getId() {
        return id;
    }

    /**
     * Sets the booking ID.
     *
     * @param id the ID to set
     */

    public final void setId(final int id) {
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
