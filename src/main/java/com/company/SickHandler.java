package com.company;

import java.util.LinkedList;
import java.util.Random;

public class SickHandler extends InteractionHandler {
    private Area[][] map;
    private GameObject reference;
    private int size;

    public SickHandler(Area[][] map, GameObject reference, int size) {
        this.map = map;
        this.reference = reference;
        this.size = size;
    }

    @Override
    protected GameObject interaction(){
        return null;
    }


    public void transformationToHealthy(Area[][] map, LinkedList<Healthy> healthyList, LinkedList<Sick> sickList, GameObjectList gameObjectList){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j].getField().getGameObjectReference() instanceof Sick) {
                    if(isMedicNearby(map, i, j)){
                        Sick sick = (Sick) map[i][j].getField().getGameObjectReference();
                        deleteSick(sick , sickList, i, j, map,gameObjectList);
                        addNewHealthy(healthyList, i, j, map);
                    }
                }
            }
        }
    }



    protected boolean isMedicNearby(Area[][] map, int a, int b){
        for(int i = a-1; i<= a+1; i++){
            for(int j = b-1; j <= b+1; j++){
                if(i >= 0 && j >= 0 && i < size && j< size){
                    if(map[i][j].getField().getGameObjectReference() instanceof Medic){
                        return true;
                    }
                }
            }
        }
        for(int i= a-2; i<= a+2; i++){
            for(int j = b-2; j <= b+2; j++){
                if(i >=0 && j >= 0 && i<size && j<size){
                    if(map[i][j].getField().getGameObjectReference() instanceof Medic){
                        return true;
                    }
                }
            }
        }
        return false;
    }



    //USUWANIE CHOREGO
    private LinkedList<Sick> deleteSick(Sick sick, LinkedList<Sick> sickList, int x, int y, Area[][] map,GameObjectList gameObjectList){
        ;
        sickList = gameObjectList.killSick(sickList, sick);
        if(map[x][y].getField().getGameObjectReference() != null){
            map[x][y].getField().setGameObjectReference(null);
        }else{
            System.out.println("coś jest nie tak w uzdrawniau chorego (usuwanie chorego)");
        }
        Sick.setNumberOfSick(Sick.getNumberOfSick()-1);
        return sickList;
    }



    //DODAWANIE ZDROWEGO
    private LinkedList<Healthy> addNewHealthy( LinkedList<Healthy> healthyList, int x, int y, Area[][] map){
        Healthy healthy = new Healthy(false);
        healthyList.add(healthy);
        if(map[x][y].getField().getGameObjectReference() == null){
            map[x][y].getField().setGameObjectReference(healthy);
        }else {
            System.out.println("coś jest nie tak w uzdrawnaniu chorego (dodawanie zdrowego)");
        }
        Healthy.setNumberOfHealthy(Healthy.getNumberOfHealthy()+1);
        return healthyList;
    }


    public void virusKillSick (Area[][] map, LinkedList<Sick> sickList, GameObjectList gameObjectList) {

        Random random =new Random();
        int bound;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bound =random.nextInt(100)+1;
//                System.out.println("BOUND PRZED WARUNKIEM:"+bound);

                if (map[i][j].getField().getGameObjectReference() instanceof Sick && ((Sick) map[i][j].getField().getGameObjectReference()).getVirus().getDeathRateOfSick()>=bound ) {

//                    System.out.println("BOUND PO WARUNKU:"+bound);
//                    System.out.println("DEATHRATEOFSIC: "+((Sick) map[i][j].getField().getGameObjectReference()).getVirus().getDeathRateOfSick());
                    Sick sick =(Sick)map[i][j].getField().getGameObjectReference();
                    deleteSick(sick, sickList,i,j,map, gameObjectList);
                }
            }
        }
    }



}
