package com.example.restservicetest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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

    public String addCarToParking(int height, int weight) {
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
                return "This car is added to Floor " + i;
            }
        }
        return "This car can't add anywhere";
    }

    public String deleteCar(int carId) {
        int floorId = 0;
        int carWeight = 0;
        Date exitTime = Calendar.getInstance().getTime();
        long duration = 0;
        long diffInSeconds = 0;
        try {
            for (Car car : cars) {
                if (car.getCarId() == carId) {
                    floorId = car.getFloorId();
                    carWeight = car.getCarWeight();
                    duration = exitTime.getTime() - car.getEnterTime().getTime();
                    diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
                    floors[floorId].deleteCarFromFloor(car);
                    floors[floorId].setFloorWeight(floors[floorId].getFloorWeight() + carWeight);
                    cars.remove(car);
                    break;
                }
            }
            return "Duration: " + diffInSeconds + " seconds. Fee: " + calculateFee(diffInSeconds);
        } catch (Exception e) {
            return e.toString();
        }
    }

    public long calculateFee(long seconds) {
        return seconds * Constants.PRICE_PER_SECOND;
    }
}
