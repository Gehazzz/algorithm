package com.example.algorithm;

import com.example.algorithm.adjuster.State;
import com.example.algorithm.model.Photo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlgorithmApplication.class)
@AutoConfigureMockMvc
public class ApiTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private State state;

    private Photo photo;

    @Before
    public void init(){
        photo = new Photo();
        photo.setPhoto("photo");
    }

    @Test
    public void speedAdjustmentTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(photo);
        long sleep = 0;
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(sleep);
            mvc.perform(post("/photo/process")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json));
            sleep = sleep + state.getAdjustment();
            sleep = (sleep < 0) ? 0 : sleep;
            if (sleep != 0)
                System.out.println("Sending photo every - " + sleep + " ms");
        }
    }
}
