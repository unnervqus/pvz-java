package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

public class Puffshroom extends Plant {
    public static final int RELOAD_TIME = 400;
    public static final int COST = 0;
    public static int RELOAD = RELOAD_TIME;
    public Puffshroom(int posX, int posY) {
        super(10, posX, posY, GameEngine.SeedSlot.PUFFSHROOM, true);
        hitbox = new Rect(column * 150+55, line * 150+145 , 35, 34);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 25;
    }
}
