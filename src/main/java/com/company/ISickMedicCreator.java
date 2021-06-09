package com.company;

import java.util.LinkedList;
import java.util.List;

public interface ISickMedicCreator {
    public static LinkedList<SickMedic> createSickMedic (int numberOfPeople, Virus virus ){
        List<SickMedic> sickMedicList =new LinkedList<>();


        System.out.println("Sick w moemencie tworzenia listy:" + numberOfPeople);


        for (int i=0; i<numberOfPeople; i++){
            sickMedicList.add(new SickMedic(virus,0));
        }
        return (LinkedList<SickMedic>) sickMedicList;
    };
}
