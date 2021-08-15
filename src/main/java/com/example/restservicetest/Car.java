package com.example.restservicetest;

public class Car {

    private int carId;
    private int carHeight;
    private int carWeight;

    public Car(int carHeight, int carWeight) {
        this.carHeight = carHeight;
        this.carWeight = carWeight;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCarHeight() {
        return carHeight;
    }

    public void setCarHeight(int carHeight) {
        this.carHeight = carHeight;
    }

    public int getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(int carWeight) {
        this.carWeight = carWeight;
    }
}
