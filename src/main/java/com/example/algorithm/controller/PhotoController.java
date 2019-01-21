package com.example.algorithm.controller;

import com.example.algorithm.model.Photo;
import com.example.algorithm.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private AlgorithmService algorithmService;

    @PostMapping("/process")
    @ResponseStatus(value = HttpStatus.OK)
    public void processPhoto(@RequestBody Photo photo) {
        algorithmService.run(photo);
    }
}
