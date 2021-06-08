package com.company;

import java.util.LinkedList;

public class SickMedic implements GameObject {

    private static int numberOfSickMedic = 0;
    private Virus virus;
    private final int treatmentDuration = 3;
    private int numberOfIteration=0;
    private LinkedList<SickMedic> sickMedicList;

    public SickMedic(Virus virus,int numberOfIteration) {
        this.virus = virus;
        this.numberOfIteration=numberOfIteration;
        numberOfSickMedic++;
    }

    public static int getNumberOfSickMedic() { return numberOfSickMedic; }
    public static void setNumberOfSickMedic(int numberOfSickMedic) { SickMedic.numberOfSickMedic = numberOfSickMedic; }

    public void deathOfSickMedic(SickMedic sickMedic){}

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }

    public int getNumberOfIteration() {
        return numberOfIteration;
    }

    public void setNumberOfIteration(int numberOfIteration) {
        this.numberOfIteration = numberOfIteration;
    }

//    protected LinkedList<SickMedic> killSickMedic(LinkedList<SickMedic> sickMedicList, SickMedic sickMedicToKill , GameObjectList gameObjectList){
//
//        int rememberI =-1;
//        for(int i=0;i<sickMedicList.size();i++){
//
//            if(sickMedicList.get(i)==sickMedicToKill){
//                rememberI=i;
//            }
//        }
//        if(rememberI!=-1) {
//            sickMedicList.remove(sickMedicList.get(rememberI));
//        }
//
//        return sickMedicList;
//    }




}
