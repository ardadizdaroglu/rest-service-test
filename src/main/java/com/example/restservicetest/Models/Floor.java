package com.example.restservicetest.Models;

import com.example.restservicetest.Models.Car;

import java.util.ArrayList;

public class Floor {

    private int floorId;
    private int floorHeight;
    private int floorWeight;
    ArrayList<Car> cars = new ArrayList<>();

    public Floor(int floorId, int floorHeight, int floorWeight) {
        this.floorId = floorId;
        this.floorHeight = floorHeight;
        this.floorWeight = floorWeight;
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

    public void addCarToFloor(Car car) {
        cars.add(car);
    }

    public void deleteCarFromFloor(Car car) {
        cars.remove(car);
    }
}
