package com.example.restservicetest;

import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class FloorController {

    Random random = new Random();
    int value = random.nextInt(Constants.MAX_NUM_OF_FLOORS + Constants.MIN_NUM_OF_FLOORS) + Constants.MIN_NUM_OF_FLOORS;

    Parking parking = new Parking(value);

    @GetMapping("/parking")
    public Floor[] getWholeParking(){
        return parking.getFloors();
    }

    @GetMapping("/parking/floor/{id}")
    public Floor getFloorById(@PathVariable(value = "id") int id){
        return parking.getFloor(id);
    }

    @PostMapping("/parking/car")
    public String addCarToProperFloor(@RequestParam(value = "height", defaultValue = "0") int h, @RequestParam(value = "weight", defaultValue = "0") int w){
        return parking.addCarToParking(h,w);
    }

    @DeleteMapping("/parking/car/{carId}")
    public String exitCar(@PathVariable(value = "carId") int carId){
        return parking.deleteCar(carId);
    }
}
