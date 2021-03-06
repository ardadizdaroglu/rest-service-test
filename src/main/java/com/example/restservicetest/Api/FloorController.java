package com.example.restservicetest.Api;

import com.example.restservicetest.Configs.ResponseHandler;
import com.example.restservicetest.Utility.Constants;
import com.example.restservicetest.Models.Floor;
import com.example.restservicetest.Models.Parking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
public class FloorController {

    private final Random random = new Random();
    private final int value = random.nextInt(Constants.MAX_NUM_OF_FLOORS - Constants.MIN_NUM_OF_FLOORS + 1) + Constants.MIN_NUM_OF_FLOORS;

    private final Parking parking = new Parking(value);

    @GetMapping("/api/parking")
    public ResponseEntity<Object> getWholeParking() {
        Floor[] floors = parking.getFloors();
        return ResponseHandler.generateResponse("Successfully you get whole parking floors!", HttpStatus.OK, floors);
    }

    @GetMapping("/api/parking/floor/{id}")
    public ResponseEntity<Object> getFloorById(@PathVariable(value = "id") int id) {
        Floor floor = parking.getFloor(id);
        return ResponseHandler.generateResponse("Successfully you get floor " + id, HttpStatus.OK, floor);
    }

    @PostMapping("/api/parking/car")
    public ResponseEntity<Object> addCarToProperFloor(@RequestParam(value = "height", defaultValue = "0") int h, @RequestParam(value = "weight", defaultValue = "0") int w) {
        Map<String, Object> resultMap = parking.addCarToParking(h, w);
        if(resultMap.get("status") == HttpStatus.OK){
            return ResponseHandler.generateResponse("Successfully a car entered to parking.", HttpStatus.CREATED, resultMap.get("data"));
        }
        else{
            return ResponseHandler.generateResponse("This car can't be added anywhere.", HttpStatus.NOT_ACCEPTABLE, resultMap);
        }
    }

    @DeleteMapping("/api/parking/car/{carId}")
    public ResponseEntity<Object> exitCar(@PathVariable(value = "carId") int carId) {
        Map<String, Object> resultMap = parking.deleteCar(carId);
        if(resultMap.get("status") == HttpStatus.OK){
            return ResponseHandler.generateResponse("Successfully a car left from parking.", HttpStatus.OK, resultMap);
        }
        else{
            return ResponseHandler.generateResponse("This car isn't existed.", HttpStatus.NOT_ACCEPTABLE, resultMap);
        }
    }
}
