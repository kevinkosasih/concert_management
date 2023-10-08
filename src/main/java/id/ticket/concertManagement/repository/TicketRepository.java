package id.ticket.concertManagement.repository;

import id.ticket.concertManagement.entity.Ticket;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TicketRepository extends PagingAndSortingRepository<Ticket,Long>, JpaSpecificationExecutor<Ticket> {
}
