package com.example.restservicetest.Models;

import java.util.Calendar;
import java.util.Date;

public class Car {

    private int carId;
    private int floorId;
    private int carHeight;
    private int carWeight;
    private Date enterTime;

    public Car(int carHeight, int carWeight) {
        this.carHeight = carHeight;
        this.carWeight = carWeight;
        this.enterTime = Calendar.getInstance().getTime();
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

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}
