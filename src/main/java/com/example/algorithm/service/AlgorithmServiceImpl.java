package com.example.algorithm.service;

import com.example.algorithm.model.Photo;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlgorithmServiceImpl implements AlgorithmService{
    @Async
    @Override
    @SneakyThrows
    public void run(Photo photo) {
        Thread.sleep(6000000L);
        Optional<String> optional = Optional.ofNullable(photo.getPhoto());
        //optional.ifPresent(System.out::println);
    }
}
