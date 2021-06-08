package com.company;

import java.util.LinkedList;

public class SickMedic implements GameObject {

    private static int numberOfSickMedic = 0;
    private Virus virus;
    private final int treatmentDuration = 3;
    public int numberOfIteration=0;

    public SickMedic(Virus virus) {
        this.virus = virus;
        numberOfSickMedic++;
    }

    public static int getNumberOfSickMedic() { return numberOfSickMedic; }
    public static void setNumberOfSickMedic(int numberOfSickMedic) { SickMedic.numberOfSickMedic = numberOfSickMedic; }

    public void deathOfSickMedic(SickMedic sickMedic){}

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }


    protected LinkedList<SickMedic> killSickMedic(LinkedList<SickMedic> sickMedicList, SickMedic sickMedicToKill){

        for(int i=0;i<sickMedicList.size();i++){

            if(sickMedicList.get(i)==sickMedicToKill){
                sickMedicList.remove(sickMedicToKill);
            }

        }
        return sickMedicList;
    }

}
