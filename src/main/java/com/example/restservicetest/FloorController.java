package com.example.restservicetest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class FloorController {
    int min = 1;
    int max = 10;

    Random random = new Random();
    int value = random.nextInt(max + min) + min;

    Parking parking = new Parking(value);

    @GetMapping("/floor")
    public Floor floor(@RequestParam(value = "id", defaultValue = "0") int id){
        return parking.getFloor(id);
    }

    @GetMapping("/parking")
    public Floor[] parking(){
        return parking.getFloors();
    }

    @PutMapping("/floor")
    public void newFloor(@RequestParam(value = "height", defaultValue = "0") int h, @RequestParam(value = "weight", defaultValue = "0") int w){
        parking.addCarToParking(h,w);
    }
}
