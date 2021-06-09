package com.company;

import java.util.LinkedList;
import java.util.List;

public interface IObstacleCreator {
    public static LinkedList<Obstacle> createObstacle (int numberOfPeople ){
        List<Obstacle> obstacleList =new LinkedList<>();

        System.out.println("Obstacle w momencie tworzenia listy :" + numberOfPeople);


        for (int i=0; i<numberOfPeople; i++){
            obstacleList.add(new Obstacle());
        }

        return (LinkedList<Obstacle>) obstacleList;

    }
}
