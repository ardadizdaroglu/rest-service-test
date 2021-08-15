package com.example.restservicetest;

import java.util.Random;

public class Parking {

    private Floor floors[];

    public Parking(int numOfFloors) {
        floors = new Floor[numOfFloors];

        int min_height = 1;
        int max_height = 10;
        int min_weight = 1;
        int max_weight = 10;
        Random random = new Random();

        for(int i = 0; i< numOfFloors; i++){
            floors[i] = new Floor(random.nextInt(max_height + min_height) + min_height,
                            random.nextInt(max_weight + min_weight) + min_weight);
        }
    }

    public Floor getFloor(int index){
        return floors[index];
    }
    public Floor[] getFloors(){
        return floors;
    }
}