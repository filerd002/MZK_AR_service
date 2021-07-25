package utp.edu.mzkar.service.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import utp.edu.mzkar.service.model.Stops;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "stops", path = "stops")
public interface StopsRepository extends PagingAndSortingRepository<Stops, Long> {

    List<Stops> findByStopLatBetweenAndStopLonBetween(@Param("stopLatLeft") Double stopLatLeft, @Param("stopLatRight") Double stopLatRight, @Param("stopLonDown") Double stopLonDown,  @Param("stopLonUp") Double stopLonUp);

    @Modifying
    @Query(value = "TRUNCATE TABLE Stops CASCADE; ",
            nativeQuery = true
    )
    void deleteAll();

}
