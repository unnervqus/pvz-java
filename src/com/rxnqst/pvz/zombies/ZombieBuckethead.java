package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.utils.Rect;

public class ZombieBuckethead extends Zombie {
    public ZombieBuckethead(int posX, int line) {
        super(posX, line, 1000, 1, 1);
        this.hitbox = new Rect(posX, line * 150 + 100, 58, 100);
        this.head = new Rect(posX, line * 150 + 100, 50, 50);
        type = GameObjectType.ZBuckethead;
    }
    public ZombieBuckethead(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 58, 100);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 50, 50);
        type = GameObjectType.ZBuckethead;
    }
}
