package com.company;

import java.util.LinkedList;
import java.util.List;


public class GameObjectList {

    protected int numberOfPeople;
    private LinkedList<Healthy> healthyList = new LinkedList<>();
    private LinkedList<Obstacle> obstacleList = new LinkedList<>();
    private LinkedList<Medic> medicList = new LinkedList<>();
    private LinkedList<Sick> sickList = new LinkedList<>();



    protected LinkedList<SickMedic> sickMedicList = new LinkedList<>();
    protected Virus virus;

    public GameObjectList(int numberOfPeople, LinkedList<Healthy> healthyList, LinkedList<Obstacle> obstacleList, LinkedList<Medic> medicList, LinkedList<Sick> sickList, LinkedList<SickMedic> sickMedicList, Virus virus) {
        this.numberOfPeople = numberOfPeople;
        this.healthyList = healthyList;
        this.obstacleList = obstacleList;
        this.medicList = medicList;
        this.sickList = sickList;
        this.sickMedicList = sickMedicList;
        this.virus = virus;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LinkedList<Healthy> getHealthyList() {
        return healthyList;
    }

    public void setHealthyList(LinkedList<Healthy> healthyList) {
        this.healthyList = healthyList;
    }

    public LinkedList<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public void setObstacleList(LinkedList<Obstacle> obstacleList) {
        this.obstacleList = obstacleList;
    }

    public LinkedList<Medic> getMedicList() {
        return medicList;
    }

    public void setMedicList(LinkedList<Medic> medicList) {
        this.medicList = medicList;
    }

    public LinkedList<Sick> getSickList() {
        return sickList;
    }

    public void setSickList(LinkedList<Sick> sickList) {
        this.sickList = sickList;
    }

    public LinkedList<SickMedic> getSickMedicList() {
        return sickMedicList;
    }

    public void setSickMedicList(LinkedList<SickMedic> sickMedicList) {
        this.sickMedicList = sickMedicList;
    }

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }

    public LinkedList<GameObject> bigListCreator (List<Healthy> healthyList, List<Medic> medicList, List<Sick> sickList, List<SickMedic> sickMedicList) {

        LinkedList<GameObject> bigList  = new LinkedList<>();

        for(Healthy healthy: healthyList){
            bigList.add(healthy);
        }

        for(Sick sick: sickList){
            bigList.add(sick);
        }

        for(Medic medic: medicList){
            bigList.add(medic);
        }
        for(SickMedic sickMedic: sickMedicList){
            bigList.add(sickMedic);
        }

        return bigList;
    }

    public LinkedList<GameObject> movingListCreator(List<Healthy> healthyList, List<Medic> medicList, List<Sick> sickList){
        LinkedList<GameObject> movingList  = new LinkedList<>();

        for(Healthy healthy: healthyList){
            movingList.add(healthy);
        }

        for(Sick sick: sickList){
            movingList.add(sick);
        }

        for(Medic medic: medicList){
            movingList.add(medic);
        }


        return movingList;
    }


    public GameObjectList regeneration(GameObjectList gameObjectList){

        for(Healthy helathy: gameObjectList.getHealthyList()){
            helathy.setMoved(false);
        }
        for(Medic medic: gameObjectList.getMedicList()){
            medic.setMoved(false);
        }
        for(Sick sick: gameObjectList.getSickList()){
            sick.setMoved(false);
        }

        return gameObjectList;
    }

    public GameObjectList virusSpreadingRegeneration (GameObjectList gameObjectList){

        for(Sick sick: gameObjectList.getSickList()){
            sick.setVirusSpread(false);
        }

        return gameObjectList;
    }


    public static void numberOfObjects(LinkedList<GameObject> bigList) {
        int i =0;

        for(GameObject gameObject: bigList){
            i++;
        }
        System.out.println("Liczba obiektow to: " + i + "+ liczba obstacle");
    }

    protected LinkedList<Sick> killSick(LinkedList<Sick> sickList, Sick sickToKill){

        int rememberI =-1;
        for(int i=0;i<sickList.size();i++){

            if(sickList.get(i)==sickToKill){
                rememberI=i;
            }
        }
        if(rememberI!=-1) {
            sickList.remove(sickList.get(rememberI));
        }



        return sickList;
    }

    protected LinkedList<Medic> killMedic(LinkedList<Medic> medicList, Medic medicToKill){
        int rememberI =-1;
        for(int i=0;i<medicList.size();i++){

            if(medicList.get(i)==medicToKill){
                rememberI=i;
            }
        }
        if(rememberI!=-1) {
            medicList.remove(medicList.get(rememberI));
        }
        return medicList;
    }

    protected LinkedList<SickMedic> killSickMedic(LinkedList<SickMedic> sickMedicList, SickMedic sickMedicToKill , GameObjectList gameObjectList){

        int rememberI =-1;
        for(int i=0;i<sickMedicList.size();i++){

            if(sickMedicList.get(i)==sickMedicToKill){
                rememberI=i;
            }
        }
        if(rememberI!=-1) {
            sickMedicList.remove(sickMedicList.get(rememberI));
        }

        return sickMedicList;
    }

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
