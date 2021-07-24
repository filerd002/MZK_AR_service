package utp.edu.mzkar.service.model;

import lombok.Data;
import utp.edu.mzkar.service.model.emums.Period;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="trips")
public class Trips {

    public Trips(String routeID,String  tripID) {
        this.routeID = routeID;
        this.tripID = tripID;
    }

    @Id
    @SequenceGenerator(name = "trips_sequence", allocationSize = 5, sequenceName = "trips_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trips_sequence")
    private Long id;

    @Column(name = "route_id")
    private String routeID;

    @Transient
    private String tripID;

    @Column(name = "period")
    @Enumerated(EnumType.STRING)
    private Period period;

    @ManyToMany(mappedBy = "trips")
    private Set<Stops> stops = new HashSet<> ();

}
