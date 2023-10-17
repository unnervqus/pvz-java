package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.peas.Pea;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

public class TriplePeashooter extends Plant {
    public static final int RELOAD_TIME = 1000;
    public static final int COST = 500;
    public static int RELOAD = RELOAD_TIME;
    public Pea downLinePea;
    public Pea upLinePea;
    public TriplePeashooter(int tileX, int tileY) {
        super(400, tileX, tileY, GameEngine.SeedSlot.TRIPLE_PEASHOOTER, true);
        hitbox = new Rect(column * 150+45, line * 150+125 , 66, 74);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
    }

    @Override
    public void levelUP() {
        super.levelUP();
        hp += 250;
    }
}
