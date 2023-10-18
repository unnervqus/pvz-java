package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Pea implements Serializable {
    public int dmg;
    public boolean isFired = false;
    public Rect hitbox;
    public BufferedImage image;
    public Pea(int posX, int posY, int level, BufferedImage image) {
        this.dmg = 25 + (level-1) * 15;
        hitbox = new Rect(posX, posY, 20, 20);
        if(image != null) this.image = image;
        else this.image = ImageManager.getTexture(ImageManager.ImgName.PEA);
    }
}
