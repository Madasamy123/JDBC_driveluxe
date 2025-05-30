package com.Madasamy.driveluxe.controller;

import com.Madasamy.driveluxe.Util.JwtUtil;
import com.Madasamy.driveluxe.dto.BookingDTO;
import com.Madasamy.driveluxe.model.Booking;
import com.Madasamy.driveluxe.model.Car;
import com.Madasamy.driveluxe.model.User;
import com.Madasamy.driveluxe.service.BookingService;
import com.Madasamy.driveluxe.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller to handle booking-related API endpoints.
 */
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5502")
@RequestMapping("/api/bookings")
public class BookingController {

    /** Booking service. */
    @Autowired
    private BookingService bookingService;

    /** JWT utility. */
    @Autowired
    private JwtUtil jwtUtil;

    /** User service. */
    @Autowired
    private UserService userService;

    /** Length of the 'Bearer ' token prefix. */
    private static final int TOKEN_PREFIX_LENGTH = 7;

    /** HTTP 401 Unauthorized. */
    private static final int HTTP_UNAUTHORIZED = 401;

    /** HTTP 201 Created. */
    private static final int HTTP_CREATED = 201;

    /** HTTP 403 Forbidden. */
    private static final int HTTP_FORBIDDEN = 403;

    /** HTTP 404 Not Found. */
    private static final int HTTP_NOT_FOUND = 404;

    /**
     * Retrieve all bookings.
     *
     * @return List of booking DTOs
     */
    @GetMapping
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return bookings.stream().map(booking -> {
            Car car = booking.getCar();
            Integer carId = (car != null) ? car.getCarId() : null;
            String formattedDate = booking
                    .getBookingDate()
                    .toLocalDate()
                    .format(formatter);

            return new BookingDTO(
                    booking.getId(),
                    booking.getCustomerName(),
                    booking.getEmail(),
                    booking.getPhoneNumber(),
                    booking.getAddress(),
                    carId,
                    formattedDate,
                    booking.getImageUrl(),
                    booking.getBookingStatus() != null
                            ? booking.getBookingStatus().name()
                            : "SUBMITTED"
            );
        }).collect(Collectors.toList());
    }

    /**
     * Retrieve bookings for the authenticated user.
     *
     * @param token JWT bearer token
     * @return List of user's bookings
     */
    @GetMapping("/user")
    public List<BookingDTO> getUserBookings(
            @RequestHeader("Authorization") final String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Unauthorized: No token provided");
        }

        String trimmedToken = token.substring(TOKEN_PREFIX_LENGTH);
        String userEmail = jwtUtil.extractEmail(trimmedToken);
        Optional<User> userOptional = userService.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Booking> bookings = bookingService.getUserBookings(user);
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");

            return bookings.stream().map(booking -> {
                Car car = booking.getCar();
                Integer carId = (car != null) ? car.getCarId() : null;
                String formattedDate = booking
                        .getBookingDate()
                        .toLocalDate()
                        .format(formatter);

                return new BookingDTO(
                        booking.getId(),
                        booking.getCustomerName(),
                        booking.getEmail(),
                        booking.getPhoneNumber(),
                        booking.getAddress(),
                        carId,
                        formattedDate,
                        booking.getImageUrl(),
                        booking.getBookingStatus() != null
                                ? booking.getBookingStatus().name()
                                : "SUBMITTED"
                );
            }).collect(Collectors.toList());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    /**
     * Create a new booking.
     *
     * @param bookingDTO Booking details
     * @param token      JWT bearer token
     * @return HTTP response
     */
    @PostMapping
    public ResponseEntity<String> createBooking(
            @RequestBody final BookingDTO bookingDTO,
            @RequestHeader("Authorization") final String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity
                    .status(HTTP_UNAUTHORIZED)
                    .body("Unauthorized: No token provided");
        }

        String trimmedToken = token.substring(TOKEN_PREFIX_LENGTH);
        String userEmail = jwtUtil.extractEmail(trimmedToken);
        Optional<User> userOptional = userService.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if ("USER".equals(user.getRole())) {
                bookingService.saveBooking(
                        bookingDTO.getCarId(),
                        bookingDTO.getCustomerName(),
                        bookingDTO.getEmail(),
                        bookingDTO.getPhoneNumber(),
                        bookingDTO.getAddress(),
                        user
                );
                return ResponseEntity
                        .status(HTTP_CREATED)
                        .body("Booking successful!");
            } else {
                return ResponseEntity
                        .status(HTTP_FORBIDDEN)
                        .body("Access Denied: Only users can book cars");
            }
        } else {
            return ResponseEntity
                    .status(HTTP_NOT_FOUND)
                    .body("User not found");
        }
    }

    /**
     * Update booking status.
     *
     * @param id          Booking ID
     * @param status      Booking status
     * @param requestBody Map containing 'reason'
     * @return Updated booking
     */
    @PutMapping("/{id}/status")
    public Booking updateBookingStatus(
            @PathVariable final int id,
            @RequestParam final Booking.BookingStatus status,
            @RequestBody(required = false)
            final Map<String, String> requestBody) {

        String reason = null;
        if (requestBody != null) {
            reason = requestBody.get("reason");
        }

        return bookingService.updateStatus(id, status, reason);
    }
}
