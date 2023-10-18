package com.rxnqst.pvz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import static com.rxnqst.pvz.ImageManager.ImgName.*;

public class ImageManager implements Serializable {
    private static final HashMap<ImgName, BufferedImage> textures = new HashMap<>();
    public enum ImgName {
        // * GUI, ENVIRONMENT
        BUSH_1,
        BUSH_R,
        FIELD,
        BG,
        FOG,
        SEED_INTERFACE,
        ZOMBIE_INTERFACE,
        SHOVEL,
        SHOVEL_ICON,
        SUN,
        // * PROJECTILES
        PEA,
        SNOW_PEA,
        NEEDLE,
        FIRE_PEA,
        FIRE_NEEDLE,
        WATERMELON,
        CABBAGE,
        PUFF,
        // * PLANTS
        CABBAGE_PULT,
        CHERRY_BOMB,
        PEASHOOTER,
        POTATO_MINE_UNREADY,
        POTATO_MINE,
        PUFFSHROOM,
        SNOW_PEASHOOTER,
        SUNFLOWER,
        WALLNUT,
        WATERMELON_PULT,
        TRIPLE_PEASHOOTER,
        CACTUS,
        ICE_MUSHROOM,
        JALAPENO,
        PUMPKIN,
        SPIKE_ROCK,
        SPIKE_WEED,
        TALL_WALLNUT,
        TORCH_WOOD,
        // * SEEDS
        CABBAGE_PULT_SEEDS,
        CHERRY_BOMB_SEEDS,
        PEASHOOTER_SEEDS,
        POTATO_MINE_SEEDS,
        PUFFSHROOM_SEEDS,
        SNOW_PEASHOOTER_SEEDS,
        SUNFLOWER_SEEDS,
        WALLNUT_SEEDS,
        WATERMELON_PULT_SEEDS,
        CACTUS_SEEDS,
        ICE_MUSHROOM_SEEDS,
        JALAPENO_SEEDS,
        PUMPKIN_SEEDS,
        SPIKE_ROCK_SEEDS,
        SPIKE_WEED_SEEDS,
        TALL_WALLNUT_SEEDS,
        TORCH_WOOD_SEEDS,
        TRIPLE_PEASHOOTER_SEEDS,
        // * ZOMBIES
        IMP,
        ZOMBIE_BASIC,
        ZOMBIE_BUCKETHEAD,
        ZOMBIE_CONEHEAD,
        ZOMBIE_DOOR,
        ZOMBIE_JACKBOX,
        ZOMBONI,
        ZOMBIE_BALLOON,
        YETI,
        GRAVE_1,
        GRAVE_2,
        FLAG,
        //GRAVE_3,
        //GRAVE_4,
        // * ZOMBIE SEEDS
        IMP_SEEDS,
        ZOMBIE_BASIC_SEEDS,
        ZOMBIE_BUCKETHEAD_SEEDS,
        ZOMBIE_CONEHEAD_SEEDS,
        ZOMBIE_DOOR_SEEDS,
        ZOMBIE_JACKBOX_SEEDS,
        ZOMBONI_SEEDS,
        ZOMBIE_BALLOON_SEEDS,
        YETI_SEEDS,
        GRAVE_SEEDS,
        FLAG_SEEDS
    }
    public static void loadTextures() {
        try {
            textures.put(BUSH_1, ImageIO.read(new File("assets/gui/bush_1.png")));
            textures.put(BUSH_R, ImageIO.read(new File("assets/gui/bush_right.png")));
            textures.put(FIELD, ImageIO.read(new File("assets/gui/field.png")));
            textures.put(BG, ImageIO.read(new File("assets/gui/background.png")));
            textures.put(FOG, ImageIO.read(new File("assets/gui/fog.png")));
            textures.put(SEED_INTERFACE, ImageIO.read(new File("assets/gui/seeds interface.png")));
            textures.put(ZOMBIE_INTERFACE, ImageIO.read(new File("assets/gui/zombie interface.png")));
            textures.put(SHOVEL, ImageIO.read(new File("assets/gui/shovel selected.png")));
            textures.put(SHOVEL_ICON, ImageIO.read(new File("assets/gui/shovel_icon.png")));
            textures.put(SUN, ImageIO.read(new File("assets/gui/sun.png")));

            textures.put(PEA, ImageIO.read(new File("assets/projectiles/pea.png")));
            textures.put(SNOW_PEA, ImageIO.read(new File("assets/projectiles/snow_pea.png")));
            textures.put(NEEDLE, ImageIO.read(new File("assets/projectiles/needle.png")));
            textures.put(FIRE_PEA, ImageIO.read(new File("assets/projectiles/fire_pea.png")));
            textures.put(FIRE_NEEDLE, ImageIO.read(new File("assets/projectiles/fire_needle.png")));
            textures.put(WATERMELON, ImageIO.read(new File("assets/projectiles/watermelon.png")));
            textures.put(CABBAGE, ImageIO.read(new File("assets/projectiles/cabbage.png")));
            textures.put(PUFF, ImageIO.read(new File("assets/projectiles/puff.png")));

            textures.put(CABBAGE_PULT, ImageIO.read(new File("assets/plants/cabbage_pult.png")));
            textures.put(CHERRY_BOMB, ImageIO.read(new File("assets/plants/cherry_bomb.png")));
            textures.put(PEASHOOTER, ImageIO.read(new File("assets/plants/peashooter.png")));
            textures.put(POTATO_MINE_UNREADY, ImageIO.read(new File("assets/plants/potato_mine_not_ready.png")));
            textures.put(POTATO_MINE, ImageIO.read(new File("assets/plants/potato_mine_ready.png")));
            textures.put(PUFFSHROOM, ImageIO.read(new File("assets/plants/puffshroom.png")));
            textures.put(SNOW_PEASHOOTER, ImageIO.read(new File("assets/plants/snow_pea.png")));
            textures.put(SUNFLOWER, ImageIO.read(new File("assets/plants/sunflower.png")));
            textures.put(WALLNUT, ImageIO.read(new File("assets/plants/wallnut.png")));
            textures.put(WATERMELON_PULT, ImageIO.read(new File("assets/plants/watermelon_pult.png")));
            textures.put(TRIPLE_PEASHOOTER, ImageIO.read(new File("assets/plants/triple_peashooter.png")));
            textures.put(CACTUS, ImageIO.read(new File("assets/plants/cactus.png")));
            textures.put(TORCH_WOOD, ImageIO.read(new File("assets/plants/torch_wood.png")));
            textures.put(ICE_MUSHROOM, ImageIO.read(new File("assets/plants/ice_mushroom.png")));
            textures.put(JALAPENO, ImageIO.read(new File("assets/plants/jalapeno.png")));
            textures.put(PUMPKIN, ImageIO.read(new File("assets/plants/pumpkin.png")));
            textures.put(SPIKE_ROCK, ImageIO.read(new File("assets/plants/spike_rock.png")));
            textures.put(SPIKE_WEED, ImageIO.read(new File("assets/plants/spike_weed.png")));
            textures.put(TALL_WALLNUT, ImageIO.read(new File("assets/plants/tall_wallnut.png")));

            textures.put(CABBAGE_PULT_SEEDS, ImageIO.read(new File("assets/seeds/cabbage_pult seeds.png")));
            textures.put(CHERRY_BOMB_SEEDS, ImageIO.read(new File("assets/seeds/cherry_bomb seeds.png")));
            textures.put(PEASHOOTER_SEEDS, ImageIO.read(new File("assets/seeds/peashooter seeds.png")));
            textures.put(POTATO_MINE_SEEDS, ImageIO.read(new File("assets/seeds/potato_mine seeds.png")));
            textures.put(PUFFSHROOM_SEEDS, ImageIO.read(new File("assets/seeds/puffshroom seeds.png")));
            textures.put(SNOW_PEASHOOTER_SEEDS, ImageIO.read(new File("assets/seeds/snow_pea seeds.png")));
            textures.put(SUNFLOWER_SEEDS, ImageIO.read(new File("assets/seeds/sunflower seeds.png")));
            textures.put(WALLNUT_SEEDS, ImageIO.read(new File("assets/seeds/wallnut seeds.png")));
            textures.put(WATERMELON_PULT_SEEDS, ImageIO.read(new File("assets/seeds/watermelon_pult seeds.png")));
            textures.put(TRIPLE_PEASHOOTER_SEEDS, ImageIO.read(new File("assets/seeds/triple_peashooter seeds.png")));
            textures.put(CACTUS_SEEDS, ImageIO.read(new File("assets/seeds/cactus seeds.png")));
            textures.put(TORCH_WOOD_SEEDS, ImageIO.read(new File("assets/seeds/torch_wood seeds.png")));
            textures.put(ICE_MUSHROOM_SEEDS, ImageIO.read(new File("assets/seeds/ice_mushroom seeds.png")));
            textures.put(JALAPENO_SEEDS, ImageIO.read(new File("assets/seeds/jalapeno seeds.png")));
            textures.put(PUMPKIN_SEEDS, ImageIO.read(new File("assets/seeds/pumpkin seeds.png")));
            textures.put(SPIKE_ROCK_SEEDS, ImageIO.read(new File("assets/seeds/spike_rock seeds.png")));
            textures.put(SPIKE_WEED_SEEDS, ImageIO.read(new File("assets/seeds/spike_weed seeds.png")));
            textures.put(TALL_WALLNUT_SEEDS, ImageIO.read(new File("assets/seeds/tall_wallnut seeds.png")));

            textures.put(IMP, ImageIO.read(new File("assets/zombie/imp.png")));
            textures.put(ZOMBIE_BASIC, ImageIO.read(new File("assets/zombie/zombie basic.png")));
            textures.put(ZOMBIE_CONEHEAD, ImageIO.read(new File("assets/zombie/zombie cone.png")));
            textures.put(ZOMBIE_BUCKETHEAD, ImageIO.read(new File("assets/zombie/zombie bucket.png")));
            textures.put(ZOMBIE_DOOR, ImageIO.read(new File("assets/zombie/zombie door.png")));
            textures.put(ZOMBIE_JACKBOX, ImageIO.read(new File("assets/zombie/zombie jackbox.png")));
            textures.put(ZOMBONI, ImageIO.read(new File("assets/zombie/zomboni.png")));
            textures.put(ZOMBIE_BALLOON, ImageIO.read(new File("assets/zombie/zombie balloon.png")));
            textures.put(YETI, ImageIO.read(new File("assets/zombie/yeti.png")));
            textures.put(GRAVE_1, ImageIO.read(new File("assets/zombie/grave_1.png")));
            textures.put(GRAVE_2, ImageIO.read(new File("assets/zombie/grave_2.png")));
            textures.put(FLAG, ImageIO.read(new File("assets/zombie/flag.png")));
            //textures.put(GRAVE_3, ImageIO.read(new File("assets/zombie/grave3.png")));
            //textures.put(GRAVE_4, ImageIO.read(new File("assets/zombie/grave4.png")));

            textures.put(IMP_SEEDS, ImageIO.read(new File("assets/zombie_seeds/imp seeds.png")));
            textures.put(ZOMBIE_BASIC_SEEDS, ImageIO.read(new File("assets/zombie_seeds/zombie basic seeds.png")));
            textures.put(ZOMBIE_CONEHEAD_SEEDS, ImageIO.read(new File("assets/zombie_seeds/zombie cone seeds.png")));
            textures.put(ZOMBIE_BUCKETHEAD_SEEDS, ImageIO.read(new File("assets/zombie_seeds/zombie bucket seeds.png")));
            textures.put(ZOMBIE_DOOR_SEEDS, ImageIO.read(new File("assets/zombie_seeds/zombie door seeds.png")));
            textures.put(ZOMBIE_JACKBOX_SEEDS, ImageIO.read(new File("assets/zombie_seeds/zombie jackbox seeds.png")));
            textures.put(ZOMBONI_SEEDS, ImageIO.read(new File("assets/zombie_seeds/zomboni seeds.png")));
            textures.put(ZOMBIE_BALLOON_SEEDS, ImageIO.read(new File("assets/zombie_seeds/zombie balloon seeds.png")));
            textures.put(YETI_SEEDS, ImageIO.read(new File("assets/zombie_seeds/yeti seeds.png")));
            textures.put(GRAVE_SEEDS, ImageIO.read(new File("assets/zombie_seeds/grave seeds.png")));
            textures.put(FLAG_SEEDS, ImageIO.read(new File("assets/zombie_seeds/flag seeds.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage getTexture(ImgName texture_name) {
        return textures.get(texture_name);
    }
}
