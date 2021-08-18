package com.example.restservicetest.Models;

import com.example.restservicetest.Utility.Constants;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Parking {

    private final Floor[] floors;
    private final ArrayList<Car> cars = new ArrayList<>();

    public Parking(int numOfFloors) {
        floors = new Floor[numOfFloors];

        Random random = new Random();
        for (int i = 0; i < numOfFloors; i++) {
            floors[i] = new Floor(i,
                    random.nextInt(Constants.MAX_HEIGHT_OF_FLOORS - Constants.MIN_HEIGHT_OF_FLOORS + 1) + Constants.MIN_HEIGHT_OF_FLOORS,
                    random.nextInt(Constants.MAX_WEIGHT_OF_FLOORS - Constants.MIN_WEIGHT_OF_FLOORS + 1) + Constants.MIN_WEIGHT_OF_FLOORS);
        }
    }

    public Floor getFloor(int index) {
        return floors[index];
    }

    public Floor[] getFloors() {
        return floors;
    }

    public Map<String, Object> addCarToParking(int carHeight, int carWeight) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < floors.length; i++) {
            if ((floors[i].getFloorRemainingWeight() > carWeight) && (floors[i].getFloorHeight() > carHeight)) {
                Car car = new Car(carHeight, carWeight);
                car.setCarId(cars.size());
                car.setFloorId(i);
                cars.add(car);
                floors[i].addCarToFloor(car);
                floors[i].setFloorRemainingWeight(floors[i].getFloorRemainingWeight() - carWeight);
                map.put("message", "This car is added to Floor " + i);
                map.put("status", HttpStatus.OK);
                map.put("data", car);
                return map;
            }
        }
        map.put("status", HttpStatus.NOT_ACCEPTABLE);
        return map;
    }

    public Map<String, Object> deleteCar(int carId) {
        Map<String, Object> map = new HashMap<>();
        int floorId;
        int carWeight;
        Date exitTime = Calendar.getInstance().getTime();
        long duration;
        long diffInSeconds;
        for (Car car : cars) {
            if (car.getCarId() == carId) {
                floorId = car.getFloorId();
                carWeight = car.getCarWeight();
                duration = exitTime.getTime() - car.getEnterTime().getTime();
                diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
                floors[floorId].deleteCarFromFloor(car);
                floors[floorId].setFloorRemainingWeight(floors[floorId].getFloorRemainingWeight() + carWeight);
                cars.remove(car);
                map.put("duration", diffInSeconds);
                map.put("fee", calculateFee(diffInSeconds));
                map.put("status", HttpStatus.OK);
                return map;
            }
        }
        map.put("status", HttpStatus.NOT_ACCEPTABLE);
        return map;
    }

    public long calculateFee(long seconds) {
        return seconds * Constants.PRICE_PER_SECOND;
    }
}
