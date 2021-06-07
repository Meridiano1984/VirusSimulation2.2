package com.company;

import java.util.LinkedList;
import java.util.List;

public interface ISickCreator {
    public static LinkedList<Sick> createSick (int numberOfPeople, Virus virus ){
        List<Sick> sickList = new LinkedList<>();

        numberOfPeople=numberOfPeople/5;
        System.out.println("Sick w momencie tworzenia listy :" + numberOfPeople);


//        Sick[] sickTab = new Sick[numberOfPeople];
//
//        for(int i = 0; i< sickTab.length;i++ ){
//            sickTab[i] = new Sick(virus, false);
//        }
//
//        for (int i =0; i<sickTab.length; i++) {
//            sickList.add(sickTab[i]);
//        }
//        System.out.println("a za pomiaca funkcji size wynosi:" + sickList.size());

        for (int i=0; i<numberOfPeople; i++){
            sickList.add(new Sick(virus, false));
        }

        return (LinkedList<Sick>) sickList;
    };

}
