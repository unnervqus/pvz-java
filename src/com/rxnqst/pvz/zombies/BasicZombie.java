package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

public class BasicZombie extends Zombie {
    public static final int COST = 100;
    public BasicZombie(int posX, int line) {
        super(posX, line, 250, 3, 1, ImageManager.ImgName.ZOMBIE_BASIC);
        this.hitbox = new Rect(posX, line * 150 + 100, 54, 88);
        this.head = new Rect(posX, line*150+100, 45, 40);
    }
}
