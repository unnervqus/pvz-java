package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.utils.Rect;

public class ZombieImp extends Zombie {
    public GameObjectType type = GameObjectType.ZImp;
    public ZombieImp(int posX, int line) {
        super(posX, line, 50, 2, 3);
        this.hitbox = new Rect(posX, line * 150 + 100, 61, 81);
        this.head = new Rect(posX, line * 150 + 100, 45, 40);
    }
    public ZombieImp(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 61, 81);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 45, 40);
    }
}
