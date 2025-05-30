package com.Madasamy.driveluxe.repository;

import com.Madasamy.driveluxe.model.Booking;
import com.Madasamy.driveluxe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser(User user);
}
