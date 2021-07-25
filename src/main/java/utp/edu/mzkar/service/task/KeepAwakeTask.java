package utp.edu.mzkar.service.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeepAwakeTask {

    @Value("${app.address}")
    private String appAdress;

    private final RestTemplate restTemplate;

    public KeepAwakeTask(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Scheduled(fixedRate = 1800000)
    public String KeepAwakeApp(){
        return this.restTemplate.getForObject(appAdress, String.class);


    }


}
