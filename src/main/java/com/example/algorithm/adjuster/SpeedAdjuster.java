package com.example.algorithm.adjuster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpeedAdjuster {
    @Autowired
    private State state;
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedDelay = 1000L)
    public void adjust() {
        long adjustment = state.getAdjustment();
        post(adjustment);
        System.out.println("Now running : " + state.getRunningNow());
        System.out.println("Slow down by: " + adjustment + "ms");
    }

    private void post(long ms){
        restTemplate.postForObject(
                "http://localhost:8082/adjust-speed",
                ms,
                ResponseEntity.class);
    }
}
