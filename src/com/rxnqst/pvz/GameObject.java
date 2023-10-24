package com.rxnqst.pvz;

import com.rxnqst.pvz.utils.Rect;

import java.io.Serial;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    @Serial
    private static final long serialVersionUID = 6329685023217757690L;
    public Rect hitbox;
    public int hp;
    public int line;
    public int column;
    public GameObjectType type;
    public GameObject(int line, int column, int width, int height, int hp) {
        hitbox = new Rect(column * 150, line * 150 + 90, width, height);
        this.hp = hp;

    }
}
