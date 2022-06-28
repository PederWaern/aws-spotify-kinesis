package com.hotmail.pederwaern.skinesis.controller;

import com.hotmail.pederwaern.skinesis.model.StreamData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewStreamDataController {

    @GetMapping("/data")
    public List<StreamData> getAllData() {
        return List.of(new StreamData("hello world"));
    }

}
