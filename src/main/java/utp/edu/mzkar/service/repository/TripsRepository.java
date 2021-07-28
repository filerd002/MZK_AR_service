package utp.edu.mzkar.service.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import utp.edu.mzkar.service.model.Trips;


@RepositoryRestResource(exported = false)
public interface TripsRepository extends PagingAndSortingRepository<Trips, Long> {

    @Modifying
    @Query(value = "TRUNCATE TABLE Trips CASCADE; ",
            nativeQuery = true
    )
    void deleteAll();

}
