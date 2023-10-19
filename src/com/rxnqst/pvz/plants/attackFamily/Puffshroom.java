package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class Puffshroom extends Plant {
    public GameObjectType type = GameObjectType.Puffshroom;
    public static int RELOAD = pvzContainers.get(GameObjectType.Puffshroom).RELOAD_TIME;
    public Puffshroom(int posX, int posY) {
        super(10, posX, posY, true);
        hitbox = new Rect(column * 150+55, line * 150+145 , 35, 34);
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 25;
    }
}
