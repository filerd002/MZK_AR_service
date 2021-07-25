package utp.edu.mzkar.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.mzkar.service.model.emums.Period;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "trips")
public class Trips {

    public Trips(String routeID, String tripID) {
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stops_trips",
            joinColumns = @JoinColumn(name = "trips_id"),
            inverseJoinColumns = @JoinColumn(name = "stops_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"trips_id", "stops_id"})})
    @JsonIgnoreProperties("trips")
    private List<Stops> stops = new ArrayList<> ();

}
