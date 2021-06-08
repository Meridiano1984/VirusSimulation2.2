package com.company;

import java.util.LinkedList;
import java.util.List;

public interface ISickCreator {
    public static LinkedList<Sick> createSick (int numberOfPeople, Virus virus ){
        List<Sick> sickList = new LinkedList<>();

        numberOfPeople=numberOfPeople/5;
        System.out.println("Sick w momencie tworzenia listy :" + numberOfPeople);


        for (int i=0; i<numberOfPeople; i++){
            sickList.add(new Sick(virus, false,false));
        }

        return (LinkedList<Sick>) sickList;
    };

}
