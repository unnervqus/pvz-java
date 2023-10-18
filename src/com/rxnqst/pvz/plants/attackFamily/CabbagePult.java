package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class CabbagePult extends Plant {
    public static final int RELOAD_TIME = 500 * SEED_RELOAD_MODIFIER;
    public static final int COST = 150;
    public static int RELOAD = RELOAD_TIME;

    public CabbagePult(int column, int line) {
        super(150, column, line, GameEngine.SeedSlot.CABBAGE_PULT, true);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        hitbox = new Rect(column * 150+29, line * 150+125 , 76, 66);
    }

    @Override
    public void levelUP() {
        super.levelUP();
        hp += 100;
    }
}
