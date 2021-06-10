package com.company.File;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCreator {

    public FileInformations fileInitialization (){

        //TWORZYMY OBIEKT FILEINFORMATIONS PRZECHOWUJACY SCIEZKI I DANE ZWIAZNE Z PLIKAMI W NASZYM PROJEKCIE. NASTEPNIE PO KOLEI INICJALIZUJEMY JEGO POJEDNYACZE ZMIENNNE TWORZAC PRZY OKAZJI FOLDERY I PLIKI ORAZ WYAMAGANE W NICH DANE
        FileInformations fileInformations =new FileInformations(null,null,null,null,null,0);
        FileCreator fileCreator = new FileCreator();
        String patchTo;

        //DODANIE SCIEZKI DO PLKU W KTORYM BEDZIE SIE WYSZTKO ZNAJDOWAC
        String absolutePath = new File("").getAbsolutePath();
        fileInformations.setAbsolutePath(absolutePath);

        String directoryName = "DataFile";

        //DODANIE SCIEZKI DO KATALOGU Z RESZTA PLKOW
        fileInformations.setDirectoryPatch(fileCreator.directoryCreator(fileInformations.getAbsolutePath(),directoryName));

        patchTo = fileInformations.getDirectoryPatch();
        String programDataFileName ="ProgramData.txt";

        //STWORZENIE PLIKU Z DANYMI NA TEMAT SYMULACJI I ZMIENIENIE ZMIENNEJ NUMBEROFPROGRAMSETUP
        fileInformations =fileCreator.programDataFileCreator(patchTo,programDataFileName,fileInformations);

        //STWORZENIE KATALOGU NA PLIKI Z DANYMI Z KAZDEJ SYMULACJI
        patchTo = fileInformations.getDirectoryPatch();
        String simulationDataDirectoryName = "SimulationData";

        fileInformations.setSimulationDataDirectoryPatch(fileCreator.simulationDataDirectoryCreator(patchTo,simulationDataDirectoryName));

        //STWORZENIE PLIKU NA DANE Z SYMUACJI
        patchTo = fileInformations.getSimulationDataDirectoryPatch();
        String simulationDataFileName = "SimulationData"+fileInformations.getNumberOfProgramSetUp()+".txt";

        fileInformations.setSimulationDataPatch(fileCreator.simulationDataFileCreator(patchTo,simulationDataFileName));

        return fileInformations;
    }

    private String directoryCreator(String patch, String directoryName){
        String directoryPath = patch +"\\"+ directoryName;
        try {
            Files.createDirectory(Paths.get(directoryPath));
        }catch (FileAlreadyExistsException faee){
            System.out.println("Folder o nazwie: "+directoryName+" już istnieje");

        } catch (IOException ioe){
            System.out.println("Nie udało się utworzyc folderu: "+directoryName);
        }

        return directoryPath;               //PROBLEM PONIEWAZ GDY PLIK NIE ISTNIEJE ZWRACA SCIEZKE DO NIEISTNIEJACEGO PLKI JAK ZROBIC TO LEPEIEJ?
    }


    protected FileInformations  programDataFileCreator (String patch,String programDataName, FileInformations fileInformations ){

        FileHandler fileHandler = new FileHandler();
        String programDataFilePatch = patch+"\\"+programDataName;

        try{
            Files.createFile(Paths.get(programDataFilePatch));
            fileHandler.programDataFileSave(programDataFilePatch,1);
            fileInformations.setNumberOfProgramSetUp(1);

        }catch (FileAlreadyExistsException faee) {
            System.out.println("Plik o nazwie :"+programDataName+" już istnieje");
            fileInformations.setNumberOfProgramSetUp(fileHandler.programDataFileRead(programDataFilePatch));
            fileHandler.programDataFileSave(programDataFilePatch,(fileInformations.getNumberOfProgramSetUp()+1));
            if(fileInformations.getNumberOfProgramSetUp()<0){
                System.out.println("Odczyt z pliku w sciezce: "+patch+" nie udał sie");
            }

        }catch (IOException ioe){
            System.out.println("Nie udało się utworzyć folderu:"+programDataName);
        }

        return fileInformations;
    }

    private String simulationDataDirectoryCreator (String patch, String simulationDataFileName){
        String simulationDataDirectoryPath = patch +"\\"+ simulationDataFileName;

        try {
            Files.createDirectory(Paths.get(simulationDataDirectoryPath));
        }catch (FileAlreadyExistsException faee){
            System.out.println("Folder o nazwie: "+simulationDataFileName+" już istnieje");

        } catch (IOException ioe){
            System.out.println("Nie udało się utworzyc folderu: "+simulationDataFileName);
        }

        return simulationDataDirectoryPath;               //PROBLEM PONIEWAZ GDY PLIK NIE ISTNIEJE ZWRACA SCIEZKE DO NIEISTNIEJACEGO PLKI JAK ZROBIC TO LEPEIEJ?
    }

    private String simulationDataFileCreator (String path, String simulationDataFileName){

        String simulationDataFilePath = path +"\\"+ simulationDataFileName;

        try{
            Files.createFile(Paths.get(simulationDataFilePath));
        } catch (FileAlreadyExistsException faee){
            System.out.println("Plik o nazwie: "+simulationDataFileName+" juz istnieje");
        } catch (IOException ioe) {
            System.out.println("Nie udało się utwożyć pliku: "+simulationDataFileName);
        }

        return simulationDataFilePath;
    }
}
