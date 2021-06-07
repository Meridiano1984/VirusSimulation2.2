package com.company;

import java.util.LinkedList;

public class SickMedic implements GameObject {

    public static int numberOfSickMedic = 0;
    private Virus virus;
    private final int treatmentDuration = 3;
    public int numberOfIteration=0;

    public SickMedic(Virus virus) {
        this.virus = virus;
        numberOfSickMedic++;
    }

    public void deathOfSickMedic(SickMedic sickMedic){}

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }

    public LinkedList<Medic> transformationToMedic(SickMedic sickMedic, LinkedList<Medic> medicList, LinkedList<SickMedic> sickMedicList, int x, int y, Area[][] map){
        deleteSickMedic(sickMedic, sickMedicList, x, y, map);
        addNewMedic(medicList, x, y, map);
        Medic.numberOfMedics++;

        return medicList;
    }

    public LinkedList<SickMedic> deleteSickMedic(SickMedic sickMedic, LinkedList<SickMedic> sickMedicList, int x, int y, Area[][] map){
        sickMedicList = sickMedic.killSickMedic(sickMedicList, sickMedic);
        if(map[x][y].getField().getGameObjectReference() != null){
            map[x][y].getField().setGameObjectReference(null);
        }else{
            System.out.println("coś jest nie tak w uzdrowieniu medyka (usuwanie Chorego medyka)");
        }
        numberOfSickMedic--;
        return sickMedicList;
    }

    private LinkedList<Medic> addNewMedic( LinkedList<Medic> medicList, int x, int y, Area[][] map){
        Medic medic = new Medic(false);
        medicList.add(medic);
        if(map[x][y].getField().getGameObjectReference() == null){
            map[x][y].getField().setGameObjectReference(medic);
        }else {
            System.out.println("coś jest nie tak w uzdrowieniu medyka (dodawanie medyka)");
        }
        return medicList;
    }

    public LinkedList<SickMedic> killSickMedic(LinkedList<SickMedic> sickMedicList, SickMedic sickMedicToKill){

        for(int i=0;i<sickMedicList.size();i++){

            if(sickMedicList.get(i)==sickMedicToKill){
                sickMedicList.remove(sickMedicToKill);
            }

        }
        return sickMedicList;
    }




    // DODAWANIE CHOREGO MEDYKA => NWM CZY TO TAK MA DZIAŁAĆ ??? (UŻYTE W MEDIC - ZARAZANIE)
    public LinkedList<SickMedic> addSickMedic(LinkedList<SickMedic> sickMedicList){

//        for(SickMedic sickMedic: sickMedicList){
//
//            if(sickMedic == sickMedicToAdd){
//                sickMedicList.add(sickMedicToAdd);
//            }
//
//        }
        sickMedicList.add(new SickMedic(virus));
        return sickMedicList;
    }
}
