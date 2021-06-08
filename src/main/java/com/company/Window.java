package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Window extends JPanel {

    int size = 100;
    Area[][] map = Area.mapGenerator(size, size);
    Virus virus = new Virus(1);
    int numberOfObjects =60;
    GameObjectList gameObjectList = new GameObjectList(numberOfObjects, IHealthyCreator.createHealthy(numberOfObjects), IObstacleCreator.createObstacle(numberOfObjects), IMedicCreator.createMedic(numberOfObjects), ISickCreator.createSick(numberOfObjects, virus), ISickMedicCreator.createSickMedic(numberOfObjects, virus), virus);
    LinkedList<GameObject> movingList = gameObjectList.movingListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList());



    public void paint(Graphics g, Area[][] map, int size) {

        for(int i =0; i<size; i++){
            for (int j=0; j<size;j++){
                if(map[i][j].getField().getGameObjectReference()==null){
                    g.setColor(Color.LIGHT_GRAY);
                } else if(map[i][j].getField().getGameObjectReference() instanceof Healthy){
                    g.setColor(Color.GREEN);
                } else if(map[i][j].getField().getGameObjectReference() instanceof Medic){
                    g.setColor(Color.WHITE);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Sick){
                    g.setColor(Color.RED);
                } else if(map[i][j].getField().getGameObjectReference() instanceof Obstacle){
                    g.setColor(Color.BLACK);
                } else if(map[i][j].getField().getGameObjectReference() instanceof SickMedic){
                    g.setColor(Color.CYAN);
                }
            }
        }
    }


    public static void main(String[] arg){
        Window window = new Window();
    }


    public void paint(Graphics g){


    }


    public Window(){
        JFrame frame = new JFrame("Simulation");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

}
