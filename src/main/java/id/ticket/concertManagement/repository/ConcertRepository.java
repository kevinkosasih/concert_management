package id.ticket.concertManagement.repository;

import id.ticket.concertManagement.entity.Concert;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConcertRepository extends PagingAndSortingRepository<Concert,Long>, JpaSpecificationExecutor<Concert> {
}
