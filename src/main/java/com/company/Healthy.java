package com.company;

import java.util.LinkedList;

public class Healthy implements GameObject {

    public static int numberOfHealthy = 0;
    private boolean isMoved;

    public static int getNumberOfHealthy() {
        return numberOfHealthy;
    }

    public static void setNumberOfHealthy(int numberOfHealthy) {
        Healthy.numberOfHealthy = numberOfHealthy;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public Healthy(boolean isMoved) {
        this.isMoved = isMoved;
        numberOfHealthy++;

    }

    public LinkedList<Healthy> killHealthy(LinkedList <Healthy> healthyList, Healthy healthyToKill){

/*            for (Healthy healthy : healthyList) {

                if (healthy == healthyToKill) {
                    healthyList.remove(healthyToKill);
                }
            }
            return healthyList;*/

        for(int i=0;i<healthyList.size();i++){

            if(healthyList.get(i)==healthyToKill){
                healthyList.remove(healthyToKill);
            }

        }
        return healthyList;
    }

    // DODAWANIE ZDROWEGP => NWM CZY TO TAK MA DZIAlAĆ ??? (UŻYTE W SICK - LECZENIE) // przerzniete brutalnie od Natali, przepraszam!
    public LinkedList<Healthy> addHealthy(LinkedList<Healthy> healthyList){

//        for(Healthy healthy: healthyList){
//
//            if(healthy==healthyToAdd){
//                healthyList.add(healthyToAdd);
//            }
//
//        }
        healthyList.add(new Healthy(true));
        return healthyList;
    }
}
