package com.company;

import java.util.LinkedList;
import java.util.List;

public interface IHealthyCreator {
    public static LinkedList<Healthy> createHealthy (int numberOfPeople ){
        List<Healthy> healthyList =new LinkedList<>();

        numberOfPeople = numberOfPeople/5;
        System.out.println("Healthy w momencie tworzenia listy:" + numberOfPeople);

//        Healthy[] healthyTab = new Healthy[numberOfPeople];
//
//        for(int i = 0; i< healthyTab.length;i++ ){
//            healthyTab[i] = new Healthy(false);
//        }
//
//        for (int i =0; i<healthyTab.length; i++) {
//            healthyList.add(healthyTab[i]);
//        }
//         System.out.println("a za pomiaca funkcji size wynosi:" + healthyList.size());
        for (int i=0; i<numberOfPeople; i++){
            healthyList.add(new Healthy(false));
        }

        return (LinkedList<Healthy>) healthyList;
    };
}
