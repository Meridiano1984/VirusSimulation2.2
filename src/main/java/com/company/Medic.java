package com.company;

import java.util.LinkedList;

public class Medic implements GameObject {
    public static int numberOfMedics = 0;
    private boolean isMoved;

    public Medic(boolean isMoved) {
        this.isMoved = isMoved;
        numberOfMedics++;

    }

    public static int getNumberOfMedics() {
        return numberOfMedics;
    }

    public static void setNumberOfMedics(int numberOfMedics) {
        Medic.numberOfMedics = numberOfMedics;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public Healthy healSick (Area[][] map){
        return null;
    }



    public LinkedList<Medic> killMedic(LinkedList<Medic> medicList, Medic medicToKill){

        for(Medic medic: medicList){

            if(medic==medicToKill){
                medicList.remove(medicToKill);
            }

        }
        return medicList;
    }
}
