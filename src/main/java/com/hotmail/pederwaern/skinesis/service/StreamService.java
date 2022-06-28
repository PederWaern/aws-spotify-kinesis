package com.hotmail.pederwaern.skinesis.service;

import com.hotmail.pederwaern.skinesis.model.StreamData;

import java.util.List;

public interface StreamService {
    void addToStream();
    List<StreamData> getAllStreamData();
}
