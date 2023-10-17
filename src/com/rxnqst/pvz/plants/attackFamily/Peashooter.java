package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

public class Peashooter extends Plant {
    public static final int RELOAD_TIME = 200;
    public static final int COST = 100;
    public static int RELOAD = RELOAD_TIME;
    public Peashooter(int posX, int posY) {
        super(120, posX, posY, GameEngine.SeedSlot.PEASHOOTER, true);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount-=COST;
        hitbox = new Rect(column * 150+45, line * 150+135 , 52, 58);
    }
}
