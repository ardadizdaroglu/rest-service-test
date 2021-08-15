package com.example.restservicetest;

import java.util.ArrayList;

public class Floor {

    private int floorId;
    private int floorHeight;
    private int floorWeight;
    ArrayList<Car> cars = new ArrayList<Car>();
    private int numOfCars;

    public Floor(int floorId, int floorHeight, int floorWeight) {
        this.floorId = floorId;
        this.floorHeight = floorHeight;
        this.floorWeight = floorWeight;
        this.numOfCars = 0;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(int floorHeight) {
        this.floorHeight = floorHeight;
    }

    public int getFloorWeight() {
        return floorWeight;
    }

    public void setFloorWeight(int floorWeight) {
        this.floorWeight = floorWeight;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public int getNumOfCars() {
        return numOfCars;
    }

    public void setNumOfCars(int numOfCars) {
        this.numOfCars = numOfCars;
    }

    public void addCarToFloor(Car car){
        cars.add(car);
        numOfCars++;
    }
    public void deleteCarFromFloor(Car car){
        cars.remove(car);
        numOfCars--;
    }
}
