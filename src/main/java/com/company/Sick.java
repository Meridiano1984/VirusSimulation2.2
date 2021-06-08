package com.company;

import java.util.LinkedList;

public class Sick implements GameObject {

    private static int numberOfSick = 0;
    private Virus virus;
    private boolean isMoved;

    public Sick(Virus virus, boolean isMoved) {
        this.virus = virus;
        this.isMoved = isMoved;
        numberOfSick++;
    }

    public static int getNumberOfSick() { return numberOfSick; }
    public static void setNumberOfSick(int numberOfSick) { Sick.numberOfSick = numberOfSick; }
    public boolean isMoved() { return isMoved; }
    public void setMoved(boolean moved) { isMoved = moved; }


    public void deathOfSick (Sick sick){}

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }


    protected LinkedList<Sick> killSick(LinkedList<Sick> sickList, Sick sickToKill){

        for(int i=0;i<sickList.size();i++){

            if(sickList.get(i)==sickToKill){
                sickList.remove(sickToKill);
            }

        }
        return sickList;
    }


}




