package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

public class Yeti extends Zombie {
    public static int COST = 2500;
    public Yeti(int posX, int line) {
        super(posX, line, 5000, 5, 1, ImageManager.ImgName.YETI);
        this.hitbox = new Rect(posX, line * 150 + 100, 86, 128);
        this.head = new Rect(posX, line * 150 + 100, 50, 50);
    }
}
