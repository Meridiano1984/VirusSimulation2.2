package com.company;

import java.util.LinkedList;
import java.util.Random;

public class MoveHandler extends InteractionHandler {
    private Area[][] map;
    private LinkedList<GameObject> referenceList;


    public MoveHandler(Area[][] map, LinkedList<GameObject> reference) {
        this.map = map;
        this.referenceList = reference;
    }

    public Area[][] getMap() {
        return map;
    }

    public void setMap(Area[][] map) {
        this.map = map;
    }

    public LinkedList<GameObject> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(LinkedList<GameObject> referenceList) {
        this.referenceList = referenceList;
    }

    @Override
    protected GameObject interaction(){
        return null;
    }


    public Area[][] moveTour (Area[][] map, LinkedList<GameObject> gameObjectsList) {

        int i = 0;

        for (GameObject gameObject : gameObjectsList) {
            Random random = new Random();
            int positionX;
            int positionY;

            do {

                positionX = random.nextInt(50);
                positionY = random.nextInt(50);

            } while (map[positionX][positionY].getField().getGameObjectReference() != null);

            map[positionX][positionY].getField().setGameObjectReference(gameObject);

            if (gameObject instanceof Sick) {
                System.out.println("HEALTHY X: " + positionX + " Y: " + positionY);
            } else if (gameObject instanceof Sick) {
                System.out.println("SICK X: " + positionX + " Y: " + positionY);
            } else if (gameObject instanceof Medic) {
                System.out.println("MEDIC X: " + positionX + " Y: " + positionY);
            } else if (gameObject instanceof SickMedic) {
                System.out.println("SICKMEDIC X: " + positionX + " Y: " + positionY);
            }
        }

        return map;
    }

    public Area[][] twoFieldHealthyMoveHandler (Area[][] map, int size){
//
//        int t=0;
//        int k=0;
        for(int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++) {

                if (map[i][j].getField().getGameObjectReference()!= null  &&  map[i][j].getField().getGameObjectReference() instanceof Healthy && ((Healthy) map[i][j].getField().getGameObjectReference()).isMoved()==false) {
//                    t++;
                    int positionX = map[i][j].getPositionX();
                    int newPositionX=0;
                    int positionY = map[i][j].getPositionY();
//                    System.out.println("Pozycja obiektu nr: " + t + " X: " + positionX + " Y: "+positionY);
                    int newPositionY=0;
                    int randomField;
                    Random random = new Random();

                    Integer[] tab = new Integer[48];

                    tab[0] = positionX-2;                tab[1] = positionY-2;
                    tab[2] = positionX-1;                tab[3] = positionY-2;
                    tab[4] = positionX;                tab[5] = positionY-2;
                    tab[6] = positionX+1;                tab[7] = positionY-2;
                    tab[8] = positionX+2;                tab[9] = positionY-2;
                    tab[10] = positionX-2;                tab[11] = positionY-1;
                    tab[12] = positionX-1;                tab[13] = positionY-1;
                    tab[14] = positionX;                tab[15] = positionY-1;
                    tab[16] = positionX+1;                tab[17] = positionY-1;
                    tab[18] = positionX+2;                tab[19] = positionY-1;
                    tab[20] = positionX-2;                tab[21] = positionY;
                    tab[22] = positionX-1;                tab[23] = positionY;
                    tab[24] = positionX+1;                tab[25] = positionY;
                    tab[26] = positionX+2;                tab[27] = positionY;
                    tab[28] = positionX-2;                tab[29] = positionY+1;
                    tab[30] = positionX-1;                tab[31] = positionY+1;
                    tab[32] = positionX;                tab[33] = positionY+1;
                    tab[34] = positionX+1;                tab[35] = positionY+1;
                    tab[36] = positionX+2;                tab[37] = positionY+1;
                    tab[38] = positionX-2;                tab[39] = positionY+2;
                    tab[40] = positionX-1;                tab[41] = positionY+2;
                    tab[42] = positionX;                tab[43] = positionY+2;
                    tab[44] = positionX+1;                tab[45] = positionY+2;
                    tab[46] = positionX+2;                tab[47] = positionY+2;


                    do {
//                        k=0;
                        do {
                            randomField = random.nextInt(48);
                            if (randomField % 2 == 0 && randomField != 0) {

//                                System.out.println(randomField);
                                newPositionX = tab[randomField];
                                newPositionY = tab[randomField - 1];

                            }
                            if (randomField == 0) {
//                                System.out.println(randomField);

                                newPositionX = tab[randomField];
                                newPositionY = tab[randomField + 1];
                            }
                            if (randomField % 2 == 1) {
//                                System.out.println(randomField);
                                newPositionX = tab[randomField - 1];
                                newPositionY = tab[randomField];
                            }
//
//                            k++;
//                            System.out.println("PETLA WYKONALA SIE: " + k + " a randomField: " + randomField);
//                            System.out.println("Pozycja obiektu nr: " + t + " X: " + positionX + " Y: "+positionY);
//                            System.out.println("Pozycja po zamianie   X: "+ newPositionX+ " Y: "+ newPositionY);
                        } while (!(newPositionX >= 0 && newPositionX < size && newPositionY >= 0 && newPositionY < size));
//                        System.out.println("Pozycja po zamianie   X: "+ newPositionX+ " Y: "+ newPositionY);
//                        if(map[newPositionX][newPositionY].getField().getGameObjectReference()==null){
//                            System.out.println("                                        WOLNE");
//                        } else if(map[newPositionX][newPositionY].getField().getGameObjectReference()!=null){
//                            System.out.println("                                        ZAJETE");
//                        }

                    }while(map[newPositionX][newPositionY].getField().getGameObjectReference()!=null);


                    GameObject gameObjectReference = map[positionX][positionY].getField().getGameObjectReference();
//                    if(map[positionX][positionY].getCzlowiek()==null){
//                        System.out.println("WOLNE");
//                    } else if(map[positionX][positionY].getCzlowiek()!=null){
//                        System.out.println("ZAJETE");
//                    }
                    map[i][j].getField().setGameObjectReference(null);
                    map[newPositionX][newPositionY].getField().setGameObjectReference(gameObjectReference);
                    ((Healthy)map[newPositionX][newPositionY].getField().getGameObjectReference()).setMoved(true);
//                    System.out.println("IF wykonal sie: "+ t);

                }
            }
        }

