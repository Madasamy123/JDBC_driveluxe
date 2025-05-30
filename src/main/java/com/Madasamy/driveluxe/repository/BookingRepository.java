package com.Madasamy.driveluxe.repository;

import com.Madasamy.driveluxe.model.Booking;
import com.Madasamy.driveluxe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    /**
     * Finds all bookings made by the specified user.
     *
     * @param user the user whose bookings are to be fetched
     * @return list of bookings by the user
     */
    List<Booking> findByUser(User user);
}
