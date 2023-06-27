package com.bookingservice.repo;

import com.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {


    Optional<Booking> findByPaymentId(int paymentId);
}
