package project.java.projectdisastermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.java.projectdisastermanagement.model.User;

import java.time.LocalDateTime;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    default long countUsersLastMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        return countByCreatedAtAfter(oneMonthAgo);
    }

    long countByCreatedAtAfter(LocalDateTime createdAt);

}
