package com.hotmail.pederwaern.skinesis.controller;

import com.hotmail.pederwaern.skinesis.model.StreamData;
import com.hotmail.pederwaern.skinesis.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewStreamDataController {

    private final StreamService streamService;

    @Autowired
    public ViewStreamDataController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/data")
    public List<StreamData> getAllData() {
        return streamService.getAllStreamData();
    }

}
