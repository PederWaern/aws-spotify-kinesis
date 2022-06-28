package com.hotmail.pederwaern.skinesis.model;

import lombok.Data;

import java.time.Instant;

@Data
public class StreamData {
    private String content;
    private Instant time;

    public StreamData(String content) {
        this.content = content;
        this.time = Instant.now();
    }
}
