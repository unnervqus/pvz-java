package com.rxnqst.pvz.plants.helpFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;
import static com.rxnqst.pvz.GameSettings.SUNFLOWER_PROD_TIME;

public class Sunflower extends Plant {
    public static final int RELOAD_TIME = 125 * SEED_RELOAD_MODIFIER;
    public static final int COST = 50;
    public static int RELOAD = 0;
    public int sunReload = SUNFLOWER_PROD_TIME;
    public Sunflower(Integer posX, Integer posY) {
        super(50, posX, posY, GameEngine.SeedSlot.SUNFLOWER, false);
        hitbox = new Rect(column * 150+45, line * 150+135 , 56, 61);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 25;
    }
}
