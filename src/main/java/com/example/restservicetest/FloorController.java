package com.example.restservicetest;

import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class FloorController {
    int min = 1;
    int max = 10;

    Random random = new Random();
    int value = random.nextInt(max + min) + min;

    Parking parking = new Parking(value);

    @GetMapping("/parking/floor/{id}")
    public Floor floor(@PathVariable(value = "id") int id){
        return parking.getFloor(id);
    }

    @GetMapping("/parking")
    public Floor[] parking(){
        return parking.getFloors();
    }

    @PutMapping("/floor")
    public String newFloor(@RequestParam(value = "height", defaultValue = "0") int h, @RequestParam(value = "weight", defaultValue = "0") int w){
        return parking.addCarToParking(h,w);
    }
}
