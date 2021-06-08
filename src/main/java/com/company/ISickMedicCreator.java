package com.company;

import java.util.LinkedList;
import java.util.List;

public interface ISickMedicCreator {
    public static LinkedList<SickMedic> createSickMedic (int numberOfPeople, Virus virus ){
        List<SickMedic> sickMedicList =new LinkedList<>();

        numberOfPeople=numberOfPeople/5;
        System.out.println("Sick w moemencie tworzenia listy:" + numberOfPeople);


        SickMedic[] sickMedicTab = new SickMedic[numberOfPeople];


        for (int i=0; i<numberOfPeople; i++){
            sickMedicList.add(new SickMedic(virus));
        }
        return (LinkedList<SickMedic>) sickMedicList;
    };
}
