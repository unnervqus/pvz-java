package com.rxnqst.pvz;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PvZContainer {
    public final BufferedImage image;
    public final BufferedImage eatAtlas;
    public final BufferedImage walkAtlas;
    public final BufferedImage plantAnimation;
    public final Point frameSizeWalk;
    public final Point frameSizeEat;
    public final int COST;
    public final int RELOAD_TIME;
    public int reloading;
    // Zombie configuration
    public PvZContainer(BufferedImage image, BufferedImage eatAtlas, BufferedImage walkAtlas,
                        Point frameSizeEat, Point frameSizeWalk, int COST) {
        this.image = null;
        this.plantAnimation = null;
        this.RELOAD_TIME = 0;
        assert eatAtlas != null;
        assert walkAtlas != null;
        this.eatAtlas = eatAtlas;
        this.walkAtlas = walkAtlas;
        this.frameSizeEat = frameSizeEat;
        this.frameSizeWalk = frameSizeWalk;
        this.COST = COST;
    }
    // Plant configuration
    public PvZContainer(BufferedImage image, BufferedImage plantAnimation, int COST, int RELOAD_TIME) {
        this.image = image;
        this.plantAnimation = plantAnimation;
        this.RELOAD_TIME = RELOAD_TIME;
        this.eatAtlas = null;
        this.walkAtlas = null;
        this.frameSizeEat = null;
        this.frameSizeWalk = null;
        this.COST = COST;
    }
    // Just Image configuration
    public PvZContainer(BufferedImage image, int COST) {
        this.image = image;
        this.plantAnimation = null;
        this.RELOAD_TIME = 0;
        this.eatAtlas = null;
        this.walkAtlas = null;
        this.frameSizeEat = null;
        this.frameSizeWalk = null;
        this.COST = COST;
    }
}
