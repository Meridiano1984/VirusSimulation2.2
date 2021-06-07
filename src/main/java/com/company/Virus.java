package com.company;

public class Virus {
    private int deathRateOfSick;
    private int deathRateOfSickMedic;

    public int getDeathRateOfSick() {
        return deathRateOfSick;
    }

    public void setDeathRateOfSick(int deathRateOfSick) {
        this.deathRateOfSick = deathRateOfSick;
    }

    public int getDeathRateOfSickMedic() {
        return deathRateOfSickMedic;
    }

    public void setDeathRateOfSickMedic(int deathRateOfSickMedic) {
        this.deathRateOfSickMedic = deathRateOfSickMedic;
    }

    public Virus(int deathRateOfSick, int deathRateOfSickMedic) {
        this.deathRateOfSick = deathRateOfSick;
        this.deathRateOfSickMedic = deathRateOfSickMedic;
    }
}
