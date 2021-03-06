package utp.edu.mzkar.service.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.mzkar.service.Utils.ZipUtils;
import utp.edu.mzkar.service.mapper.Mapper;
import utp.edu.mzkar.service.model.StopTimes;
import utp.edu.mzkar.service.model.Stops;
import utp.edu.mzkar.service.model.Trips;
import utp.edu.mzkar.service.repository.StopsRepository;
import utp.edu.mzkar.service.repository.TripsRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImportTask {

    @Value("${geolocation.data}")
    private String geolocationDataImportURL;

    @Value("${targetDir.importFiles}")
    private String targetDirImportFiles;

    @Value("${geolocation.data.file.stops}")
    private String geolocationDataFileStops;

    @Value("${geolocation.data.file.trips}")
    private String geolocationDataFileTrips;

    @Value("${geolocation.data.file.stopTimes}")
    private String geolocationDataFileStopTimes;

    private StopsRepository stopsRepository;

    private TripsRepository tripsRepository;

    public ImportTask(StopsRepository stopsRepository, TripsRepository tripsRepository) {
        this.stopsRepository = stopsRepository;
        this.tripsRepository = tripsRepository;
    }

    @Transactional
    @Scheduled(cron = "0 0 1  * * *")
    public void importData() {
        try {
            ZipUtils.downloadArchive ( new URL ( geolocationDataImportURL ), new File ( targetDirImportFiles ) );
            stopsRepository.deleteAll ();
            tripsRepository.deleteAll ();
            stopsRepository.saveAll ( processFiles () );
        } catch (MalformedURLException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public List<Stops> processFiles() throws IOException {
        String stopsFile = targetDirImportFiles + geolocationDataFileStops;
        String tripsFile = targetDirImportFiles + geolocationDataFileTrips;
        String stopTimesFile = targetDirImportFiles +geolocationDataFileStopTimes;

        List<Stops> stops = Files.lines ( Paths.get ( stopsFile ) ).skip ( 1 ).map ( Mapper.mapLineToStop ).collect ( Collectors.toList () );
        List<Trips> trips = Files.lines ( Paths.get ( tripsFile ) ).skip ( 1 ).map ( Mapper.mapLineToTrips ).collect ( Collectors.toList () );
        List<StopTimes> stopTimes = Files.lines ( Paths.get ( stopTimesFile ) ).skip ( 1 ).map ( Mapper.mapLineToStopTimes ).collect ( Collectors.toList () );

        return Mapper.aggregation ( stops, trips, stopTimes );

    }
}
