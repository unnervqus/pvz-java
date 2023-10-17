package com.rxnqst.pvz.plants.defenceFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

public class Wallnut extends Plant {
    public static final int RELOAD_TIME = 1000;
    public static final int COST = 50;
    public static int RELOAD = RELOAD_TIME;
    public Wallnut(int posX, int posY) {
        super(4000, posX, posY, GameEngine.SeedSlot.WALLNUT, false);
        hitbox = new Rect(column * 150+75, line * 150+125 , 52, 60);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
    }

    @Override
    public void levelUP() {
        super.levelUP();
        hp += 2500;
    }
}
