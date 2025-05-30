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
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5502")
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    //  Get All Bookings
    @GetMapping
    public List<BookingDTO> getAllBookings() {

        List<Booking> bookings = bookingService.getAllBookings();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return bookings.stream().map(booking -> {
            Car car = booking.getCar();
            Integer carId = (car != null) ? car.getCarId() : null;

            String formattedDate = booking.getBookingDate().toLocalDate().toString();

            return new BookingDTO(
                    booking.getId(),
                    booking.getCustomerName(),
                    booking.getEmail(),
                    booking.getPhoneNumber(),
                    booking.getAddress(),
                    carId,
                    formattedDate,
                    booking.getImageUrl(),
                    booking.getBookingStatus() != null ? booking.getBookingStatus().name() : "SUBMITTED"
            );
        }).collect(Collectors.toList());
    }

    //  Get User-specific Bookings
    @GetMapping("/user")
    public List<BookingDTO> getUserBookings(@RequestHeader("Authorization") String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Unauthorized: No token provided");
        }

        token = token.substring(7); // Remove "Bearer " prefix
        String userEmail = jwtUtil.extractEmail(token);

        Optional<User> userOptional = userService.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Booking> bookings = bookingService.getUserBookings(user);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            return bookings.stream().map(booking -> {
                Car car = booking.getCar();
                Integer carId = (car != null) ? car.getCarId() : null;
                String formattedDate = booking.getBookingDate().toLocalDate().toString();

                return new BookingDTO(
                        booking.getId(),
                        booking.getCustomerName(),
                        booking.getEmail(),
                        booking.getPhoneNumber(),
                        booking.getAddress(),
                        carId,
                        formattedDate,
                        booking.getImageUrl(),
                        booking.getBookingStatus() != null ? booking.getBookingStatus().name() : "SUBMITTED"
                );
            }).collect(Collectors.toList());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    //  Create Booking
    @PostMapping
    public ResponseEntity<String> createBooking(
            @RequestBody BookingDTO bookingDTO,
            @RequestHeader("Authorization") String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Unauthorized: No token provided");
        }

        token = token.substring(7); // Remove "Bearer " prefix

        String userEmail = jwtUtil.extractEmail(token);

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
                return ResponseEntity.status(201).body("Booking successful!");
            } else {
                return ResponseEntity.status(403).body("Access Denied: Only users can book cars");
            }
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    //  Update Booking Status
    @PutMapping("/{id}/status")
    public Booking updateBookingStatus(
            @PathVariable int id,
            @RequestParam Booking.BookingStatus status,
            @RequestBody(required = false) Map<String, String> requestBody) {

        String reason = null;
        if (requestBody != null) {
            reason = requestBody.get("reason");
        }

        return bookingService.updateStatus(id, status, reason);
    }
}
