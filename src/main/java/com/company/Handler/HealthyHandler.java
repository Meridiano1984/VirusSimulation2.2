package com.company.Handler;

import com.company.*;
import com.company.Objects.GameObject;
import com.company.Objects.Healthy;
import com.company.Objects.Sick;

import java.util.LinkedList;

public class HealthyHandler extends InteractionHandler {
    private Area[][] map;
    private GameObject reference;
    private int size;

    public HealthyHandler(Area[][] map, GameObject reference, int size) {
        this.map = map;
        this.reference = reference;
        this.size = size;
    }

    @Override
    protected GameObject interaction(){
        return null;
    }


    public void transformationToSick(Area[][] map, Virus virus, LinkedList<Healthy> healthyList, LinkedList<Sick> sickList, GameObjectList gameObjectList){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j].getField().getGameObjectReference() instanceof Healthy) {
                    if(isSickNearby(map, i, j)){
                        Healthy healthy = (Healthy) map[i][j].getField().getGameObjectReference();
                        deleteHealthy(healthy , healthyList, i, j, map, gameObjectList);
                        addNewSick(virus, sickList, i, j, map);

                    }
                }
            }
        }
    }



    private boolean isSickNearby(Area[][] map, int a, int b){
        for(int i = a-1; i<= a+1; i++){
            for(int j = b-1; j <= b+1 ; j++){
                if(i >= 0 && j >= 0 && i < size && j< size){
                    if(map[i][j].getField().getGameObjectReference() instanceof Sick && ((Sick) map[i][j].getField().getGameObjectReference()).isVirusSpread()==false){
                        return true;
                    }
                }
            }
        }
        return false;
    }



    // USUWANIE ZDROWEGO
    private LinkedList<Healthy> deleteHealthy(Healthy healthy, LinkedList<Healthy> healthyList, int x, int y, Area[][] map, GameObjectList gameObjectList){
        healthyList = gameObjectList.killHealthy(healthyList, healthy);
        if(map[x][y].getField().getGameObjectReference() != null){
            map[x][y].getField().setGameObjectReference(null);
        }else{
            System.out.println("co?? jest nie tak w zarazaniu zdrowego (usuwanie zrdowego)");
        }
        Healthy.setNumberOfHealthy(Healthy.getNumberOfHealthy()-1);
        return healthyList;
    }



    // DODAWANIE CHOREGO
    private LinkedList<Sick> addNewSick(Virus virus, LinkedList<Sick> sickList, int x, int y, Area[][] map){

        Sick sick = new Sick(virus, false,true);
        sickList.add(sick);
        if(map[x][y].getField().getGameObjectReference() == null){
            map[x][y].getField().setGameObjectReference(sick);
            Sick.sickToFile++;
        }else {
            System.out.println("co?? jest nie tak w zarazaniu zdrowego (dodawanie chorego)");
        }
        Sick.setNumberOfSick(Sick.getNumberOfSick()+1);
        return sickList;
    }
}
