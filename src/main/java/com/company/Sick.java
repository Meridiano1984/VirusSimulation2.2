package com.company;

import java.util.LinkedList;

public class Sick implements GameObject {

    private static int numberOfSick = 0;
    private Virus virus;
    private boolean isMoved;
    private boolean isVirusSpread;

    public Sick(Virus virus, boolean isMoved, boolean isVirusSpread) {
        this.virus = virus;
        this.isMoved = isMoved;
        this.isVirusSpread = isVirusSpread;
        numberOfSick++;
    }

    public static int getNumberOfSick() { return numberOfSick; }
    public static void setNumberOfSick(int numberOfSick) { Sick.numberOfSick = numberOfSick; }
    public boolean isMoved() { return isMoved; }
    public void setMoved(boolean moved) { isMoved = moved; }
    public boolean isVirusSpread() {
        return isVirusSpread;
    }
    public void setVirusSpread(boolean virusSpread) {
        isVirusSpread = virusSpread;
    }

    public void deathOfSick (Sick sick){}

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }


//    protected LinkedList<Sick> killSick(LinkedList<Sick> sickList, Sick sickToKill){
//
//        int rememberI =-1;
//        for(int i=0;i<sickList.size();i++){
//
//            if(sickList.get(i)==sickToKill){
//                rememberI=i;
//            }
//        }
//        if(rememberI!=-1) {
//            sickList.remove(sickList.get(rememberI));
//        }
//
//
//
//        return sickList;
//    }


}




