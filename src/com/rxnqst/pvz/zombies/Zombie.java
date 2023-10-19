package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameObject;
import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.utils.Rect;

import java.io.Serial;
import java.io.Serializable;
public abstract class Zombie extends GameObject implements Serializable {
    public GameObjectType type;
    @Serial
    private static final long serialVersionUID = 6529685023217757690L;
    public Rect head;
    public int dmg;
    public int speed;
    public int freezeDelay = 0;
    public int frameIndex = 0;
    public boolean isEating = false;
    public Zombie(int posX, int line, int hp, int dmg, int speed) {
        super(line, posX/150, 0, 0, 0);
        this.line = line;
        this.hp = hp;
        this.dmg = dmg;
        this.speed = speed;
    }
}
