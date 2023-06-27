package com.bookingservice.repo;

import com.bookingservice.entity.Ticket;
import com.bookingservice.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Integer> {


    List<Ticket> findAllByTicketStatus(TicketStatus ticketStatus);

}
