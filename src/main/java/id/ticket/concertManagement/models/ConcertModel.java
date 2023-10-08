package id.ticket.concertManagement.models;

import id.ticket.concertManagement.entity.Concert;
import lombok.Data;

@Data
public class ConcertModel extends Concert {
    Integer totalTicket;
}
