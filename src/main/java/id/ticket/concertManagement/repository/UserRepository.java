package id.ticket.concertManagement.repository;

import id.ticket.concertManagement.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query(nativeQuery = true, value = "FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    User findOneByUsername(@Param("username") String username);
}
