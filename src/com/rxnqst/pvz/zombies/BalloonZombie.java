package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.utils.Rect;

public class BalloonZombie extends Zombie {
    public GameObjectType type = GameObjectType.ZBalloon;
    public BalloonZombie(int posX, int line) {
        super(posX, line, 300, 1, 5);
        hitbox = new Rect(posX, line * 150 + 40, 64, 106);
        head = new Rect(posX, line * 150 + 75, 35, 40);
    }
    public BalloonZombie(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed);
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 64, 106);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 35, 40);
    }
}
