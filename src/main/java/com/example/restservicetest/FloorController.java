package com.example.restservicetest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class FloorController {
    int min = 1;
    int max = 10;

    Random random = new Random();
    int value = random.nextInt(max + min) + min;

    Floor[] floors = new Floor[value];

    public FloorController() {

        int min_height = 1;
        int max_height = 10;
        int min_weight = 1;
        int max_weight = 10;

        for(int i = 0; i< value; i++){
            floors[i] = new Floor(random.nextInt(max_height + min_height) + min_height,
                                    random.nextInt(max_weight + min_weight) + min_weight);
        }
    }

    @GetMapping("/floor")
    public Floor floor(@RequestParam(value = "id", defaultValue = "0") int id){
        return floors[id];
    }
}
