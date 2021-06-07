package com.company;

public class Field {

    private GameObject gameObjectReference;

    public Field(GameObject gameObjectReference) {
        this.gameObjectReference = gameObjectReference;
    }

    public GameObject getGameObjectReference() {
        return gameObjectReference;
    }

    public void setGameObjectReference(GameObject gameObjectReference) {
        this.gameObjectReference = gameObjectReference;
    }
}
