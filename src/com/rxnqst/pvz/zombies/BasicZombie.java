package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.utils.Rect;

public class BasicZombie extends Zombie {
    public GameObjectType type = GameObjectType.ZBasic;
    public BasicZombie(int posX, int line) {
        super(posX, line, 250, 3, 1);
        this.hitbox = new Rect(posX, line * 150 + 100, 54, 88);
        this.head = new Rect(posX, line*150+100, 45, 40);
    }
    public BasicZombie(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 54, 88);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 45, 40);
    }
}
