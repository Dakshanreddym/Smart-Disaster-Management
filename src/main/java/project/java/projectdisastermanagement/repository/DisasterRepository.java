package project.java.projectdisastermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.java.projectdisastermanagement.model.Disaster;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface DisasterRepository extends JpaRepository<Disaster, Long> {
    default long countReportsLastMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        return countByCreatedAtAfter(oneMonthAgo);
    }

    long countByCreatedAtAfter(LocalDateTime createdAt);

    @Query(value = "SELECT disaster_type, COUNT(*) AS total FROM disaster_reports GROUP BY disaster_type", nativeQuery = true)
    List<Object[]> countByDisasterType();

    @Query(value = "SELECT status, COUNT(*) AS total FROM disaster_reports GROUP BY status", nativeQuery = true)
    List<Object[]> countByStatus();

    List<Disaster> findTop5ByOrderByCreatedAtDesc();

    List<Disaster> findByUsername(String username);

}
