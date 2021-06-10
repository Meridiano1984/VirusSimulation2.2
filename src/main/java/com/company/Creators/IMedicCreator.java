package com.company.Creators;

import com.company.Objects.Medic;

import java.util.LinkedList;
import java.util.List;

public interface IMedicCreator {
    public static LinkedList<Medic> createMedic (int numberOfPeople ){

        System.out.println("Medic w momencie tworzenia listy :" + numberOfPeople);

        List<Medic> medicList =new LinkedList<>();


        for (int i=0; i<numberOfPeople; i++){
            medicList.add(new Medic(false));
        }

        return (LinkedList<Medic>) medicList;
    };
}
