package id.ticket.concertManagement.service;

import id.ticket.concertManagement.entity.Ticket;
import id.ticket.concertManagement.models.ConcertModel;
import id.ticket.concertManagement.repository.TicketRepository;
import id.ticket.concertManagement.entity.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void generateTicket(ConcertModel model, Concert concert){
        int totalTicket = model.getTotalTicket();

        for (int row = 1; row <= totalTicket; row++) {
                Ticket ticket = new Ticket();
                ticket.setConcertId(concert.getId());

                ticketRepository.save(ticket);
        }
    }

}
