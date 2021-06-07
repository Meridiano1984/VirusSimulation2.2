package com.company;

import java.util.LinkedList;

public class MedicHandler extends InteractionHandler{

    private Area[][] map;
    private GameObject reference;
    private int size;

    public MedicHandler(Area[][] map, GameObject reference) {
        this.map = map;
        this.reference = reference;
    }

    @Override
    public GameObject interaction(){return null;}


    // JAK CHORY MEDYK W POBLIZU >= 5 TO ZAMIANA MEDYKA NA CHOREGO MEDYKA
    public void transformationToSickMedic(Area[][] map, Virus virus, LinkedList<Medic> medicList, LinkedList<SickMedic> sickMedicList){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j].getField().getGameObjectReference() instanceof Medic) {
                    if(isSickNearby(map, i, j) >= 5){
                        Medic medic = (Medic) map[i][j].getField().getGameObjectReference();
                        deleteMedic(medic,  medicList, i, j, map);
                        addNewSickMedic(virus, sickMedicList, i, j, map);
                    }
                }
            }
        }
    }


    public int isSickNearby(Area[][] map, int a, int b){
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
        Medic.numberOfMedics--;
        return medicList;
    }


    // DODAWANIE CHOREGO MEDYKA
    private LinkedList<SickMedic> addNewSickMedic(Virus virus, LinkedList<SickMedic> sickMedicList, int x, int y, Area[][] map){

        SickMedic sickMedic = new SickMedic(virus);
        sickMedicList = sickMedic.addSickMedic(sickMedicList);
        if(map[x][y].getField().getGameObjectReference() == null){
            map[x][y].getField().setGameObjectReference(sickMedic);
        }else {
            System.out.println("coś jest nie tak w zarazaniu medyka (dodawanie chorego medyka)");
        }
        SickMedic.numberOfSickMedic++;
        return sickMedicList;
    }





/*    public Area[][] lookingForTheSick (Area [][] map, int size, LinkedList<Healthy> healthyList, LinkedList<Sick> sickList){
        for (int i = 0; i < size; i++){
            for (int j=0; j < size; j++){
                if (map[i][j].getField().getGameObjectReference() instanceof Medic){
                    if (i==0 && j==0){                                        //lewy górny róg
                        for(int k=0; k<3; k++){
                            for (int m=0; m<3; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==0 && j==1){                 //leczenie gdyby medyk byl pod lewym gornym rogiem
                        for (int k=0; k<3; k++){
                            for (int m=0; m<4; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==0 && j>=2 && j<=(size-3)){        //sprawdzanie dla pozostalych w pionie x = 0, y >= 2, y <= wielkoscTablicy -3
                        for(int k=0; k<3; k++){
                            for(int m=(j-2); m<=j+2; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==0 && j==(size-2)){              //lewy przedostatni dolny
                        for (int k=0; k<3; k++){
                            for (int m=j; m<=j+1; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==0 && j==(size-1)){         //lewy dolny róg
                        for (int k=0; k<3; k++){
                            for (int m=j-2; m<=j; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==1 && j==0){                     //druga kolumna, pierwszy rzad
                        for (int k=0; k<4; k++){
                            for (int m=0; m<3; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==1 && j==1){             //x=1,y=1
                        for (int k=0; k<4; k++){
                            for (int m=0; m<4; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if(i==1 && j>=2 && j<=(size-3)){           //druga kolumna bez dwoch pierwszych i dwoch ostatnich wierszow
                        for(int k=0; k<4; k++){
                            for (int m=(j-2); m<=(j+2); m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==1 && j==(size-2)){              //2 kolumna, przedostatni wiersz
                        for (int k=0; k<4; k++){
                            for (int m=(j-2); m<=(j+1); m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if(i==1 && j==(size-1)){               //druga kolumna, ostatni wiersz
                        for (int k=0; k<4; k++){
                            for (int m=(j-2); m<=j; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (j==0 && i>=2 && i<=(size-3)){          //cały pierwszy wiersz bez dwoch pierwszych i dwoch opstatnich kolumn
                        for (int k=(i-2); k<=(i+2); k++){
                            for (int m=0; m<3; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if(j==1 && i>=2 && i<=(size-3)){       //cały drugi wiersz bez dwóch pierwszy i dwóch ostatnich kolumn
                        for (int k=(i-2); k<=(i+2); k++){
                            for (int m=0; m<4; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (j==(size-2) && i>=2 && i<=(size-3)){           //przedostatni wiersz bez dwoch pierwszych i dwoch ostatnich kolumn
                        for (int k=(i-2); k<=(i+2); k++){
                            for (int m=(j-2); m<=(j+1); m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (j==(size-1) && i>=2 && i<=(size-3)){           //ostatni wiersz bez dwóch pierwszych i dwoch ostatnich kolumn
                        for (int k=(i-2); k<=(i+2); k++){
                            for (int m=(j-2); m<=j; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-2) && j==0){      //przedostatnia kolumna, pierwszy rzad
                        for (int k=(i-2); k<=(i+1); k++){
                            for (int m=0; m<3; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-2) && j==1){              //x jest przedostatni, a y=1
                        for (int k=(i-2); k<=(i+1);k++){
                            for (int m=0; m<4; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-2) && j>=2 && j<=(size-3)){       //przedostatnia kolumna cała oprócz dwóch pierwszych i dwóch ostatnich wierszow
                        for (int k=(i-2); k<=(i+1); k++){
                            for(int m=(j-2); m<=(j+2); m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-2) && j==(size-2)){           //przedostatni wiersz, przedostatnia kolumna
                        for (int k=(i-2); k<=(i+1); k++){
                            for (int m=(j-2); m<=(j+1); m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-2) && j==(size-1)){       //ostatni wiersz, przedostatnia kolumna
                        for (int k=(i-2); k<=(i+1); k++){
                            for (int m=(j-2); m<=j; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-1) && j==0){          //prawy gorny rog
                        for (int k=(i-2); k<=i; k++){
                            for (int m=0; m<3; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-1) && j==1){          //pod prawym gornym rogiem
                        for (int k=(i-2); k<=i; k++){
                            for (int m=0; m<4; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-1) && j>=2 && j<=(size-3)){           //ostatnia kolumna bez dwoch pierwszych i ostatnich wierszow
                        for (int k=(i-2); k<=i; k++){
                            for (int m=(j-2); m<=(j+2); m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-1) && j==(size-2)){           //ostatnia kolumna, przedostatni wiersz
                        for (int k=(i-2); k<=i; k++){
                            for (int m=(j-2); m<=(j+1); m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if (i==(size-1) && j==(size-1)){       //prawy dolny róg
                        for (int k=(i-2); k<=i; k++){
                            for (int m=(j-2); m<=j; m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                    else if(i>=2 && i<=(size-3) && j>=2 && j<=(size-3)){            // cały środek
                        for (int k=(i-2); k<=(i+2); k++){
                            for (int m=(j-2); m<=(j+2); m++){
                                if (map[k][m].getField().getGameObjectReference() instanceof Sick){
                                    Sick sick = (Sick)map[k][m].getField().getGameObjectReference();
                                    sick.transformationToHealthy(sick, healthyList, sickList, k, m, map);
                                }
                            }
                        }
                    }
                }
            }
        }

        return map;
    }*/

}
