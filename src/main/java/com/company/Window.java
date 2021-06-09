package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import static com.company.Area.mapGameObjectInitialization;

public class Window extends JPanel implements ActionListener {

//    int map.length = 40;
    Area[][] map;
    Virus virus = new Virus(2);
    int numberOfObjects;
    int numberOfHealthy;
    int numberOfSick;
    int numberOfMedic;
    int numberOfSickMedic;
    int numberOfObstacle;
    GameObjectList gameObjectList;
//    LinkedList<GameObject> movingList = gameObjectList.movingListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList());


    SickHandler sickHandler;                  //TWORZENIE OBIEKTOW HENDLEROW NA KTORYCH RZECZ BEDA WYWOLYWANE FUNKCJE
    HealthyHandler healthyHandler;
    SickMedicHandler sickMedicHandler;
    MedicHandler medicHandler;
    MoveHandler moveHandler ;


//    public static void main(String[] arg){
//        Window window = new Window();
//    }


    public void paint(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif",Font.ITALIC, 20));
        g.drawString("Tura nr: ", map.length*10 + 100, 20);
        g.drawString("Ilosc zdrowych: " + gameObjectList.getHealthyList().size(), map.length*10+100, 50);
        g.drawString("Ilosc chorych: " + gameObjectList.getSickList().size(), map.length*10+100, 80);
        g.drawString("Ilosc medykow: " + gameObjectList.getMedicList().size(), map.length*10+100, 110);
        g.drawString("Ilosc chorych medykow: " + gameObjectList.getSickMedicList().size(), map.length*10+100, 140);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].getField().getGameObjectReference() == null) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fill3DRect(10*j, 10*i, 10, 10, true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Healthy) {
                    g.setColor(Color.YELLOW);
                    g.fill3DRect(10*j, 10*i, 10,10,true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Medic) {
                    g.setColor(Color.CYAN);
                    g.fill3DRect(10*j, 10*i, 10, 10,true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Sick) {
                    g.setColor(Color.RED);
                    g.fill3DRect(10*j, 10*i, 10, 10,true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Obstacle) {
                    g.setColor(Color.BLACK);
                    g.fill3DRect(10*j, 10*i, 10, 10,true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof SickMedic) {
                    g.setColor(Color.BLUE);
                    g.fill3DRect(10*j, 10*i, 10, 10,true);
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            map = moveHandler.twoFieldHealthyMoveHandler(map, map.length);
            map = moveHandler.twoFieldMedicMoveHandler(map, map.length);
            map = moveHandler.oneFieldSickMoveHandler(map, map.length);
            healthyHandler.transformationToSick(map,virus,gameObjectList.getHealthyList(),gameObjectList.getSickList(),gameObjectList);
            medicHandler.transformationToSickMedic(map, virus, gameObjectList.getMedicList(), gameObjectList.getSickMedicList(),gameObjectList);
            sickMedicHandler.checkingNumberOfIteration(map, gameObjectList.getSickMedicList(), gameObjectList.getMedicList(), medicHandler, map.length);
            sickMedicHandler.transformationToMedicOrDying(map,gameObjectList.getMedicList(),gameObjectList.getSickMedicList(),medicHandler,map.length,gameObjectList);
            sickHandler.transformationToHealthy(map, gameObjectList.getHealthyList(), gameObjectList.getSickList(),gameObjectList);
            sickHandler.virusKillSick(map,gameObjectList.getSickList(),gameObjectList);
        }
    }



    public Window(Area[][] map,int numberOfObjects, int numberOfHealthy, int numberOfSick, int numberOfMedic, int numberOfSickMedic,int numberOfObstacle,GameObjectList gameObjectList){
        JFrame frame = new JFrame("Simulation");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sickHandler =new SickHandler(map, null, map.length);
        healthyHandler = new HealthyHandler(map, null, map.length);
        sickMedicHandler = new SickMedicHandler(map, null, map.length);
        medicHandler = new MedicHandler(map, null, map.length);
        moveHandler = new MoveHandler(map, null);


        this.numberOfObjects =numberOfObjects;
        this.numberOfHealthy = numberOfHealthy;
        this.numberOfSick =numberOfSick;
        this.numberOfMedic = numberOfSickMedic;
        this.numberOfMedic = numberOfMedic;
        this.numberOfObstacle = numberOfObstacle;

        this.gameObjectList = gameObjectList;

        this.map =map;

//        mapGameObjectInitialization(map,
//                gameObjectList.getObstacleList(),
//                gameObjectList.getHealthyList(),
//                gameObjectList.getSickList(),
//                gameObjectList.getMedicList(), gameObjectList.getSickMedicList(),
//                map.length);

//        Timer t = new Timer(2000, this);
//        t.restart();
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}