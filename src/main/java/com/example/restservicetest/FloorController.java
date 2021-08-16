package com.example.restservicetest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class FloorController {

    Random random = new Random();
    int value = random.nextInt(Constants.MAX_NUM_OF_FLOORS + Constants.MIN_NUM_OF_FLOORS) + Constants.MIN_NUM_OF_FLOORS;

    Parking parking = new Parking(value);

    @GetMapping("/parking")
    public ResponseEntity<Object> getWholeParking() {
        Floor[] floors = parking.getFloors();
        return ResponseHandler.generateResponse("Successfully get whole parking floors!", HttpStatus.OK, floors);
    }

    @GetMapping("/parking/floor/{id}")
    public ResponseEntity<Object> getFloorById(@PathVariable(value = "id") int id) {
        Floor floor = parking.getFloor(id);
        return ResponseHandler.generateResponse("Successfully you get floor " + id, HttpStatus.OK, floor);
    }

    @PostMapping("/parking/car")
    public String addCarToProperFloor(@RequestParam(value = "height", defaultValue = "0") int h, @RequestParam(value = "weight", defaultValue = "0") int w) {
        return parking.addCarToParking(h, w);
    }

    @DeleteMapping("/parking/car/{carId}")
    public String exitCar(@PathVariable(value = "carId") int carId) {
        return parking.deleteCar(carId);
    }
}
