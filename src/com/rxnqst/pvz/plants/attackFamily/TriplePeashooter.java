package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.peas.Pea;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class TriplePeashooter extends Plant {
    public static int RELOAD = pvzContainers.get(GameObjectType.TriplePeashooter).RELOAD_TIME;
    public Pea downLinePea;
    public Pea upLinePea;
    public TriplePeashooter(int tileX, int tileY) {
        super(400, tileX, tileY, true);
        hitbox = new Rect(column * 150+45, line * 150+125 , 66, 74);
        type = GameObjectType.TriplePeashooter;
    }

    @Override
    public void levelUP() {
        super.levelUP();
        hp += 250;
    }
}
