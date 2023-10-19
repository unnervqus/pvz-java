package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.image.BufferedImage;
import java.io.Serial;
import java.io.Serializable;

public class Pea implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685412217757690L;
    public int dmg;
    public boolean isFired = false;
    public Rect hitbox;
    transient public BufferedImage image;
    public Pea(int posX, int posY, int level, BufferedImage image) {
        this.dmg = 25 + (level-1) * 15;
        hitbox = new Rect(posX, posY, 20, 20);
        if(image != null) this.image = image;
        else this.image = ImageManager.getTexture(GameObjectType.Pea);
    }
}
