package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.utils.Rect;

public class ZombieConehead extends Zombie {
    public ZombieConehead(int posX, int line){
        super(posX, line, 500, 1, 1);
        this.hitbox = new Rect(posX, line * 150 + 100, 54, 110);
        this.head = new Rect(posX, line * 150 + 100, 45, 60);
        type = GameObjectType.ZConehead;
    }
    public ZombieConehead(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 54, 110);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 45, 60);
        type = GameObjectType.ZConehead;
    }
}
