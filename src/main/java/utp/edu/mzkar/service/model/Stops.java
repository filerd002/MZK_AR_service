package utp.edu.mzkar.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "stops")
public class Stops {

    public Stops(Long stopID, String stopName, Double stopLat, Double stopLon) {
        this.stopID = stopID;
        this.stopName = stopName;
        this.stopLat = stopLat;
        this.stopLon = stopLon;

    }

    @Id
    @SequenceGenerator(name = "stops_sequence", allocationSize = 5, sequenceName = "stops_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stops_sequence")
    private Long id;

    @Column(name = "stop_id")
    private Long stopID;

    @Column(name = "stop_name")
    private String stopName;

    @Column(name = "stop_lat")
    private Double stopLat;

    @Column(name = "stop_lon")
    private Double stopLon;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stops_trips",
            joinColumns = @JoinColumn(name = "stops_id"),
            inverseJoinColumns = @JoinColumn(name = "trips_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"stops_id", "trips_id"})})

    @JsonIgnoreProperties("stops")
    List<Trips> trips = new ArrayList<> ();


}
