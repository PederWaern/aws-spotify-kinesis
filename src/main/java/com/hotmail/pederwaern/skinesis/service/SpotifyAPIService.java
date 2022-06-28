package com.hotmail.pederwaern.skinesis.service;


import com.neovisionaries.i18n.CountryCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.browse.GetListOfNewReleasesRequest;

import java.io.IOException;
import java.util.Random;

@Slf4j
@Service
public class SpotifyAPIService {
    private static final String CLIENT_ID =  System.getenv("SPOTIFY_ID");
    private static final String CLIENT_SECRET = System.getenv("SPOTIFY_SECRET");

    public SpotifyApi getApi() {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .build();
        ClientCredentialsRequest req = spotifyApi.clientCredentials().build();
        try {
            final ClientCredentials clientCredentials = req.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            log.warn("Failed to get spotify client credentials");
        }

        return spotifyApi;
    }

    public String getRecentImageUrl() {
        GetListOfNewReleasesRequest req = getApi().getListOfNewReleases()
                .limit(10)
                .offset(getRandomNumberOffset())
                .country(CountryCode.SE)
                .build();

        Paging<AlbumSimplified> res = null;
        try {
            res = req.execute();
            return res.getItems()[0].getImages()[0].getUrl();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            log.warn("Error requesting spotify");
            throw new RuntimeException(e);
        }
    }

    private int getRandomNumberOffset() {
        Random random = new Random();
        int min = 1;
        int max = 20;
        return random.nextInt(max + min) + min;
    }
}
