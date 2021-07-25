package utp.edu.mzkar.service.mapper;

import com.google.common.base.Splitter;
import utp.edu.mzkar.service.model.StopTimes;
import utp.edu.mzkar.service.model.Stops;
import utp.edu.mzkar.service.model.Trips;
import utp.edu.mzkar.service.model.emums.Period;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mapper {


    public static List<Stops> aggregation(List<Stops> stops, List<Trips> trips, List<StopTimes> stopTimes) {

        stops.stream ().forEach ( stop -> {
            stopTimes.stream ().filter ( stopS -> stopS.getStopID ().equals ( stop.getStopID () ) )
                    .collect ( Collectors.toList () )
                    .stream ().forEach ( times -> {
                stop.getTrips ().addAll ( trips
                        .stream ().filter ( trip -> trip.getTripID ().equals ( times.getTripID () ) )
                        .peek ( trips1 -> {
                            Arrays.stream ( Period.values () )
                                    .forEach ( v -> {
                                        if ( trips1.getTripID ().contains ( v.getValue () ) ) {
                                            trips1.setPeriod ( v );
                                        }
                                    } );
                        } )
                        .collect ( Collectors.toList () )
                        .stream ().filter ( trips1 -> trips1.getPeriod ().equals ( Period.currentPeriod()) && stop.getTrips ()
                                .stream ().filter ( trips2 -> (trips1.getRouteID ().equals ( trips2.getRouteID () )
                                        && trips1.getPeriod ().equals ( trips2.getPeriod () )) )
                                .collect ( Collectors.toList () ).isEmpty ()
                        )
                        .collect ( Collectors.toList () )
                );
            } );
        } );

        return stops;
    }





    public static Function<String, Trips> mapLineToTrips = line -> {
        List<String> tripFields = Splitter.on ( "," ).trimResults ()
                .omitEmptyStrings ().splitToList ( line );
        Trips trips = new Trips ( tripFields.get ( 0 ), tripFields.get ( 2 ) );
        return trips;
    };

    public static Function<String, StopTimes> mapLineToStopTimes = line -> {
        List<String> stopTimesFields = Splitter.on ( "," ).trimResults ()
                .omitEmptyStrings ().splitToList ( line );
        StopTimes stopTimes = new StopTimes ( stopTimesFields.get ( 0 ), Long.valueOf ( stopTimesFields.get ( 3 ) ) );
        return stopTimes;
    };

    public static Function<String, Stops> mapLineToStop = line -> {
        List<String> stopsFields = Splitter.on ( "," ).trimResults ()
                .omitEmptyStrings ().splitToList ( line );
        Stops stop = new Stops ( Long.valueOf ( stopsFields.get ( 0 ) ), stopsFields.get ( 1 ), Double.valueOf ( stopsFields.get ( 2 ) ), Double.valueOf ( stopsFields.get ( 3 ) ) );
        return stop;
    };

}
