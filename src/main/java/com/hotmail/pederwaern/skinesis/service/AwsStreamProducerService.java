package com.hotmail.pederwaern.skinesis.service;

import com.hotmail.pederwaern.skinesis.model.StreamData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Component
public class AwsStreamProducerService implements StreamService {

    public static final String SPOTIFY_KINESIS_STREAM = "spotify-kinesis-stream";
    private final AwsKinesisClient awsKinesisClient;
    private final SpotifyAPIService spotifyAPIService;

    public AwsStreamProducerService(AwsKinesisClient awsKinesisClient, SpotifyAPIService spotifyAPIService) {
        this.awsKinesisClient = awsKinesisClient;
        this.spotifyAPIService = spotifyAPIService;
        this.foundImageUrls = new HashSet<>();
    }

    private final List<StreamData> allStreamData = new ArrayList<>();
    private final Set<String> foundImageUrls;

    @Override
    @Scheduled(fixedRate = 1000L * 30)
    public void addToStream() {
        // Get the first image url
        final String imageUrl = spotifyAPIService.getRecentImageUrl();

        if (imageUrl != null && !foundImageUrls.contains(imageUrl)) {
            final byte[] urlByteArray = imageUrl.getBytes(StandardCharsets.UTF_8);
            PutRecordRequest request =
                    PutRecordRequest.builder().streamName(SPOTIFY_KINESIS_STREAM)
                            .data(SdkBytes.fromByteArray(urlByteArray))
                            .partitionKey(SPOTIFY_KINESIS_STREAM)
                            .build();
            foundImageUrls.add(imageUrl);
            addToKinesis(imageUrl, urlByteArray, request);
        }
    }

    private void addToKinesis(String imageUrl, byte[] urlByteArray, PutRecordRequest request) {
        try (KinesisClient client = awsKinesisClient.getKinesisClient()) {
            client.putRecord(request);
            StreamData streamData = new StreamData(imageUrl);
            allStreamData.add(streamData);
        } catch (Exception e) {
            log.warn("Failed to write to kinesis for message: " + new String(urlByteArray));
            throw e;
        }
    }
    @Override
    public List<StreamData> getAllStreamData() {
        return getAllStreamDataEntries();
    }

    public List<StreamData> getAllStreamDataEntries() {
        return Collections.unmodifiableList(allStreamData);
    }
}
