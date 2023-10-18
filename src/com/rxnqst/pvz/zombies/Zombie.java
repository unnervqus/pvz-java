package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.GameObject;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Zombie extends GameObject implements Serializable {
    public Rect head;
    public int dmg;
    public int speed;
    public ImageManager.ImgName type;
    public int freezeDelay = 0;
    public int COST;
    public BufferedImage walkAtlas;
    public BufferedImage eatAtlas;
    public int frameIndex = 0;
    public boolean isEating = false;
    public Zombie(int posX, int line, int hp, int dmg, int speed, ImageManager.ImgName textureName, BufferedImage walkAtlas, BufferedImage eatAtlas) {
        //TODO: make better abstraction, not this sh!t
        super(line, posX/150, 0, 0, 0);
        this.line = line;
        this.hp = hp;
        this.dmg = dmg;
        this.speed = speed;
        this.type = textureName;
        this.eatAtlas = eatAtlas;
        this.walkAtlas = walkAtlas;
    }
}
