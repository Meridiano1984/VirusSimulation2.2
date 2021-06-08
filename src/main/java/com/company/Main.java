package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        FileInformations fileInformations;
        FileCreator fileCreator =new FileCreator();

        fileInformations = fileCreator.fileInitialization();



        int size = 100;                                                       //     ZMIENNA UMOZLIWIAJACA ZMINE ROZMIARU TABLICY
        int numberOfHealthy = 0*5;
        int numberOfSick = 40*5;
        int numberOfMedic = 0*5;
        int numberOfSickMedic =0*5;
        int numberOfObstacle =0*5;
        //int deathRateSick =4;                                          //     TUTAJ ZNANAJDUJE SIE ATRYBUTY KLASY WIRUS TRZBE BEDZIE JE POBIERAC OD CZLOWIEKA
        //int deathRateSickMedic=5;
        Virus virus = new Virus(2 );        //     INICJALIZACJA KLASY WIRUS
        int numberOfObjects =60;                                           //     WYBIERAMY LICZBE OBIEKTOW
        int iteration;
        GameObjectList gameObjectList = new GameObjectList(numberOfObjects, IHealthyCreator.createHealthy(numberOfObjects), IObstacleCreator.createObstacle(numberOfObjects), IMedicCreator.createMedic(numberOfObjects), ISickCreator.createSick(numberOfObjects, virus), ISickMedicCreator.createSickMedic(numberOfObjects, virus), virus);              // INICJALIZUJEMY LISTE OBIEKATMI, ARGUMENTY DO TEJ FUNKCJI SA DOSTARCZNAE PRZEZ FUNKCJE KTORE TWORZA LISTY OBIEKTOW
//        GameObjectList gameObjectList = new GameObjectList(numberOfObjects, IHealthyCreator.createHealthy(numberOfHealthy), IObstacleCreator.createObstacle(numberOfObstacle), IMedicCreator.createMedic(numberOfMedic), ISickCreator.createSick(numberOfSick, virus), ISickMedicCreator.createSickMedic(numberOfSickMedic, virus), virus);
//        LinkedList<GameObject> bigList = gameObjectList.bigListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList(), gameObjectList.getSickMedicList());                                                                                                                                                 // utworzeni listy zawierajace wszystkie gameobjecty z wyjatkiem obstacle sluży do przmeiszczania obiektow
        LinkedList<GameObject> movingList = gameObjectList.movingListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList());
        Window window = new Window();
        //Area[][] map = new Map[50][50];
        Area[][] map = Area.mapGenerator(size, size);                                  //TWORZENIE MAPY O WYMIARACH 50 NA 50 ZA POMOCA SPECJALNEJ FUNKCJI

//        MoveHandler moveTour = new MoveHandler(map, bigList);
// TWORZENIE OBIEKTU WYWOLUJACEGO CHODZENIE


        SickHandler sickHandler = new SickHandler(map, null, size);                  //TWORZENIE OBIEKTOW HENDLEROW NA KTORYCH RZECZ BEDA WYWOLYWANE FUNKCJE
        HealthyHandler healthyHandler = new HealthyHandler(map, null, size);
        SickMedicHandler sickMedicHandler = new SickMedicHandler(map, null, size);
        MedicHandler medicHandler = new MedicHandler(map, null, size);
        MoveHandler moveHandler = new MoveHandler(map, null);


        map = map[0][0].mapGameObjectInitialization(map, gameObjectList.getObstacleList(), gameObjectList.getHealthyList(), gameObjectList.getSickList(), gameObjectList.getMedicList(), gameObjectList.getSickMedicList(), size);       //INICJALIZOWANIE MAPY OBIEKATMI Z LIST


        //PRZYKLADOWE PARE TUR PRZEMIESZCZANIA//
        Area.MapDisplay(map, size);
        for (int i = 0; i < 10; i++) {
            map = moveHandler.twoFieldHealthyMoveHandler(map, size);
            map = moveHandler.twoFieldMedicMoveHandler(map, size);
            map = moveHandler.oneFieldSickMoveHandler(map, size);

//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Ruch                              TURA NR:"+(i+1)+   " TYLE SICK: " +gameObjectList.getSickList().size()+ "    TYLE HEALTHY:"  + gameObjectList.getHealthyList().size()  );
//            Area.MapDisplay(map, size);
//
            healthyHandler.transformationToSick(map,virus,gameObjectList.getHealthyList(),gameObjectList.getSickList());
////
//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Zarazenie Zdrowego                 TURA NR:"+(i+1)+"   TYLE SICK: " +gameObjectList.getSickList().size()+ "    TYLE HEALTHY: " + gameObjectList.getHealthyList().size()  );
//            Area.MapDisplay(map, size);
//
            medicHandler.transformationToSickMedic(map, virus, gameObjectList.getMedicList(), gameObjectList.getSickMedicList());

//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Zarazenie Medyka                    TURA NR:"+(i+1)+"   TYLE SICKMEDIC: " +gameObjectList.getSickMedicList().size()+ "    TYLE MEDIC: " + gameObjectList.getMedicList().size());
//            Area.MapDisplay(map, size);
//

            sickMedicHandler.checkingNumberOfIteration(map, gameObjectList.getSickMedicList(), gameObjectList.getMedicList(), medicHandler, size);

            sickMedicHandler.transformationToMedicOrDying(map,gameObjectList.getMedicList(),gameObjectList.getSickMedicList(),medicHandler,size);
//
//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Leczenie lub usuwanie chorego medyka       TURA NR:" +(i+1) + "    TYLE CHPRYCH MEDYKOW: " + gameObjectList.getSickMedicList().size() + "    TYLE MEDYKOW:" + gameObjectList.getMedicList().size());
//            Area.MapDisplay(map, size);
//
            sickHandler.transformationToHealthy(map, gameObjectList.getHealthyList(), gameObjectList.getSickList());
//
//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Leczenie                             TURA NR:"+(i+1)+"   TYLE SICK: " +gameObjectList.getSickList().size()+ "    TYLE HEALTHY: " + gameObjectList.getHealthyList().size());

            sickHandler.virusKillSick(map,gameObjectList.getSickList());


            System.out.println("");
            System.out.println("KONIEC TURY:"+(i+1)+" SICK: "+ gameObjectList.getSickList().size()+" HEALTHY:"+gameObjectList.getHealthyList().size()+" MEDIC:"+gameObjectList.getMedicList().size()+" SICKMEDIC: "+ gameObjectList.getSickMedicList().size());
            Area.MapDisplay(map, size);

            gameObjectList.regeneration(gameObjectList);
            gameObjectList.virusSpreadingRegeneration(gameObjectList);


        }
    }
}