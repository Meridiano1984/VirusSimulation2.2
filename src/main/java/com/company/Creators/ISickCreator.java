package com.company.Creators;

import com.company.Objects.Sick;
import com.company.Virus;

import java.util.LinkedList;
import java.util.List;

public interface ISickCreator {
    public static LinkedList<Sick> createSick (int numberOfPeople, Virus virus ){
        List<Sick> sickList = new LinkedList<>();


        System.out.println("Sick w momencie tworzenia listy :" + numberOfPeople);


        for (int i=0; i<numberOfPeople; i++){
            sickList.add(new Sick(virus, false,false));
        }

        return (LinkedList<Sick>) sickList;
    };

}
