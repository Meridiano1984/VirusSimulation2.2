package com.company;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /*ODCZYTANIE ILOSCI ODTWORZEN SYMULACJI UTWORZENIE PLIKOW ZBIERAJACYCH DANE Z SYMULACJI*/
        FileInformations fileInformations;
        FileCreator fileCreator =new FileCreator();
        FileHandler fileHandler = new FileHandler();
        fileInformations = fileCreator.fileInitialization();
        String sentence = new String();


        /*ZADEKLAROWNIE I INICJALIZACJIA WIEKSZOSCI ZMIENNYCH PRYMITYWNYCH UZYWANYCH W PROGRAMIE */
        int size = 20;
        int numberOfHealthy =20 ;
        int numberOfSick = 20;
        int numberOfMedic = 20;
        int numberOfSickMedic =20;
        int numberOfObstacle =20;
        int deathRateSick =2;
        int iteration=100;
        int numberOfObjects =numberOfHealthy+numberOfMedic+numberOfSickMedic+numberOfObstacle+numberOfSick;
        Scanner scanner = new Scanner(System.in);


        /*WYSWIETLENIE MENU I POBRANIE ZMIENNYCH DOTYCZACYCH SYMULACJI OD UZYTKOWNIKA*/
        boolean end=true;
        do {
            try {
                InputMismatchException iae;

                System.out.println("                                            SYMULACJA PANDEMII                        ");
                System.out.println("PODAJE DANE DOTYCZĄCE SYMULACJI");
                System.out.print("ROZMIAR POLA(KWADRAT, MINIMALNY ROZMIAR TO 2, A MAKSYMALNY 80): ");
                size =scanner.nextInt();
                if(size<2 || size>80){throw new InputMismatchException();}
                System.out.print("LICZBA ZDROWYCH: ");
                numberOfHealthy = scanner.nextInt();
                if(numberOfHealthy<0){throw new InputMismatchException();}
                System.out.print("LICZBA CHORYCH: ");
                numberOfSick = scanner.nextInt();
                if(numberOfSick<0){throw new InputMismatchException();}
                System.out.print("LICZBA MEDYKÓW: ");
                numberOfMedic = scanner.nextInt();
                if(numberOfMedic<0){throw new InputMismatchException();}
                System.out.print("LICZBA CHORYCH MEDYKÓW(SUGEROWANA=0): ");
                numberOfSickMedic = scanner.nextInt();
                if(numberOfSickMedic<0){throw new InputMismatchException();}
                System.out.print("LICZBA PRZESZKÓD: ");
                numberOfObstacle = scanner.nextInt();
                if(numberOfObstacle<0){throw new InputMismatchException();}
                System.out.print("SMIERTELNOSC WIRUSA (WYRAZONA W PUNKTACH PROCENTOWYCH): ");
                deathRateSick = scanner.nextInt();
                if(deathRateSick<0){throw new InputMismatchException();}
                System.out.print("LICZBA TUR: ");
                iteration = scanner.nextInt();
                if(iteration<0){throw new InputMismatchException();}


                end = false;
                numberOfObjects =numberOfHealthy+numberOfMedic+numberOfSickMedic+numberOfObstacle+numberOfSick;
                if(size*size<numberOfObjects){
                    System.out.println("");
                    System.out.println("SUMA PODANYCH PRZEZ CIEBIE OBIEKTOW JEST WIEKSZ NIZ ROZMIAR POLA, UZUPELNIJ POLA JESZCZE RAZ");
                    System.out.println("");
                    end=true;
                }


            } catch (InputMismatchException iae) {
                System.out.println("");
                System.out.println("Argument który wprowaddziłeś nie jest prawidłowy, wprowadź go od nowa");
                System.out.println("");
            }
            scanner.nextLine();
        }while (end);




        Virus virus = new Virus(deathRateSick);        //     INICJALIZACJA KLASY WIRUS
        numberOfObjects =numberOfHealthy+numberOfMedic+numberOfSickMedic+numberOfObstacle+numberOfSick;                                           //     WYBIERAMY LICZBE OBIEKTOW
        GameObjectList gameObjectList = new GameObjectList(numberOfObjects, IHealthyCreator.createHealthy(numberOfHealthy), IObstacleCreator.createObstacle(numberOfObstacle), IMedicCreator.createMedic(numberOfMedic), ISickCreator.createSick(numberOfSick, virus), ISickMedicCreator.createSickMedic(numberOfSickMedic, virus), virus);
//      LinkedList<GameObject> bigList = gameObjectList.bigListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList(), gameObjectList.getSickMedicList());                                                                                                                                                 // utworzeni listy zawierajace wszystkie gameobjecty z wyjatkiem obstacle sluży do przmeiszczania obiektow
        LinkedList<GameObject> movingList = gameObjectList.movingListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList());
        Area[][] map = Area.mapGenerator(size, size);                                  //TWORZENIE MAPY O WYMIARACH size NA size ZA POMOCA SPECJALNEJ FUNKCJI



        SickHandler sickHandler = new SickHandler(map, null, size);                  //TWORZENIE OBIEKTOW HENDLEROW NA KTORYCH RZECZ BEDA WYWOLYWANE FUNKCJE
        HealthyHandler healthyHandler = new HealthyHandler(map, null, size);
        SickMedicHandler sickMedicHandler = new SickMedicHandler(map, null, size);
        MedicHandler medicHandler = new MedicHandler(map, null, size);
        MoveHandler moveHandler = new MoveHandler(map, null);


        map = map[0][0].mapGameObjectInitialization(map, gameObjectList.getObstacleList(), gameObjectList.getHealthyList(), gameObjectList.getSickList(), gameObjectList.getMedicList(), gameObjectList.getSickMedicList(), size);       //INICJALIZOWANIE MAPY OBIEKATMI Z LIST


        Window window = new Window(map,numberOfObjects,numberOfHealthy,numberOfSick,numberOfMedic,numberOfSickMedic,numberOfObstacle,gameObjectList, virus);
        window.repaint();
        Thread.sleep(2000);

        sentence = ("Tura nr " + (0) + "\nIlość obiektów: " + numberOfObjects +"\nIlość chorych: " + numberOfSick + "\nIlość zdrowych: " + numberOfHealthy + "\nIlość Medyków: " + numberOfMedic + "\nIlość Chorych Medyków: " + numberOfSickMedic + "\nIlość przeszkód: " + numberOfObstacle +"\n\n");
        fileHandler.programDataFileWriter(sentence,fileInformations);

        Area.MapDisplay(map, size);
        for (int i = 0; i <iteration || Area.isEnd(gameObjectList); i++) {


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

            sickMedicHandler.checkingNumberOfIteration(gameObjectList.getSickMedicList());

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


            if(Area.isEnd(gameObjectList)){
                break;
            }

            Area.numberOfIteration++;
            window.repaint();
            Thread.sleep(2000);

        }
        window.repaint();

    }
}