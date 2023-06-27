package com.bookingservice.service;

import com.bookingservice.dto.TicketRequest;
import com.bookingservice.dto.TicketResponse;
import com.bookingservice.entity.Ticket;
import com.bookingservice.enums.TicketStatus;
import com.bookingservice.exception.TicketNotAvailableException;
import com.bookingservice.feignclient.MovieClient;
import com.bookingservice.repo.TicketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepo ticketRepo;

    private final MovieClient movieClient;
    @Override
    public Optional<Ticket> bookTicket(Ticket ticket) {

        Ticket ticket1= ticketRepo.findById(ticket.getTicketId()).orElseThrow(()->new TicketNotAvailableException("Ticket Not available with id"+ ticket.getTicketId()));

       if(ticket1!=null){
           Ticket ticket2=ticket;
           ticket2.setTicketPrice(ticket1.getTicketPrice());
           return Optional.of(ticketRepo.save(ticket2));
       }
       else{
           return null;
       }
    }

    @Override
    public List<TicketResponse> showAvailableTicket() {


        return ticketReponseList(ticketRepo.findAllByTicketStatus(TicketStatus.Available));
    }

    @Override
    public boolean addTicket(TicketRequest ticketRequest) {

        if(movieClient.getMovieById(ticketRequest.getMovieId())!=null){

            ticketRepo.save(toTicket(ticketRequest));
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TicketResponse findTicketById(int ticketId) {

        return toTicketResponse(ticketRepo.findById(ticketId).orElseThrow(()->new TicketNotAvailableException("not available.")));
    }

    public Ticket toTicket(TicketRequest ticketRequest){
        Ticket ticket=new Ticket();
        ticket.setTicketStatus(TicketStatus.Available);
        ticket.setTicketId(ticketRequest.getTicketId());
        ticket.setTicketPrice(ticketRequest.getTicketPrice());
        return ticket;
    }

    public TicketResponse toTicketResponse(Ticket ticket){

        TicketResponse ticketResponse=new TicketResponse();
        ticketResponse.setTicketId(ticket.getTicketId());
        ticketResponse.setTicketStatus(ticket.getTicketStatus().toString());
        ticketResponse.setMovieName(ticket.getMovieName());

        return ticketResponse;

    }

    public List<TicketResponse> ticketReponseList(List<Ticket> tickets){

        List<TicketResponse> ticketResponseList=new ArrayList<>();
        for(Ticket ticket:tickets){

           ticketResponseList.add(toTicketResponse(ticket));

        }
        return ticketResponseList;
    }
}
