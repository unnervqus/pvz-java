package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class CabbagePult extends Plant {
    public GameObjectType type = GameObjectType.CabbagePult;
    public static int RELOAD = pvzContainers.get(GameObjectType.CabbagePult).RELOAD_TIME;
    public CabbagePult(int column, int line) {
        super(150, column, line, true);
        hitbox = new Rect(column * 150+29, line * 150+125 , 76, 66);
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 100;
    }
}
