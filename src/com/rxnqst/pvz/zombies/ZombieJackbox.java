package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

public class ZombieJackbox extends Zombie {
    public static final int COST = 150;
    public ZombieJackbox(int posX, int line) {
        super(posX, line, 400, 0, 3, ImageManager.ImgName.ZOMBIE_JACKBOX, null, null);
        this.hitbox = new Rect(posX, line * 150 + 100, 74, 106);
        this.head = new Rect(posX, line * 150 + 100, 45, 45);
    }
    public ZombieJackbox(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.ZOMBIE_JACKBOX,null, null);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 74, 106);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 45, 45);
    }
}
