package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

public class WatermelonPult extends Plant {
    public static final int RELOAD_TIME = 1000;
    public static final int COST = 700;
    public static int RELOAD = RELOAD_TIME;
    public WatermelonPult(int tileX, int tileY) {
        super(500, tileX, tileY, GameEngine.SeedSlot.WATERMELON_PULT, true);
        hitbox = new Rect(column * 150+5, line * 150+125 , 96, 84);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
    }
}
