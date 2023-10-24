package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.GameObject;
import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.image.BufferedImage;

public class Grave extends GameObject {
    public transient BufferedImage image;
    public Grave(int column, int line) {
        super(line, column,  0,0, 10000 );
        this.line = line;
        this.column = column;
        type = GameObjectType.ZGrave;
        hp = 10000;
        hitbox = new Rect(column * 150 + 20, line * 150 + 90, 0, 0);
        int r = GameEngine.randomizer.nextInt(0,2);
        switch (r) {
            case 0 -> {
                image = ImageManager.getTexture(GameObjectType.ZGrave1);
                hitbox.width = 106;
                hitbox.height = 122;
            }
            case 1 -> {
                image = ImageManager.getTexture(GameObjectType.ZGrave2);
                hitbox.width = 92;
                hitbox.height = 106;
                hitbox.x += 10;
            }
        }
    }
}
