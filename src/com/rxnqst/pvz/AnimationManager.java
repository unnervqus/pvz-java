package com.rxnqst.pvz;

import com.rxnqst.pvz.zombies.Zombie;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.rxnqst.pvz.GameEngine.*;

public class AnimationManager {
    public static HashMap<AtlasName, BufferedImage> animationAtlases = new HashMap<>();

    public enum AtlasName {
        ZOMBIE_BASIC_WALK, ZOMBIE_BASIC_EAT,
        ZOMBIE_CONE_WALK, ZOMBIE_CONE_EAT,
        ZOMBIE_BUCKET_WALK, ZOMBIE_BUCKET_EAT,
        ZOMBIE_DOOR_WALK, ZOMBIE_DOOR_EAT,
        ZOMBIE_JACKBOX_WALK,
        ZOMBIE_DEAD_WALK,

        ZOMBONI_RIDE,
        ZOMBIE_BALLOON_FLY,

        ZOMBONI_BOOM_EFFECT,

        POTATO_BOOM_EFFECT,
        CHERRY_ACTIVATION_EFFECT,
        CHERRY_BOOM_EFFECT
    }
    public static void loadAnimationAtlases() throws IOException {
        String aFolder = "assets/animations/";
        animationAtlases.put(AtlasName.ZOMBIE_BASIC_WALK, ImageIO.read(new File(aFolder +"zombie/zombie walk.png")));
        animationAtlases.put(AtlasName.ZOMBIE_BASIC_EAT, ImageIO.read(new File(aFolder +"zombie/zombie eat.png")));
        animationAtlases.put(AtlasName.ZOMBIE_CONE_WALK, ImageIO.read(new File(aFolder +"zombie/zombie cone walk.png")));
        animationAtlases.put(AtlasName.ZOMBIE_CONE_EAT, ImageIO.read(new File(aFolder +"zombie/zombie cone eat.png")));
        animationAtlases.put(AtlasName.ZOMBIE_BUCKET_WALK, ImageIO.read(new File(aFolder +"zombie/zombie bucket walk.png")));
        animationAtlases.put(AtlasName.ZOMBIE_BUCKET_EAT, ImageIO.read(new File(aFolder +"zombie/zombie bucket eat.png")));
        animationAtlases.put(AtlasName.ZOMBIE_DOOR_EAT, ImageIO.read(new File(aFolder +"zombie/zombie door eat.png")));
        animationAtlases.put(AtlasName.ZOMBIE_DOOR_WALK, ImageIO.read(new File(aFolder +"zombie/zombie door walk.png")));
        animationAtlases.put(AtlasName.ZOMBIE_JACKBOX_WALK, ImageIO.read(new File(aFolder +"zombie/zombie jackbox walk.png")));
        animationAtlases.put(AtlasName.ZOMBIE_BALLOON_FLY, ImageIO.read(new File(aFolder +"zombie/zombie balloon fly.png")));
        animationAtlases.put(AtlasName.ZOMBONI_RIDE, ImageIO.read(new File(aFolder +"zombie/zomboni ride.png")));

        animationAtlases.put(AtlasName.ZOMBIE_DEAD_WALK, ImageIO.read(new File(aFolder +"zombie/zombie dead.png")));

        animationAtlases.put(AtlasName.ZOMBONI_BOOM_EFFECT, ImageIO.read(new File(aFolder +"effects/zomboniBoom.png")));
        animationAtlases.put(AtlasName.POTATO_BOOM_EFFECT, ImageIO.read(new File(aFolder +"effects/potatoBoom.png")));
        animationAtlases.put(AtlasName.CHERRY_ACTIVATION_EFFECT, ImageIO.read(new File(aFolder +"effects/cherryActivation.png")));
        animationAtlases.put(AtlasName.CHERRY_BOOM_EFFECT, ImageIO.read(new File(aFolder +"effects/cherryBoom.png")));
    }
    public static BufferedImage getTexture(AtlasName atlasName) {
        return animationAtlases.get(atlasName);
    }
}
