package utp.edu.mzkar.service.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="stops")
public class Stops {

    public Stops(Long stopID, String stopName, String stopLat, String stopLon) {
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
    private String stopLat;

    @Column(name = "stop_lon")
    private String stopLon;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "stops_trips",
            joinColumns = { @JoinColumn(name = "stops_id") },
            inverseJoinColumns = { @JoinColumn(name = "trips_id") }
    )
    Set<Trips> trips = new  HashSet<> ();



}
