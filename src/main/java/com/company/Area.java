package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Area {

    private int positionX;
    private int positionY;
    private Field field;

    public Area(int positionX, int positionY, Field field) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.field = field;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }


    public static Area[][] mapGenerator(int x, int y) {

        Area[][] map = new Area[x][y];
        Field[][] fields = new Field[x][y];


        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                fields[i][j] = new Field(null);
                map[i][j] = new Area(i, j, fields[i][j]);
            }
        }
        return map;
    }


    //LEPSZE WYSWIETLANIE MAPY
    public static void MapDisplay(Area[][] map, int size) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j].getField().getGameObjectReference() == null) {
                    System.out.print(" X");
                } else if (map[i][j].getField().getGameObjectReference() instanceof Healthy) {
                    System.out.print(" H");
                } else if (map[i][j].getField().getGameObjectReference() instanceof Medic) {
                    System.out.print(" M");
                } else if (map[i][j].getField().getGameObjectReference() instanceof Sick) {
                    System.out.print(" S");
                } else if (map[i][j].getField().getGameObjectReference() instanceof Obstacle) {
                    System.out.print(" O");
                } else if (map[i][j].getField().getGameObjectReference() instanceof SickMedic) {
                    System.out.print(" Z");
                }

            }
            System.out.println(" ");
        }
    }


    // FUNKCJA INICJALIZUJAC MAPE
    public static Area[][] mapObstacleInitialization(Area[][] map, List<Obstacle> obstacleList, int size) {

        int positionX;
        int positionY;
        Random random = new Random();
        boolean end = true;
        int i = 0, j = 0, k = 0;

//        for (Obstacle obstacle : obstacleList) {
//
//            i++;
//            do {
//                positionX = random.nextInt(50);
//                positionY = random.nextInt(50);
//
//                if (map[positionX][positionY].getField().getGameObjectReference() == null) {
//
//                    map[positionX][positionY].getField().setGameObjectReference(obstacle);
//                    end =false;
//                    k++;
//                } else {
//                    System.out.println("nie jest NULLEM dla iteracji nr: "+ j + " x: " + positionX + " y: " + positionY);
//                    System.out.println(map[positionX][positionY].getField().getGameObjectReference());
//                    map[positionX][positionY].getField().setGameObjectReference(null);
//                    System.out.println(map[positionX][positionY].getField().getGameObjectReference());
//                }
//
//                j++;
//            }while (end);
//        }

        for (Obstacle obstacle : obstacleList) {

            i++;
            do {
                positionX = random.nextInt(size);
                positionY = random.nextInt(size);
                map[positionX][positionY].getField().getGameObjectReference();
                j++;
            } while (map[positionX][positionY].getField().getGameObjectReference() != null);

            map[positionX][positionY].getField().setGameObjectReference(obstacle);
        }


        System.out.println("Liczba obstacle to: " + i + " petla do while wykonala sie: " + j + " a w niej if wykonal sie: " + k);
        return map;

    }

    public static Area[][] mapHealthyInitialization(Area[][] map, List<Healthy> healthyList, int size) {

        int positionX;
        int positionY;
        Random random = new Random();
        boolean end = true;
        int i = 0;

//        for(Healthy healthy: healthyList){
//
//            i++;
//
//            do {
//                positionX = random.nextInt(50);
//                positionY = random.nextInt(50);
//
//                if (map[positionX][positionY].getField().getGameObjectReference() == null) {
//
//                    map[positionX][positionY].getField().setGameObjectReference(healthy);
//                    end =false;
//                }
//            }while (end);
//        }

        for (Healthy healthy : healthyList) {

            i++;
            do {
                positionX = random.nextInt(size);
                positionY = random.nextInt(size);
                map[positionX][positionY].getField().getGameObjectReference();
            } while (map[positionX][positionY].getField().getGameObjectReference() != null);

            map[positionX][positionY].getField().setGameObjectReference(healthy);
        }

        System.out.println("Liczba heathy to:" + i);

        return map;
    }

    public static Area[][] mapSickInitialization(Area[][] map, List<Sick> sickList, int size) {

        int positionX;
        int positionY;
        Random random = new Random();
        boolean end = true;
        int i = 0;

//        for(Sick sick: sickList){
//
//            i++;
//
//            do {
//                positionX = random.nextInt(50);
//                positionY = random.nextInt(50);
//
//                if (map[positionX][positionY].getField().getGameObjectReference() == null) {
//
//                    map[positionX][positionY].getField().setGameObjectReference(sick);
//                    end =false;
//                }
//            }while (end);
//        }

        for (Sick sick : sickList) {

            i++;
            do {
                positionX = random.nextInt(size);
                positionY = random.nextInt(size);
                map[positionX][positionY].getField().getGameObjectReference();
            } while (map[positionX][positionY].getField().getGameObjectReference() != null);

            map[positionX][positionY].getField().setGameObjectReference(sick);
        }

        System.out.println("Liczba Sick to:" + i);

        return map;
    }

    public static Area[][] mapMedicInitialization(Area[][] map, List<Medic> medicList, int size) {

        int positionX;
        int positionY;
        Random random = new Random();
        boolean end = true;
        int i = 0;

//        for(Medic medic: medicList){
//
//            i++;
//
//            do {
//                positionX = random.nextInt(50);
//                positionY = random.nextInt(50);
//
//                if (map[positionX][positionY].getField().getGameObjectReference() == null) {
//
//                    map[positionX][positionY].getField().setGameObjectReference(medic);
//                    end =false;
//                }
//            }while (end);
//        }

        for (Medic medic : medicList) {

            i++;
            do {
                positionX = random.nextInt(size);
                positionY = random.nextInt(size);
                map[positionX][positionY].getField().getGameObjectReference();
            } while (map[positionX][positionY].getField().getGameObjectReference() != null);

            map[positionX][positionY].getField().setGameObjectReference(medic);
        }


        System.out.println("Liczba Medic to:" + i);

        return map;
    }

    public static Area[][] mapSickMedicInitialization(Area[][] map, List<SickMedic> sickMedicList, int size) {

        int positionX;
        int positionY;
        Random random = new Random();
        boolean end = true;
        int i = 0;

//        for(SickMedic sickMedic: sickMedicList){
//
//            i++;
//
//            do {
//                positionX = random.nextInt(50);
//                positionY = random.nextInt(50);
//
//                if (map[positionX][positionY].getField().getGameObjectReference() == null) {
//
//                    map[positionX][positionY].getField().setGameObjectReference(sickMedic);
//                    end =false;
//                }
//            }while (end);
//        }

        for (SickMedic sickMedic : sickMedicList) {

            i++;
            do {
                positionX = random.nextInt(size);
                positionY = random.nextInt(size);
                map[positionX][positionY].getField().getGameObjectReference();
            } while (map[positionX][positionY].getField().getGameObjectReference() != null);

            map[positionX][positionY].getField().setGameObjectReference(sickMedic);
        }
        System.out.println("Liczba heathy to:" + i);

        return map;
    }

    public static Area[][] mapGameObjectInitialization(Area[][] map, List<Obstacle> obstacleList, List<Healthy> healthyList, List<Sick> sickList, List<Medic> medicList, List<SickMedic> sickMedicList, int size) {

//        System.out.println(" ");
//        System.out.println("Obstacle");
//        System.out.println(" ");
        map = mapObstacleInitialization(map, obstacleList, size);
//        primitiveMapDisplay(map,size);

//        System.out.println(" ");
//        System.out.println("Healthy");
//        System.out.println(" ");
        map = mapHealthyInitialization(map, healthyList, size);
//        primitiveMapDisplay(map,size);

//        System.out.println(" ");
//        System.out.println("Sick");
//        System.out.println(" ");
        map = mapSickInitialization(map, sickList, size);
//        primitiveMapDisplay(map,size);

//        System.out.println(" ");
//        System.out.println("Medic");
//        System.out.println(" ");
        map = mapMedicInitialization(map, medicList, size);
//        primitiveMapDisplay(map,size);

//        System.out.println(" ");
//        System.out.println("SickMedic");
//        System.out.println(" ");
        map = mapSickMedicInitialization(map, sickMedicList, size);
//        primitiveMapDisplay(map,size);

        return map;
    }

    // PRYMITYWNE(słowne) WYSWIETLANIE MAPY
    public static void primitiveMapDisplay(Area[][] map, int size) {

        int numberOfObstacle = 0;
        int numberOfHealthy = 0;
        int numberOfSick = 0;
        int numberOfMedic = 0;
        int numberOfSickMedic = 0;
        int nuberOfNull = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (map[i][j].getField().getGameObjectReference() == null) {
                    System.out.println("X:" + map[i][j].getPositionX() + " Y:" + map[i][j].getPositionY() + " NULL ");
                    nuberOfNull++;
                }

                if (map[i][j].getField().getGameObjectReference() != null) {

                    if (map[i][j].getField().getGameObjectReference() instanceof Obstacle) {
                        System.out.println("X:" + map[i][j].getPositionX() + " Y:" + map[i][j].getPositionY() + "  TU JEST OBSTACLE");
                        numberOfObstacle++;
                    }
                    if (map[i][j].getField().getGameObjectReference() instanceof Healthy) {
                        System.out.println("X:" + map[i][j].getPositionX() + " Y:" + map[i][j].getPositionY() + "  TU JEST HEALTHY");
                        numberOfHealthy++;
                    }
                    if (map[i][j].getField().getGameObjectReference() instanceof Sick) {
                        System.out.println("X:" + map[i][j].getPositionX() + " Y:" + map[i][j].getPositionY() + "  TU JEST SICK");
                        numberOfSick++;
                    }
                    if (map[i][j].getField().getGameObjectReference() instanceof Medic) {
                        System.out.println("X:" + map[i][j].getPositionX() + " Y:" + map[i][j].getPositionY() + "  TU JEST MEDIC");
                        numberOfMedic++;
                    }
                    if (map[i][j].getField().getGameObjectReference() instanceof SickMedic) {
                        System.out.println("X:" + map[i][j].getPositionX() + " Y:" + map[i][j].getPositionY() + "  TU JEST SICKMEDIC");
                        numberOfSickMedic++;
                    }
                }

            }
        }

        int suma = nuberOfNull + numberOfHealthy + numberOfMedic + numberOfSickMedic + numberOfObstacle + numberOfSick;
        System.out.println("OBSTACLE: " + numberOfObstacle);
        System.out.println("HEALTHY: " + numberOfHealthy);
        System.out.println("SICK: " + numberOfSick);
        System.out.println("MEDIC: " + numberOfMedic);
        System.out.println("SICKMEDIC: " + numberOfSickMedic);
        System.out.println("NULL: " + nuberOfNull);
        System.out.println("SUMA = " + suma);

    }


    public static boolean isEnd(GameObjectList gameObjectList) {

        if (gameObjectList.getHealthyList().size() == 0 && gameObjectList.getSickList().size() == 0 && gameObjectList.getSickMedicList().size() == 0 && gameObjectList.getMedicList().size() == 0) {
            return true;
        }
        return false;
    }


}

