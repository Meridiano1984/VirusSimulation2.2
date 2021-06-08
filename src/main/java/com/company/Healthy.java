package com.company;

import java.util.LinkedList;

public class Healthy implements GameObject {

    private static int numberOfHealthy = 0;
    private boolean isMoved;

    public Healthy(boolean isMoved) {
        this.isMoved = isMoved;
        numberOfHealthy++;
    }

    public static int getNumberOfHealthy() { return numberOfHealthy;}
    public static void setNumberOfHealthy(int numberOfHealthy) { Healthy.numberOfHealthy = numberOfHealthy; }
    public boolean isMoved() { return isMoved; }
    public void setMoved(boolean moved) { isMoved = moved; }


    protected LinkedList<Healthy> killHealthy(LinkedList <Healthy> healthyList, Healthy healthyToKill){

        for(int i=0;i<healthyList.size();i++){

            if(healthyList.get(i)==healthyToKill){
                healthyList.remove(healthyToKill);
            }
        }
        return healthyList;
    }


    protected LinkedList<Healthy> addHealthy(LinkedList<Healthy> healthyList){

        healthyList.add(new Healthy(true));
        return healthyList;
    }
}
