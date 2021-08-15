package com.example.restservicetest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Parking {

    private Floor floors[];
    ArrayList<Car> cars = new ArrayList<Car>();
    int numOfCars;

    public Parking(int numOfFloors) {
        floors = new Floor[numOfFloors];

        numOfCars = 0;

        int min_height = 1;
        int max_height = 10;
        int min_weight = 1;
        int max_weight = 10;
        Random random = new Random();

        for(int i = 0; i< numOfFloors; i++){
            floors[i] = new Floor(i,
                    random.nextInt(max_height + min_height) + min_height,
                            random.nextInt(max_weight + min_weight) + min_weight);
        }
    }

    public Floor getFloor(int index){
        return floors[index];
    }

    public Floor[] getFloors(){
        return floors;
    }

    public String addCarToParking(int height, int weight){
        for(int i = 0; i< floors.length; i++){
            if ((floors[i].getFloorWeight() > weight) && (floors[i].getFloorHeight() > height)){
                int current_weight = floors[i].getFloorWeight();
                int car_weight = weight;
                Car car = new Car(height, car_weight);
                car.setCarId(numOfCars);
                car.setFloorId(i);
                cars.add(car);
                numOfCars++;
                floors[i].addCarToFloor(car);
                floors[i].setFloorWeight(current_weight-car_weight);
                return "This car is added to Floor " + i;
            }
        }
        return "This car can't add anywhere";
    }

    public String deleteCar(int carId){
        try{
            int floorId = cars.get(carId).getFloorId();
            int carWeight = cars.get(carId).getCarWeight();
            Date exitTime = Calendar.getInstance().getTime();
            long duration = exitTime.getTime() - cars.get(carId).getEnterTime().getTime();
            long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            floors[floorId].deleteCarFromFloor(cars.get(carId));
            floors[floorId].setFloorWeight(floors[floorId].getFloorWeight() + carWeight);
            cars.remove(carId);
            numOfCars--;
            return "Ucret: " + diffInSeconds;
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
