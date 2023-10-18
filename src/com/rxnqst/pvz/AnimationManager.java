package com.rxnqst.pvz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class AnimationManager {
    public static HashMap<AtlasName, BufferedImage> animationAtlases = new HashMap<>();
    public enum AtlasName {
        ZOMBIE_BASIC_WALK
    }
    public static void loadAnimationAtlases() throws IOException {
        String aFolder = "assets/animations/";
        animationAtlases.put(AtlasName.ZOMBIE_BASIC_WALK, ImageIO.read(new File(aFolder +"zombie/zombie walk.png")));
    }
}
