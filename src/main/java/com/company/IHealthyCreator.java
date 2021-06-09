package com.company;

import java.util.LinkedList;
import java.util.List;

public interface IHealthyCreator {
    public static LinkedList<Healthy> createHealthy (int numberOfPeople ){
        List<Healthy> healthyList =new LinkedList<>();


        System.out.println("Healthy w momencie tworzenia listy:" + numberOfPeople);


        for (int i=0; i<numberOfPeople; i++){
            healthyList.add(new Healthy(false));
        }

        return (LinkedList<Healthy>) healthyList;
    };
}
