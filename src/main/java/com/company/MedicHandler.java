package com.company;

import java.util.LinkedList;

public class MedicHandler extends InteractionHandler{

    private Area[][] map;
    private GameObject reference;
    private int size;

    public MedicHandler(Area[][] map, GameObject reference, int size) {
        this.map = map;
        this.reference = reference;
        this.size = size;
    }

    @Override
    public GameObject interaction(){return null;}


    public void transformationToSickMedic(Area[][] map, Virus virus, LinkedList<Medic> medicList, LinkedList<SickMedic> sickMedicList){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j].getField().getGameObjectReference() instanceof Medic) {
                    if(isSickNearby(map, i, j) >= 3){
                        Medic medic = (Medic) map[i][j].getField().getGameObjectReference();
                        deleteMedic(medic,  medicList, i, j, map);
                        addNewSickMedic(virus, sickMedicList, i, j, map);
                    }
                }
            }
        }
    }


    protected int isSickNearby(Area[][] map, int a, int b){
        int numberOfSicks = 0;
        for(int i = a-1; i<= a+1; i++){
            for(int j = b-1; j <= b+1 ; j++){
                if(i >= 0 && j >= 0 && i < size && j< size){
                    if(map[i][j].getField().getGameObjectReference() instanceof Sick){
                        numberOfSicks++;
                    }
                }
            }
        }
        return numberOfSicks;
    }


    // USUWANIE MEDYKA
    private LinkedList<Medic> deleteMedic(Medic medic, LinkedList<Medic> medicList, int x, int y, Area[][] map){
        medicList = medic.killMedic(medicList, medic);
        if(map[x][y].getField().getGameObjectReference() != null){
            map[x][y].getField().setGameObjectReference(null);
        }else{
            System.out.println("coś jest nie tak w zarazeniu medyka (usuwanie medyka)");
        }
        Medic.setNumberOfMedics(Medic.getNumberOfMedics()-1);
        return medicList;
    }


    // DODAWANIE CHOREGO MEDYKA
    private LinkedList<SickMedic> addNewSickMedic(Virus virus, LinkedList<SickMedic> sickMedicList, int x, int y, Area[][] map){

        SickMedic sickMedic = new SickMedic(virus,0);
        sickMedicList.add(sickMedic);
 //       sickMedicList = sickMedic.addSickMedic(sickMedicList);
        if(map[x][y].getField().getGameObjectReference() == null){
            map[x][y].getField().setGameObjectReference(sickMedic);
        }else {
            System.out.println("coś jest nie tak w zarazaniu medyka (dodawanie chorego medyka)");
        }
        SickMedic.setNumberOfSickMedic(SickMedic.getNumberOfSickMedic()+1);
        return sickMedicList;
    }

}
