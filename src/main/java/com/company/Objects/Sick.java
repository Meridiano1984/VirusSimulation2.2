package com.company.Objects;

import com.company.Virus;

public class Sick implements GameObject {

    private static int numberOfSick = 0;
    private Virus virus;
    private boolean isMoved;
    private boolean isVirusSpread;
    public static int sickToFile =0;
    public static int deathSickToFile = 0;

    public Sick(Virus virus, boolean isMoved, boolean isVirusSpread) {
        this.virus = virus;
        this.isMoved = isMoved;
        this.isVirusSpread = isVirusSpread;
        numberOfSick++;
    }

    public static int getNumberOfSick() { return numberOfSick; }
    public static void setNumberOfSick(int numberOfSick) { Sick.numberOfSick = numberOfSick; }
    public boolean isMoved() { return isMoved; }
    public void setMoved(boolean moved) { isMoved = moved; }
    public boolean isVirusSpread() {
        return isVirusSpread;
    }
    public void setVirusSpread(boolean virusSpread) {
        isVirusSpread = virusSpread;
    }

    public void deathOfSick (Sick sick){}

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }

}




