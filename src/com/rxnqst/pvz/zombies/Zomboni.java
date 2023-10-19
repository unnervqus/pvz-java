package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.utils.Rect;

public class Zomboni extends Zombie {
    public GameObjectType type = GameObjectType.ZZomboni;
    public Zomboni(int posX, int line) {
        super(posX, line, 2000, 1000, 1);
        hitbox = new Rect(posX, line * 150 + 100, 118, 118);
        head = new Rect(posX, line * 150 + 100, 118, 118);
    }
    public Zomboni(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 118, 118);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 118, 118);
    }
}
