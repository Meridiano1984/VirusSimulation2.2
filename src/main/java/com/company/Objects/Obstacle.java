package com.company.Objects;

import com.company.Area;
import com.company.Objects.GameObject;

public class Obstacle implements GameObject {

    public static int numberOfObstacles = 0;

    public Obstacle() {
        numberOfObstacles++;
    }

    public void areaRandomization (Area[][] map){}
}
