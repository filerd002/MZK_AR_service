package utp.edu.mzkar.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.mzkar.service.model.Stops;

@Repository
public interface StopsRepository extends JpaRepository<Stops, Long> {
}
