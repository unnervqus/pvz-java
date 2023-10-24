package com.rxnqst.pvz.plants.defenceFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class Wallnut extends Plant {
    public static int RELOAD = pvzContainers.get(GameObjectType.Wallnut).RELOAD_TIME;
    public Wallnut(int posX, int posY) {
        super(4000, posX, posY, false);
        hitbox = new Rect(column * 150+75, line * 150+125 , 52, 60);
        type = GameObjectType.Wallnut;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 2500;
    }
}
