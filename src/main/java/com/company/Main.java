package com.company;

import com.company.Creators.*;
import com.company.File.FileCreator;
import com.company.File.FileHandler;
import com.company.File.FileInformations;
import com.company.Handler.*;
import com.company.Objects.*;

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


        /*WYSWIETLENIE MENU I POBRANIE ZMIENNYCH DOTYCZACYCH SYMULACJI OD UZYTKOWNIKA. MENU JEST ZABEZPIECZONE PRZED WPISYWANIEM NIEODPOWIEDNICH WARTOSCI*/
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



        //TWORZENIE WIRUSA O ZADANEJ SMIERTELNOSCI
        Virus virus = new Virus(deathRateSick);

        //SUMWOWANIE LICZBY OBIEKTOW WYSTEPUJACYCH W SYMULACJI
        numberOfObjects =numberOfHealthy+numberOfMedic+numberOfSickMedic+numberOfObstacle+numberOfSick;

        //TWORZENIE LIST ZAWIERJACYCH OBIEKTY WYSTEPUJACE W SYMULACJI
        GameObjectList gameObjectList = new GameObjectList(numberOfObjects, IHealthyCreator.createHealthy(numberOfHealthy), IObstacleCreator.createObstacle(numberOfObstacle), IMedicCreator.createMedic(numberOfMedic), ISickCreator.createSick(numberOfSick, virus), ISickMedicCreator.createSickMedic(numberOfSickMedic, virus), virus);

        //TWORZENIE MAPY NA KTOREJ BEDA PORUSZAC SIE OBIEKTY
        Area[][] map = Area.mapGenerator(size, size);




        //TWORZENIE HANDLEROW ODPOWIADJACYCH ZA AKCJE DANYCH OBIEKTOW
        SickHandler sickHandler = new SickHandler(map, null, size);
        HealthyHandler healthyHandler = new HealthyHandler(map, null, size);
        SickMedicHandler sickMedicHandler = new SickMedicHandler(map, null, size);
        MedicHandler medicHandler = new MedicHandler(map, null, size);
        MoveHandler moveHandler = new MoveHandler(map, null);

        //ROZMIESZCZENIE OBIEKTOW Z LIST NA TABLICE
        map = map[0][0].mapGameObjectInitialization(map, gameObjectList.getObstacleList(), gameObjectList.getHealthyList(), gameObjectList.getSickList(), gameObjectList.getMedicList(), gameObjectList.getSickMedicList(), size);       //INICJALIZOWANIE MAPY OBIEKATMI Z LIST

        //DEKALRACJA I INCJALIZACJIA OKNA GRAFICZNEGO WYSWIETLAJACEGO SYMULACJE
        Window window = new Window(map,numberOfObjects,numberOfHealthy,numberOfSick,numberOfMedic,numberOfSickMedic,numberOfObstacle,gameObjectList, virus);
        window.repaint();
        Thread.sleep(2000);

        //ZAPIS DANYCH DO PLIKU .TXT
        sentence = ("Tura nr " + (0) + "\nIlość obiektów: " + numberOfObjects +"\nIlość chorych: " + numberOfSick + "\nIlość zdrowych: " + numberOfHealthy + "\nIlość Medyków: " + numberOfMedic + "\nIlość Chorych Medyków: " + numberOfSickMedic + "\nIlość przeszkód: " + numberOfObstacle +"\n\n");
        fileHandler.programDataFileWriter(sentence,fileInformations);

        //WYSWITLENIE MAPY W KONSOLI PO INICJALIZACJI OBIEKTAMI
        Area.MapDisplay(map, size);


        //WLASCIWE ROZPOCZECIE DZIALANIA SYMULACJI
        for (int i = 0; i <iteration || Area.isEnd(gameObjectList); i++) {

            //ZEROWANIE LICZNIKOW ZAPISUJACYCH DANE
            Medic.medicToFile = 0;
            SickMedic.sickMedicToFile = 0;
            Healthy.healthyToFile = 0;
            Sick.sickToFile = 0;
            SickMedic.deathSickMedicToFile = 0;
            Sick.deathSickToFile = 0;

            //RUCH HEALTHY ZASIEG 2 POLA
            map = moveHandler.twoFieldHealthyMoveHandler(map, size);

            //RUCH MEDIC ZASIEG 2 POLA
            map = moveHandler.twoFieldMedicMoveHandler(map, size);

            //RUCH SICK ZASIEG 1 POLE
            map = moveHandler.oneFieldSickMoveHandler(map, size);

            //ZARAZANIE. ZAMIANA ZDROWYCH W SICK JESLI W ZASIEGU JEDNEGO POLA ZNADUJE SIE CHORY
            healthyHandler.transformationToSick(map,virus,gameObjectList.getHealthyList(),gameObjectList.getSickList(),gameObjectList);

            //ZARAZANIE MEDIC. ZAMIANA W SICKMEDIC JESLI W ZASIEGU JEDNEGO POLA ZNAJDUJA SIE CO NAJMNIEJ 3 SICK
            medicHandler.transformationToSickMedic(map, virus, gameObjectList.getMedicList(), gameObjectList.getSickMedicList(),gameObjectList);

            //ZWIEKSZENIE WARTOSCI LICZNIKA CZASU ZARAZENIA SICKMEDIC O JEDEN (MAX 3)
            sickMedicHandler.checkingNumberOfIteration(gameObjectList.getSickMedicList());

            //SMIERC LUB OZDROWIENIE. SPRAWDZENIE CZY PO 3 TURACH OD ZARAZENIA SICKMEDIC W JEGO OTOCZENIU ZNAJDUJE SIE 3 LUB WIECEJ SICK JESLI TAK OBIEKT GINIE W PRZECIWNYM RAZIE ZAMIENIA SIE W MEDIC
            sickMedicHandler.transformationToMedicOrDying(map,gameObjectList.getMedicList(),gameObjectList.getSickMedicList(),medicHandler,size,gameObjectList);

            //ULECZENIE SICK. JESLI W ODLEGLOSCI 2 POL OD SICK ZNAJDUJE SIE MEDICK TO SICK ZOSTAJE ZASTAPIONY HEALTHY
            sickHandler.transformationToHealthy(map, gameObjectList.getHealthyList(), gameObjectList.getSickList(),gameObjectList);

            //SMIERC SICK. TURA ZABIJAJACA CZESC SCIK
            sickHandler.virusKillSick(map,gameObjectList.getSickList(),gameObjectList);

            //WYSWIETLENIE MAPY W KONSOLI WRAZ Z KROTKIM OPISEM
            System.out.println("");
            System.out.println("KONIEC TURY:"+(i+1)+" SICK: "+ gameObjectList.getSickList().size()+" HEALTHY:"+gameObjectList.getHealthyList().size()+" MEDIC:"+gameObjectList.getMedicList().size()+" SICKMEDIC: "+ gameObjectList.getSickMedicList().size());
            Area.MapDisplay(map, size);

            //ZAPISANIE DANYCH Z DANEJ TURY DO PLIKU
            sentence = ("Tura nr " + (i+1) + "\nWyzdrowiało tyle chorych: " + Healthy.healthyToFile + "\nZachorowało tyle zdrowych: " + Sick.sickToFile + "\nWyleczyło się tyle Lekarzy z choroby: " + Medic.medicToFile + "\nTyle Medyków zachorowało: " + SickMedic.sickMedicToFile + "\nUmarło tyle chorych: " + Sick.deathSickToFile + "\nUmarło tyle zarażonych lekarzy: " + SickMedic.deathSickMedicToFile + "\nIlość chorych: " + gameObjectList.getSickList().size() + "\nIlość zdrowych: " + gameObjectList.getHealthyList().size() + "\nIlość Medyków: " + gameObjectList.getMedicList().size() + "\nIlość Chorych Medyków: " + gameObjectList.getSickMedicList().size() +"\n\n");
            fileHandler.programDataFileWriter(sentence,fileInformations);

            //REGENERACJA OBIEKTOW PO WYKONANIU RUCHU
            gameObjectList.regeneration(gameObjectList);
            gameObjectList.virusSpreadingRegeneration(gameObjectList);

            //SPRAWDZNEIE WARUNKU KONCOWEGO
            if(Area.isEnd(gameObjectList)){
                break;
            }

            //ZWIEKSZENIE LICZNIKA ZAPISUJACEGO LICZBE TUR DO PLIKU
            Area.numberOfIteration++;

            //ZAKTUALIZOWANIE SYTUACJI W OKNIE GRAFICZNYM
            window.repaint();

            //ZATRZYMANIE CZASU NA 2 SEKUNDY ABY UZYTKOWNIK MIAL CZAS ZOBACZYC ZMIANY
            Thread.sleep(2000);

        }

        //WYSWIETLENIE OSTATECZNEJ SYTUACJI W OKNIE GRAFICZNYM
        window.repaint();

    }
}