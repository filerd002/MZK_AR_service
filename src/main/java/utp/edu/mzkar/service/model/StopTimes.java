package utp.edu.mzkar.service.model;

import lombok.Data;

@Data
public class StopTimes {

    public StopTimes(String tripID,Long stopID) {
        this.tripID = tripID;
        this.stopID = stopID;
    }

    private String tripID;

    private Long stopID;


}
