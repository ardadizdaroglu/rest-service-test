package com.example.restservicetest;

import java.util.Random;

public class Parking {

    private Floor floors[];
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
                numOfCars++;
                floors[i].addCarToFloor(car);
                floors[i].setFloorWeight(current_weight-car_weight);
                return "This car is added to Floor " + i;
            }
        }
        return "This car can't add anywhere";
    }
}
