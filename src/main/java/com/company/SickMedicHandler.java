package com.company;

import java.util.LinkedList;

public class SickMedicHandler extends InteractionHandler{
    private Area[][] map;
    private GameObject reference;
    private int size;


    public SickMedicHandler(Area[][] map, GameObject reference, int size) {
        this.map = map;
        this.reference = reference;
        this.size = size;
    }

    @Override
    protected GameObject interaction(){return null;}


    public LinkedList<SickMedic> checkingNumberOfIteration(Area [][] map, LinkedList<SickMedic> sickMedicList, LinkedList<Medic> medicList, MedicHandler medicHandler, int size){

        for (SickMedic sickMedic: sickMedicList){
            sickMedic.setNumberOfIteration(sickMedic.getNumberOfIteration()+1);
        }

        return sickMedicList;
    }


    public Area[][] transformationToMedicOrDying(Area[][] map, LinkedList<Medic> medicList, LinkedList<SickMedic> sickMedicList, MedicHandler medicHandler, int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (map[i][j].getField().getGameObjectReference() instanceof SickMedic && ((SickMedic) map[i][j].getField().getGameObjectReference()).getNumberOfIteration()>=4 ) {
                    SickMedic sickMedic = (SickMedic)map[i][j].getField().getGameObjectReference();
                    if(medicHandler.isSickNearby(map, i, j) >= 3){
                        deleteSickMedic( (SickMedic) map[i][j].getField().getGameObjectReference(),  sickMedicList, i, j, map);
                    }
                    else{
                        transformationToMedic(sickMedic, medicList, sickMedicList, i, j, map);
                    }
                }
            }
        }
        return map;
    }


    private LinkedList<Medic> transformationToMedic(SickMedic sickMedic, LinkedList<Medic> medicList, LinkedList<SickMedic> sickMedicList, int x, int y, Area[][] map){
        deleteSickMedic(sickMedic, sickMedicList, x, y, map);
        addNewMedic(medicList, x, y, map);
        Medic.setNumberOfMedics(Medic.getNumberOfMedics()+1);

        return medicList;
    }


    private LinkedList<SickMedic> deleteSickMedic(SickMedic sickMedic, LinkedList<SickMedic> sickMedicList, int x, int y, Area[][] map){
        sickMedicList = sickMedic.killSickMedic(sickMedicList, sickMedic);
        if(map[x][y].getField().getGameObjectReference() != null){
            map[x][y].getField().setGameObjectReference(null);
        }else{
            System.out.println("coś jest nie tak w uzdrowieniu medyka (usuwanie Chorego medyka)");
        }
        SickMedic.setNumberOfSickMedic(SickMedic.getNumberOfSickMedic()-1);
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
        Medic.setNumberOfMedics(Medic.getNumberOfMedics()+1);
        return medicList;
    }



}
