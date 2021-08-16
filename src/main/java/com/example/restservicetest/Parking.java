package com.example.restservicetest;

import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Parking {

    private Floor floors[];
    ArrayList<Car> cars = new ArrayList<Car>();

    public Parking(int numOfFloors) {
        floors = new Floor[numOfFloors];

        Random random = new Random();
        for (int i = 0; i < numOfFloors; i++) {
            floors[i] = new Floor(i,
                    random.nextInt(Constants.MAX_HEIGHT_OF_FLOORS + Constants.MIN_HEIGHT_OF_FLOORS) + Constants.MIN_HEIGHT_OF_FLOORS,
                    random.nextInt(Constants.MAX_WEIGHT_OF_FLOORS + Constants.MIN_WEIGHT_OF_FLOORS) + Constants.MIN_WEIGHT_OF_FLOORS);
        }
    }

    public Floor getFloor(int index) {
        return floors[index];
    }

    public Floor[] getFloors() {
        return floors;
    }

    public Map<String, Object> addCarToParking(int height, int weight) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < floors.length; i++) {
            if ((floors[i].getFloorWeight() > weight) && (floors[i].getFloorHeight() > height)) {
                int current_weight = floors[i].getFloorWeight();
                int car_weight = weight;
                Car car = new Car(height, car_weight);
                car.setCarId(cars.size());
                car.setFloorId(i);
                cars.add(car);
                floors[i].addCarToFloor(car);
                floors[i].setFloorWeight(current_weight - car_weight);
                map.put("message", "This car is added to Floor " + i);
                map.put("status", HttpStatus.OK);
                map.put("data", car);
                return map;
            }
        }
        map.put("message", "This car can't be added anywhere.");
        map.put("status", HttpStatus.FORBIDDEN);
        return map;
    }

    public String deleteCar(int carId) {
        int floorId = 0;
        int carWeight = 0;
        Date exitTime = Calendar.getInstance().getTime();
        long duration = 0;
        long diffInSeconds = 0;
        for (Car car : cars) {
            if (car.getCarId() == carId) {
                floorId = car.getFloorId();
                carWeight = car.getCarWeight();
                duration = exitTime.getTime() - car.getEnterTime().getTime();
                diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
                floors[floorId].deleteCarFromFloor(car);
                floors[floorId].setFloorWeight(floors[floorId].getFloorWeight() + carWeight);
                cars.remove(car);
                return "Duration: " + diffInSeconds + " seconds. Fee: " + calculateFee(diffInSeconds);
            }
        }
        return "This car isn't existed.";
    }

    public long calculateFee(long seconds) {
        return seconds * Constants.PRICE_PER_SECOND;
    }
}
