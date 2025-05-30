package com.Madasamy.driveluxe.service;

import com.Madasamy.driveluxe.model.Booking;
import com.Madasamy.driveluxe.model.Car;
import com.Madasamy.driveluxe.model.User;
import com.Madasamy.driveluxe.repository.BookingRepository;
import com.Madasamy.driveluxe.repository.DriveluxeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    /**
     * Repository for managing booking data.
     */
    private final BookingRepository bookingRepository;

    /**
     * Repository for managing Driveluxe-related data.
     */
    private final DriveluxeRepository driveluxeRepository;
    /**
     * Initializes the BookingService with the required repositories.
     *
     * @param bookingRepo the repository for managing booking data
     * @param driveluxeRepo the repository for managing Driveluxe data
     */
    public BookingService(
            final BookingRepository bookingRepo,
            final DriveluxeRepository driveluxeRepo) {
        this.bookingRepository = bookingRepo;
        this.driveluxeRepository = driveluxeRepo;
    }




    /**
     * Creates and saves a new booking.
     *
     * @param booking the booking object to be saved
     * @return the saved booking object
     */
    public Booking createBooking(final Booking booking) {
        return bookingRepository.save(booking);
    }
    /**
     * Retrieves all bookings from the repository.
     *
     * @return a list of all bookings
     */
    public final List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }




    // car booking

    /**
     * Saves a new booking with the specified customer and car details.
     *
     * @param carId        the ID of the car to be booked
     * @param customerName the name of the customer making the booking
     * @param email        the email address of the customer
     * @param phoneNumber  the phone number of the customer
     * @param address      the address of the customer
     * @param user         the user who is making the booking
     */
    public void saveBooking(
            final int carId,
           final  String customerName,
            final String email,
            final String phoneNumber,
            final String address,
           final  User user) {

        Car car = driveluxeRepository.findById(carId).orElse(null);
        if (car != null) {
            String imageUrl = car.getImageUrl();
            if (imageUrl != null) {
                //  Add the missing `User` object to the constructor
                Booking booking = new Booking(
                        car,
                        user,
                        customerName,
                        email,
                        phoneNumber,
                        address,
                        imageUrl
                );
                booking.setBookingStatus(Booking.BookingStatus.SUBMITTED);
                bookingRepository.save(booking);
                System.out.println("Booking saved with image URL: " + imageUrl);
            } else {
                System.out.println("Image URL is null for car ID: " + carId);
            }
        } else {
            System.out.println("Car not found with ID: " + carId);
        }
    }


    //  update booking status

//    public Booking updateStatus(int id, Booking.BookingStatus status) {
//        Optional<Booking> optionalBooking = bookingRepository.findById(id);
//        if (optionalBooking.isPresent()) {
//            Booking booking = optionalBooking.get();
//            booking.setBookingStatus(status);
//            return bookingRepository.save(booking);
//        }
//        return null;
//    }

    /**
     * Retrieves all bookings made by the specified user.
     *
     * @param user the user whose bookings are to be fetched
     * @return a list of bookings associated with the given user
     */
    public List<Booking> getUserBookings(final User user) {
        return bookingRepository.findByUser(user);
    }





    // cancellation

    /**
     * Updates the status of a booking identified by its ID.
     *
     * @param id     the ID of the booking to update
     * @param status the new status to set for the booking
     * @param reason the reason for updating the status
     * @return the updated Booking object
     */
    public Booking updateStatus(
            final int id,
            final Booking.BookingStatus status,
            final String reason) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setBookingStatus(status);
            if (status == Booking.BookingStatus.CANCELLED && reason != null) {
                booking.setCancellationReason(reason);
            }
            return bookingRepository.save(booking);
        }
        return null;
    }






}
