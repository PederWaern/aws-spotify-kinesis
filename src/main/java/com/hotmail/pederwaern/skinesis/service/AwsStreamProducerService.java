package com.hotmail.pederwaern.skinesis.service;

import com.hotmail.pederwaern.skinesis.model.StreamData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AwsStreamProducerService implements StreamService {

    private final List<StreamData> allStreamData;

    public AwsStreamProducerService(List<StreamData> allStreamData) {
        this.allStreamData = allStreamData;
    }

    @Override
    @Scheduled(fixedRate = 5000L)
    public void addToStream() {
        System.out.println("Hello from scheduled");
    }
}
