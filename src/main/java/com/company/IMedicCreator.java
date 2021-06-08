package com.company;

import java.util.LinkedList;
import java.util.List;

public interface IMedicCreator {
    public static LinkedList<Medic> createMedic (int numberOfPeople ){

        numberOfPeople =numberOfPeople/5;
        System.out.println("Medic w momencie tworzenia listy :" + numberOfPeople);

        List<Medic> medicList =new LinkedList<>();


        for (int i=0; i<numberOfPeople; i++){
            medicList.add(new Medic(false));
        }

        return (LinkedList<Medic>) medicList;
    };
}
