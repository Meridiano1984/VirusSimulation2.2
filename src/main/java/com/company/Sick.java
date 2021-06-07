package com.company;

import java.util.LinkedList;

public class Sick implements GameObject {

    public static int numberOfSick = 0;
    private Virus virus;
    private boolean isMoved;

    public Sick(Virus virus, boolean isMoved) {
        this.virus = virus;
        this.isMoved = isMoved;
        numberOfSick++;
    }

    public static int getNumberOfSick() {
        return numberOfSick;
    }

    public static void setNumberOfSick(int numberOfSick) {
        Sick.numberOfSick = numberOfSick;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }



    public boolean isMedicNearby (Area[][] map){
        return false;
    }

    public void deathOfSick (Sick sick){}


    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }


/*    public LinkedList<Healthy> transformationToHealthy(Sick sick, LinkedList<Healthy> healthyList, LinkedList<Sick> sickList, int x, int y, Area[][] map) {
        deleteSick(sick, sickList, x, y, map);
        addNewHealthy(healthyList, x, y, map);
        Healthy.numberOfHealthy++;
        return healthyList;
    }


   //Usuniecie chorego
    private LinkedList<Sick> deleteSick(Sick sick, LinkedList<Sick> sickList, int x, int y, Area[][] map){
        sickList = sick.killSick(sickList, sick);
        if(map[x][y].getField().getGameObjectReference() != null){
            map[x][y].getField().setGameObjectReference(null);
        }else{
            System.out.println("coś jest nie tak w zarazaniu zdrowego (usuwanie zrdowego)");
        }
        numberOfSick--;
        return sickList;
    }

    //Dodanie zdrowego
    private LinkedList<Healthy> addNewHealthy( LinkedList<Healthy> healthyList, int x, int y, Area[][] map){
        Healthy healthy = new Healthy(false);
        healthyList.add(healthy);
        if(map[x][y].getField().getGameObjectReference() == null){
            map[x][y].getField().setGameObjectReference(healthy);
        }else {
            System.out.println("coś jest nie tak w zarazaniu zdrowego (dodawanie chorego)");
        }
        return healthyList;
    }*/

    public LinkedList<Sick> killSick(LinkedList<Sick> sickList, Sick sickToKill){

//        for(Sick sick: sickList){
//
//            if(sick==sickToKill){
//                sickList.remove(sickToKill);
//            }
//
//        }



        for(int i=0;i<sickList.size();i++){

            if(sickList.get(i)==sickToKill){
                sickList.remove(sickToKill);
            }

        }
        return sickList;
    }

    // DODAWANIE CHOREGO => NWM CZY TO TAK MA DZIAŁAĆ ??? (UŻYTE W HEALTHY - ZARAZANIE)
    public LinkedList<Sick> addSick(LinkedList<Sick> sickList){

//        for(Sick sick: sickList){
//
//            if(sick==sickToAdd){
//                sickList.add(sickToAdd);
//            }
//
//        }
        sickList.add(new Sick(virus, true));
        return sickList;
    }

}




