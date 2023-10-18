package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

public class BalloonZombie extends Zombie {
    public static int COST = 300;
    public BalloonZombie(int posX, int line) {
        super(posX, line, 300, 1, 5, ImageManager.ImgName.ZOMBIE_BALLOON, null, null);
        hitbox = new Rect(posX, line * 150 + 40, 64, 106);
        head = new Rect(posX, line * 150 + 75, 35, 40);
    }
}
