package com.hotmail.pederwaern.skinesis.service;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;

@Component
public class AwsKinesisClient {

    public KinesisClient getKinesisClient() {
        return KinesisClient.builder().region(Region.EU_NORTH_1).build();
    }
}
