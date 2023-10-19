package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.utils.Rect;

public class ZombieJackbox extends Zombie {
    public GameObjectType type = GameObjectType.ZJackbox;
    public Rect boomArea;
    public ZombieJackbox(int posX, int line) {
        super(posX, line, 400, 0, 3);
        this.hitbox = new Rect(posX, line * 150 + 100, 74, 106);
        this.head = new Rect(posX, line * 150 + 100, 45, 45);
        boomArea =  new Rect(hitbox.x-150, hitbox.y-150, 450, 450);
    }
    public ZombieJackbox(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 74, 106);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 45, 45);
        boomArea = new Rect(hitbox.x-150, hitbox.y-150, 450, 450);
    }
}
