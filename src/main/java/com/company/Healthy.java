package com.company;

import java.util.LinkedList;

public class Healthy implements GameObject {

    private static int numberOfHealthy = 0;
    private boolean isMoved;
    public static int healthyToFile =0;

    public Healthy(boolean isMoved) {
        this.isMoved = isMoved;
        numberOfHealthy++;
    }

    public static int getNumberOfHealthy() { return numberOfHealthy;}
    public static void setNumberOfHealthy(int numberOfHealthy) { Healthy.numberOfHealthy = numberOfHealthy; }
    public boolean isMoved() { return isMoved; }
    public void setMoved(boolean moved) { isMoved = moved; }


    protected LinkedList<Healthy> killHealthy(LinkedList <Healthy> healthyList, Healthy healthyToKill){


        int rememberI =-1;
        for(int i=0;i<healthyList.size();i++){

            if(healthyList.get(i)==healthyToKill){
                rememberI=i;
            }
        }
        if(rememberI!=-1) {
            healthyList.remove(healthyList.get(rememberI));
        }

        return healthyList;
    }

}
