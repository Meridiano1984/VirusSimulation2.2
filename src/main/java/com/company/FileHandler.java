package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public void programDataFileSave(String patch,int numberOfProgramSetUp ){

        FileWriter fileWriter;
        //numberOfProgramSetUp++;

        try{
            fileWriter = new FileWriter(patch);
            fileWriter.write(Integer.toString(numberOfProgramSetUp));
            fileWriter.close();
        } catch (IOException ioe){
            System.out.println("Zapis do pliku z scieżki: "+ patch+ " nie udal sie");
        }
    }


    public int programDataFileRead (String patch){
        String text;
        int numberOfProgramSetUp ;
        BufferedReader fileReader;

        try{
            fileReader = new BufferedReader(new FileReader(patch));
            text = fileReader.readLine();
            numberOfProgramSetUp = Integer.parseInt(text);
            fileReader.close();
            if(numberOfProgramSetUp==1){
                numberOfProgramSetUp++;
            }
            return numberOfProgramSetUp;

        } catch(IOException ioe){

            System.out.println("Odczyt z pliku w sciezce: "+patch+" nie udał sie");
            return -1;
        }
    }
}
