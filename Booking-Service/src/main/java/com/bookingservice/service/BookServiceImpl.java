package com.bookingservice.service;

import com.bookingservice.dto.BookingResponse;
import com.bookingservice.dto.Email;
import com.bookingservice.dto.RequestBooking;
import com.bookingservice.entity.Booking;
import com.bookingservice.entity.Ticket;
import com.bookingservice.enums.BookingStatus;
import com.bookingservice.enums.TicketStatus;
import com.bookingservice.exception.TicketAlreadyBookedWithThisPaymentException;
import com.bookingservice.exception.TicketNotAvailableException;
import com.bookingservice.feignclient.MailServer;
import com.bookingservice.feignclient.MovieClient;
import com.bookingservice.feignclient.PaymentClient;
import com.bookingservice.feignclient.UserClient;
import com.bookingservice.repo.BookingRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;

    private final MovieClient movieClient;

    private final MailServer mailServer;

    private final UserClient userClient;

    private final PaymentClient paymentClient;

    private final TicketService ticketService;


    @Override
    @Transactional
    public BookingResponse requestBooking(RequestBooking requestBooking) {

        if (movieClient.getMovieById(requestBooking.getMovieId()) != null && paymentClient.checkPayment(requestBooking.getPaymentId()).getBody().getPaymentStatus().equals("SUCCESSFUL")) {

            if (ticketService.findTicketById(requestBooking.getTicketId()) == null) {

                throw new TicketNotAvailableException("Ticket with id " + requestBooking.getTicketId() + " not available.");

            } else if (bookingRepo.findByPaymentId(requestBooking.getPaymentId()).isPresent()) {

                throw new TicketAlreadyBookedWithThisPaymentException("Ticket already booked with this payment id" + requestBooking.getPaymentId());
            } else {
                Booking booking = bookingRepo.save(toBooking(requestBooking));
                ticketService.bookTicket(toTicket(requestBooking, booking.getBookingId()));
                Email email = new Email();
                email.setTo(userClient.findUserByUsername(requestBooking.getUsername()).getEmail());
                email.setSubject("Book Ticket");
                email.setBody("Your have booked ticket for movie" + requestBooking.getMovieName());
                email.setCc("rupeshsunuwar2019@gmail.com");
                mailServer.sendEmail(email);


                return toResponseBooking(booking);
            }

        }

        return null;
    }


    public BookingResponse toResponseBooking(Booking booking) {

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setBookingDate(new Date());
        bookingResponse.setBookingStatus("Booked");
        bookingResponse.setTicketId(booking.getTicketId());

        return bookingResponse;

    }

    public Booking toBooking(RequestBooking requestBooking) {

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.Confirmed);
        booking.setPaymentId(requestBooking.getPaymentId());
        booking.setUserName(requestBooking.getUsername());
        booking.setTicketId(requestBooking.getTicketId());
        return booking;
    }

    public Ticket toTicket(RequestBooking requestBooking, int bookingId) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(requestBooking.getTicketId());
        ticket.setBookingId(bookingId);
        ticket.setMovieName(requestBooking.getMovieName());
        ticket.setTicketStatus(TicketStatus.Booked);

        return ticket;
    }

}
