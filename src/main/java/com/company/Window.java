package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import static com.company.Area.mapGameObjectInitialization;

public class Window extends JPanel implements ActionListener {

    int size = 20;
    Area[][] map = Area.mapGenerator(size, size);
    Virus virus = new Virus(2);
    int numberOfObjects =100;
    int numberOfHealthy = 5*5;
    int numberOfSick = 5*5;
    int numberOfMedic = 5*5;
    int numberOfSickMedic =5*5;
    int numberOfObstacle =5*5;
    GameObjectList gameObjectList = new GameObjectList(numberOfObjects, IHealthyCreator.createHealthy(numberOfHealthy), IObstacleCreator.createObstacle(numberOfObstacle), IMedicCreator.createMedic(numberOfMedic), ISickCreator.createSick(numberOfSick, virus), ISickMedicCreator.createSickMedic(numberOfSickMedic, virus), virus);
    LinkedList<GameObject> movingList = gameObjectList.movingListCreator(gameObjectList.getHealthyList(), gameObjectList.getMedicList(), gameObjectList.getSickList());


    SickHandler sickHandler = new SickHandler(map, null, size);                  //TWORZENIE OBIEKTOW HENDLEROW NA KTORYCH RZECZ BEDA WYWOLYWANE FUNKCJE
    HealthyHandler healthyHandler = new HealthyHandler(map, null, size);
    SickMedicHandler sickMedicHandler = new SickMedicHandler(map, null, size);
    MedicHandler medicHandler = new MedicHandler(map, null, size);
    MoveHandler moveHandler = new MoveHandler(map, null);


    public static void main(String[] arg){
        Window window = new Window();
    }


    public void paint(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif",Font.ITALIC, 20));
        g.drawString("Tura nr: ", 400, 20);
        g.drawString("Ilosc zdrowych: " + gameObjectList.getHealthyList().size(), 400, 50);
        g.drawString("Ilosc chorych: " + gameObjectList.getSickList().size(), 400, 80);
        g.drawString("Ilosc medykow: " + gameObjectList.getMedicList().size(), 400, 110);
        g.drawString("Ilosc chorych medykow: " + gameObjectList.getSickMedicList().size(), 400, 140);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j].getField().getGameObjectReference() == null) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fill3DRect(10*i, 10*j, 10, 10, true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Healthy) {
                    g.setColor(Color.YELLOW);
                    g.fill3DRect(10*i, 10*j, 10,10,true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Medic) {
                    g.setColor(Color.CYAN);
                    g.fill3DRect(10*i, 10*j, 10, 10,true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Sick) {
                    g.setColor(Color.RED);
                    g.fill3DRect(10*i, 10*j, 10, 10,true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof Obstacle) {
                    g.setColor(Color.BLACK);
                    g.fill3DRect(10*i, 10*j, 10, 10,true);
                } else if (map[i][j].getField().getGameObjectReference() instanceof SickMedic) {
                    g.setColor(Color.BLUE);
                    g.fill3DRect(10*i, 10*j, 10, 10,true);
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            map = moveHandler.twoFieldHealthyMoveHandler(map, size);
            map = moveHandler.twoFieldMedicMoveHandler(map, size);
            map = moveHandler.oneFieldSickMoveHandler(map, size);
            healthyHandler.transformationToSick(map,virus,gameObjectList.getHealthyList(),gameObjectList.getSickList(),gameObjectList);
            medicHandler.transformationToSickMedic(map, virus, gameObjectList.getMedicList(), gameObjectList.getSickMedicList(),gameObjectList);
            sickMedicHandler.checkingNumberOfIteration(map, gameObjectList.getSickMedicList(), gameObjectList.getMedicList(), medicHandler, size);
            sickMedicHandler.transformationToMedicOrDying(map,gameObjectList.getMedicList(),gameObjectList.getSickMedicList(),medicHandler,size,gameObjectList);
            sickHandler.transformationToHealthy(map, gameObjectList.getHealthyList(), gameObjectList.getSickList(),gameObjectList);
            sickHandler.virusKillSick(map,gameObjectList.getSickList(),gameObjectList);
        }
    }



    public Window(){
        JFrame frame = new JFrame("Simulation");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mapGameObjectInitialization(map,
                gameObjectList.getObstacleList(),
                gameObjectList.getHealthyList(),
                gameObjectList.getSickList(),
                gameObjectList.getMedicList(), gameObjectList.getSickMedicList(),
                size);



        Timer t = new Timer(16, this);
        t.restart();
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}