        return map;
    }

    public Area[][] twoFieldMedicMoveHandler (Area[][] map, int size){

        int t=0;
        int k=0;
        for(int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++) {

                if (map[i][j].getField().getGameObjectReference()!= null  &&  map[i][j].getField().getGameObjectReference() instanceof Medic && ((Medic) map[i][j].getField().getGameObjectReference()).isMoved()==false) {
                    t++;
                    int positionX = map[i][j].getPositionX();
                    int newPositionX=0;
                    int positionY = map[i][j].getPositionY();
                    //System.out.println("Pozycja obiektu nr: " + t + " X: " + positionX + " Y: "+positionY);
                    int newPositionY=0;
                    int randomField;
                    Random random = new Random();

                    Integer[] tab = new Integer[48];

                    tab[0] = positionX-2;                tab[1] = positionY-2;
                    tab[2] = positionX-1;                tab[3] = positionY-2;
                    tab[4] = positionX;                tab[5] = positionY-2;
                    tab[6] = positionX+1;                tab[7] = positionY-2;
                    tab[8] = positionX+2;                tab[9] = positionY-2;
                    tab[10] = positionX-2;                tab[11] = positionY-1;
                    tab[12] = positionX-1;                tab[13] = positionY-1;
                    tab[14] = positionX;                tab[15] = positionY-1;
                    tab[16] = positionX+1;                tab[17] = positionY-1;
                    tab[18] = positionX+2;                tab[19] = positionY-1;
                    tab[20] = positionX-2;                tab[21] = positionY;
                    tab[22] = positionX-1;                tab[23] = positionY;
                    tab[24] = positionX+1;                tab[25] = positionY;
                    tab[26] = positionX+2;                tab[27] = positionY;
                    tab[28] = positionX-2;                tab[29] = positionY+1;
                    tab[30] = positionX-1;                tab[31] = positionY+1;
                    tab[32] = positionX;                tab[33] = positionY+1;
                    tab[34] = positionX+1;                tab[35] = positionY+1;
                    tab[36] = positionX+2;                tab[37] = positionY+1;
                    tab[38] = positionX-2;                tab[39] = positionY+2;
                    tab[40] = positionX-1;                tab[41] = positionY+2;
                    tab[42] = positionX;                tab[43] = positionY+2;
                    tab[44] = positionX+1;                tab[45] = positionY+2;
                    tab[46] = positionX+2;                tab[47] = positionY+2;


                    do {
                        k=0;
                        do {
                            randomField = random.nextInt(48);
                            if (randomField % 2 == 0 && randomField != 0) {

//                                System.out.println(randomField);
                                newPositionX = tab[randomField];
                                newPositionY = tab[randomField - 1];

                            }
                            if (randomField == 0) {
//                                System.out.println(randomField);

                                newPositionX = tab[randomField];
                                newPositionY = tab[randomField + 1];
                            }
                            if (randomField % 2 == 1) {
//                                System.out.println(randomField);
                                newPositionX = tab[randomField - 1];
                                newPositionY = tab[randomField];
                            }
//
                            k++;
//                            System.out.println("PETLA WYKONALA SIE: " + k + " a randomField: " + randomField);
//                            System.out.println("Pozycja obiektu nr: " + t + " X: " + positionX + " Y: "+positionY);
//                            System.out.println("Pozycja po zamianie   X: "+ newPositionX+ " Y: "+ newPositionY);
                        } while (!(newPositionX >= 0 && newPositionX < size && newPositionY >= 0 && newPositionY < size));
                        //System.out.println("Pozycja po zamianie   X: "+ newPositionX+ " Y: "+ newPositionY);
//                        if(map[newPositionX][newPositionY].getField().getGameObjectReference()==null){
//                            System.out.println("                                        WOLNE");
//                        } else if(map[newPositionX][newPositionY].getField().getGameObjectReference()!=null){
//                            System.out.println("                                        ZAJETE");
//                        }

                    }while(map[newPositionX][newPositionY].getField().getGameObjectReference()!=null);


                    GameObject gameObjectReference = map[positionX][positionY].getField().getGameObjectReference();
//                    if(map[positionX][positionY].getCzlowiek()==null){
//                        System.out.println("WOLNE");
//                    } else if(map[positionX][positionY].getCzlowiek()!=null){
//                        System.out.println("ZAJETE");
//                    }
                    map[i][j].getField().setGameObjectReference(null);
                    map[newPositionX][newPositionY].getField().setGameObjectReference(gameObjectReference);
                    ((Medic)map[newPositionX][newPositionY].getField().getGameObjectReference()).setMoved(true);
//                    System.out.println("IF wykonal sie: "+ t);

                }
            }
        }

        return map;
    }

    public Area[][] oneFieldSickMoveHandler (Area[][] map, int size){

        int t=0;
        int k=0;
        for(int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++) {

                if (map[i][j].getField().getGameObjectReference()!= null  &&  map[i][j].getField().getGameObjectReference() instanceof Sick && ((Sick) map[i][j].getField().getGameObjectReference()).isMoved()==false) {
                    t++;
                    int positionX = map[i][j].getPositionX();
                    int newPositionX=0;
                    int positionY = map[i][j].getPositionY();
                    //System.out.println("Pozycja obiektu nr: " + t + " X: " + positionX + " Y: "+positionY);
                    int newPositionY=0;
                    int randomField;
                    Random random = new Random();

                    Integer[] tab = new Integer[16];

                    tab[0] = positionX - 1;                tab[1] = positionY - 1;
                    tab[2] = positionX;                    tab[3] = positionY - 1;
                    tab[4] = positionX + 1;                tab[5] = positionY - 1;
                    tab[6] = positionX + 1;                tab[7] = positionY;
                    tab[8] = positionX + 1;                tab[9] = positionY + 1;
                    tab[10] = positionX;                   tab[11] = positionY + 1;
                    tab[12] = positionX - 1;               tab[13] = positionY + 1;
                    tab[14] = positionX - 1;               tab[15] = positionY;


                    do {
                        k=0;
                        do {
                            randomField = random.nextInt(16);
                            if (randomField % 2 == 0 && randomField != 0) {

//                                System.out.println(randomField);
                                newPositionX = tab[randomField];
                                newPositionY = tab[randomField - 1];

                            }
                            if (randomField == 0) {
//                                System.out.println(randomField);

                                newPositionX = tab[randomField];
                                newPositionY = tab[randomField + 1];
                            }
                            if (randomField % 2 == 1) {
//                                System.out.println(randomField);
                                newPositionX = tab[randomField - 1];
                                newPositionY = tab[randomField];
                            }
//
                            k++;
//                            System.out.println("PETLA WYKONALA SIE: " + k + " a randomField: " + randomField);
//                            System.out.println("Pozycja obiektu nr: " + t + " X: " + positionX + " Y: "+positionY);
//                            System.out.println("Pozycja po zamianie   X: "+ newPositionX+ " Y: "+ newPositionY);
                        } while (!(newPositionX >= 0 && newPositionX < size && newPositionY >= 0 && newPositionY < size));
                        //System.out.println("Pozycja po zamianie   X: "+ newPositionX+ " Y: "+ newPositionY);
//                        if(map[newPositionX][newPositionY].getField().getGameObjectReference()==null){
//                            System.out.println("                                        WOLNE");
//                        } else if(map[newPositionX][newPositionY].getField().getGameObjectReference()!=null){
//                            System.out.println("                                        ZAJETE");
//                        }

                    }while(map[newPositionX][newPositionY].getField().getGameObjectReference()!=null);


                    GameObject gameObjectReference = map[positionX][positionY].getField().getGameObjectReference();
//                    if(map[positionX][positionY].getCzlowiek()==null){
//                        System.out.println("WOLNE");
//                    } else if(map[positionX][positionY].getCzlowiek()!=null){
//                        System.out.println("ZAJETE");
//                    }
                    map[i][j].getField().setGameObjectReference(null);
                    map[newPositionX][newPositionY].getField().setGameObjectReference(gameObjectReference);
                    ((Sick)map[newPositionX][newPositionY].getField().getGameObjectReference()).setMoved(true);
//                    System.out.println("IF wykonal sie: "+ t);

                }
            }
        }

        return map;
    }
}
