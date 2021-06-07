package com.company;

import java.util.LinkedList;
import java.util.List;

public interface IMedicCreator {
    public static LinkedList<Medic> createMedic (int numberOfPeople ){

        numberOfPeople =numberOfPeople/5;
        System.out.println("Medic w momencie tworzenia listy :" + numberOfPeople);

        List<Medic> medicList =new LinkedList<>();

//        Medic[] medicTab = new Medic[numberOfPeople];
//
//        for(int i = 0; i< medicTab.length;i++ ){
//            medicTab[i] = new Medic(false);
//
//        }
//
//
//        for (int i =0; i<medicTab.length; i++) {
//            medicList.add(medicTab[i]);
//        }
//        System.out.println("a za pomiaca funkcji size wynosi:" + medicList.size());
        for (int i=0; i<numberOfPeople; i++){
            medicList.add(new Medic(false));
        }

        return (LinkedList<Medic>) medicList;
    };
}
