package com.company;

public class FileInformations {
    private String absolutePath;
    private String directoryPatch;
    private String programDataPatch;
    private String simulationDataDirectoryPatch;
    private String simulationDataPatch;
    private int numberOfProgramSetUp;


    public FileInformations(String absolutePath, String directoryPatch, String programDataPatch, String simulationDataDirectoryPatch, String simulationDataPatch, int numberOfProgramSetUp) {
        this.absolutePath = absolutePath;
        this.directoryPatch = directoryPatch;
        this.programDataPatch = programDataPatch;
        this.simulationDataDirectoryPatch = simulationDataDirectoryPatch;
        this.simulationDataPatch = simulationDataPatch;
        this.numberOfProgramSetUp = numberOfProgramSetUp;
    }

    public int getNumberOfProgramSetUp() {
        return numberOfProgramSetUp;
    }

    public void setNumberOfProgramSetUp(int numberOfProgramSetUp) {
        this.numberOfProgramSetUp = numberOfProgramSetUp;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getDirectoryPatch() {
        return directoryPatch;
    }

    public void setDirectoryPatch(String directoryPatch) {
        this.directoryPatch = directoryPatch;
    }

    public String getProgramDataPatch() {
        return programDataPatch;
    }

    public void setProgramDataPatch(String programDataPatch) {
        this.programDataPatch = programDataPatch;
    }

    public String getSimulationDataDirectoryPatch() {
        return simulationDataDirectoryPatch;
    }

    public void setSimulationDataDirectoryPatch(String simulationDataDirectoryPatch) {
        this.simulationDataDirectoryPatch = simulationDataDirectoryPatch;
    }

    public String getSimulationDataPatch() {
        return simulationDataPatch;
    }

    public void setSimulationDataPatch(String simulationDataPatch) {
        this.simulationDataPatch = simulationDataPatch;
    }
}
