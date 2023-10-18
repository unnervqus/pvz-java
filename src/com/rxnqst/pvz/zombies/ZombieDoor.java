package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

public class ZombieDoor extends Zombie {
    public static int COST = 550;
    public ZombieDoor(int posX, int line) {
        super(posX, line, 1200, 3, 1, ImageManager.ImgName.ZOMBIE_DOOR, null, null);
        this.hitbox = new Rect(posX, line * 150 + 100, 62, 92);
        this.head = new Rect(posX+10, line * 150 + 100, 35, 40);
    }
    public ZombieDoor(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.ZOMBIE_DOOR,null, null);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 62, 92);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 35, 40);
    }
}
