package com.company;

import java.util.LinkedList;

public class SickMedicHandler extends InteractionHandler{
    private Area[][] map;
    private GameObject reference;


    public SickMedicHandler(Area[][] map, GameObject reference) {
        this.map = map;
        this.reference = reference;
    }

    public Area[][] checkingNumberOfIteration(Area [][] map, LinkedList<SickMedic> sickMedicList, LinkedList<Medic> medicList, MedicHandler medicHandler, int size){

        for (SickMedic sickMedic: sickMedicList){
            sickMedic.numberOfIteration++;
            if (sickMedic.numberOfIteration==4){
                transformationToMedicOrDying(map, medicList, sickMedicList, medicHandler, size);
            }
        }
        return map;
    }
    public Area[][] transformationToMedicOrDying(Area[][] map, LinkedList<Medic> medicList, LinkedList<SickMedic> sickMedicList, MedicHandler medicHandler, int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (map[i][j].getField().getGameObjectReference() instanceof SickMedic) {
                    SickMedic sickMedic = (SickMedic)map[i][j].getField().getGameObjectReference();
                    if(medicHandler.isSickNearby(map, i, j) >= 5){
                        sickMedic.deleteSickMedic( (SickMedic) map[i][j].getField().getGameObjectReference(),  sickMedicList, i, j, map);
                    }
                    else{
                        sickMedic.transformationToMedic(sickMedic, medicList, sickMedicList, i, j, map);
                    }
                }
            }
        }
        return map;
    }



    @Override
    protected GameObject interaction(){return null;}

}
