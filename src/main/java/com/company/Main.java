package com.company;

import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        FileInformations fileInformations;
        FileCreator fileCreator =new FileCreator();
        FileHandler fileHandler = new FileHandler();

        fileInformations = fileCreator.fileInitialization();
        String sentence = new String();


        int size = 10;                                                       //     ZMIENNA UMOZLIWIAJACA ZMINE ROZMIARU TABLICY
        int numberOfHealthy = 5;
        int numberOfSick = 5;
        int numberOfMedic = 5;
        int numberOfSickMedic =5;
        int numberOfObstacle =5;
        int deathRateSick =4;
        int iteration=10;
//     TUTAJ ZNANAJDUJE SIE ATRYBUTY KLASY WIRUS TRZBE BEDZIE JE POBIERAC OD CZLOWIEKA
        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("                                            SYMULACJA PANDEMII                        ");
//        System.out.println("PODAJE DANE DOTYCZĄCE SYMULACJI");
//        System.out.print("LICZBA MEDYKÓW: ");
//        numberOfMedic =(int)scanner.nextInt();
//        System.out.print("LICZBA CHORYCH: ");
//        numberOfSick =scanner.nextInt();
//        System.out.print("LICZBA ZDROWYCH: ");
//        numberOfHealthy =scanner.nextInt();
//        System.out.print("SMIERTELNOSC WIRUSA (WYRAZONA W PUNKTACH PROCENTOEYCH): ");
//        deathRateSick =scanner.nextInt();
//        System.out.print("LICZBA TUR: ");
//        iteration =scanner.nextInt();





        Virus virus = new Virus(deathRateSick);        //     INICJALIZACJA KLASY WIRUS
        int numberOfObjects =numberOfHealthy+numberOfMedic+numberOfSickMedic+numberOfObstacle+numberOfSick;                                           //     WYBIERAMY LICZBE OBIEKTOW
//        GameObjectList gameObjectList = new GameObjectList(numberOfObjects, IHealthyCreator.createHealthy(numberOfObjects), IObstacleCreator.createObstacle(numberOfObjects), IMedicCreator.createMedic(numberOfObjects), ISickCreator.createSick(numberOfObjects, virus), ISickMedicCreator.createSickMedic(numberOfObjects, virus), virus);              // INICJALIZUJEMY LISTE OBIEKATMI, ARGUMENTY DO TEJ FUNKCJI SA DOSTARCZNAE PRZEZ FUNKCJE KTORE TWORZA LISTY OBIEKTOW
        GameObjectList gameObjectList = new GameObjectList(numberOfObjects, IHealthyCreator.createHealthy(numberOfHealthy), IObstacleCreator.createObstacle(numberOfObstacle), IMedicCreator.createMedic(numberOfMedic), ISickCreator.createSick(numberOfSick, virus), ISickMedicCreator.createSickMedic(numberOfSickMedic, virus), virus);
//        LinkedList<GameObject> bigList = gameObjectList.bigListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList(), gameObjectList.getSickMedicList());                                                                                                                                                 // utworzeni listy zawierajace wszystkie gameobjecty z wyjatkiem obstacle sluży do przmeiszczania obiektow
        LinkedList<GameObject> movingList = gameObjectList.movingListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList());
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

        Window window = new Window(map,numberOfObjects,numberOfHealthy,numberOfSick,numberOfMedic,numberOfSickMedic,numberOfObstacle);
        Area.MapDisplay(map, size);
        for (int i = 0; i <iteration; i++) {






            Medic.medicToFile = 0;
            SickMedic.sickMedicToFile = 0;
            Healthy.healthyToFile = 0;
            Sick.sickToFile = 0;
            SickMedic.deathSickMedicToFile = 0;
            Sick.deathSickToFile = 0;

            map = moveHandler.twoFieldHealthyMoveHandler(map, size);
            map = moveHandler.twoFieldMedicMoveHandler(map, size);
            map = moveHandler.oneFieldSickMoveHandler(map, size);

//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Ruch                              TURA NR:"+(i+1)+   " TYLE SICK: " +gameObjectList.getSickList().size()+ "    TYLE HEALTHY:"  + gameObjectList.getHealthyList().size()  );
//            Area.MapDisplay(map, size);
//
            healthyHandler.transformationToSick(map,virus,gameObjectList.getHealthyList(),gameObjectList.getSickList(),gameObjectList);
////
//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Zarazenie Zdrowego                 TURA NR:"+(i+1)+"   TYLE SICK: " +gameObjectList.getSickList().size()+ "    TYLE HEALTHY: " + gameObjectList.getHealthyList().size()  );
//            Area.MapDisplay(map, size);
//
            medicHandler.transformationToSickMedic(map, virus, gameObjectList.getMedicList(), gameObjectList.getSickMedicList(),gameObjectList);

//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Zarazenie Medyka                    TURA NR:"+(i+1)+"   TYLE SICKMEDIC: " +gameObjectList.getSickMedicList().size()+ "    TYLE MEDIC: " + gameObjectList.getMedicList().size());
//            Area.MapDisplay(map, size);
//

            sickMedicHandler.checkingNumberOfIteration(map, gameObjectList.getSickMedicList(), gameObjectList.getMedicList(), medicHandler, size);

            sickMedicHandler.transformationToMedicOrDying(map,gameObjectList.getMedicList(),gameObjectList.getSickMedicList(),medicHandler,size,gameObjectList);
//
//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Leczenie lub usuwanie chorego medyka       TURA NR:" +(i+1) + "    TYLE CHPRYCH MEDYKOW: " + gameObjectList.getSickMedicList().size() + "    TYLE MEDYKOW:" + gameObjectList.getMedicList().size());
//            Area.MapDisplay(map, size);
//
            sickHandler.transformationToHealthy(map, gameObjectList.getHealthyList(), gameObjectList.getSickList(),gameObjectList);
//
//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println("Leczenie                             TURA NR:"+(i+1)+"   TYLE SICK: " +gameObjectList.getSickList().size()+ "    TYLE HEALTHY: " + gameObjectList.getHealthyList().size());

            sickHandler.virusKillSick(map,gameObjectList.getSickList(),gameObjectList);


            System.out.println("");
            System.out.println("KONIEC TURY:"+(i+1)+" SICK: "+ gameObjectList.getSickList().size()+" HEALTHY:"+gameObjectList.getHealthyList().size()+" MEDIC:"+gameObjectList.getMedicList().size()+" SICKMEDIC: "+ gameObjectList.getSickMedicList().size());
            Area.MapDisplay(map, size);

            sentence = ("Tura nr " + (i+1) + "\nWyzdrowiało tyle chorych: " + Healthy.healthyToFile + "\nZachorowało tyle zdrowych: " + Sick.sickToFile + "\nWyleczyło się tyle Lekarzy z choroby: " + Medic.medicToFile + "\nTyle Medyków zachorowało: " + SickMedic.sickMedicToFile + "\nUmarło tyle chorych: " + Sick.deathSickToFile + "\nUmarło tyle zarażonych lekarzy: " + SickMedic.deathSickMedicToFile + "\nIlość chorych: " + gameObjectList.getSickList().size() + "\nIlość zdrowych: " + gameObjectList.getHealthyList().size() + "\nIlość Medyków: " + gameObjectList.getMedicList().size() + "\nIlość Chorych Medyków: " + gameObjectList.getSickMedicList().size() +"\n\n");
            fileHandler.programDataFileWriter(sentence,fileInformations);

            gameObjectList.regeneration(gameObjectList);
            gameObjectList.virusSpreadingRegeneration(gameObjectList);




            window.repaint();

            Thread.sleep(5000);

        }

    }
}