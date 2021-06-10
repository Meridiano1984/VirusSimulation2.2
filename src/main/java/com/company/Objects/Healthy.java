package com.company.Objects;

public class Healthy implements GameObject {

    private static int numberOfHealthy = 0;
    private boolean isMoved;
    public static int healthyToFile =0;

    public Healthy(boolean isMoved) {
        this.isMoved = isMoved;
        numberOfHealthy++;
    }

    public static int getNumberOfHealthy() { return numberOfHealthy;}
    public static void setNumberOfHealthy(int numberOfHealthy) { Healthy.numberOfHealthy = numberOfHealthy; }
    public boolean isMoved() { return isMoved; }
    public void setMoved(boolean moved) { isMoved = moved; }


}
