package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

public class Yeti extends Zombie {
    public static int COST = 2500;
    public Yeti(int posX, int line) {
        super(posX, line, 5000, 5, 1, ImageManager.ImgName.YETI, null, null);
        this.hitbox = new Rect(posX, line * 150 + 100, 86, 128);
        this.head = new Rect(posX, line * 150 + 100, 50, 50);
    }
    public Yeti(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.YETI,null, null);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 86, 128);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 50, 50);
    }
}
