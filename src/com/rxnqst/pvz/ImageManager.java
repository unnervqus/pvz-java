package com.rxnqst.pvz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import static com.rxnqst.pvz.GameObjectType.*;

public class ImageManager implements Serializable {
    private static final HashMap<GameObjectType, BufferedImage> textures = new HashMap<>();
    public static void loadTextures() {
        try {
            textures.put(Bush_1, ImageIO.read(new File("assets/gui/bush_1.png")));
            textures.put(Bush_R, ImageIO.read(new File("assets/gui/bush_right.png")));
            textures.put(Field, ImageIO.read(new File("assets/gui/field.png")));
            textures.put(BG, ImageIO.read(new File("assets/gui/background.png")));
            textures.put(Fog, ImageIO.read(new File("assets/gui/fog.png")));
            textures.put(SeedInterface, ImageIO.read(new File("assets/gui/seeds interface.png")));
            textures.put(ZombieInterface, ImageIO.read(new File("assets/gui/zombie interface.png")));
            textures.put(Shovel, ImageIO.read(new File("assets/gui/shovel selected.png")));
            textures.put(ShovelIcon, ImageIO.read(new File("assets/gui/shovel_icon.png")));
            textures.put(Sun, ImageIO.read(new File("assets/gui/sun.png")));

            textures.put(Pea, ImageIO.read(new File("assets/projectiles/pea.png")));
            textures.put(SnowPea, ImageIO.read(new File("assets/projectiles/snow_pea.png")));
            textures.put(Needle, ImageIO.read(new File("assets/projectiles/needle.png")));
            textures.put(FirePea, ImageIO.read(new File("assets/projectiles/fire_pea.png")));
            textures.put(FireNeedle, ImageIO.read(new File("assets/projectiles/fire_needle.png")));
            textures.put(Watermelon, ImageIO.read(new File("assets/projectiles/watermelon.png")));
            textures.put(Cabbage, ImageIO.read(new File("assets/projectiles/cabbage.png")));
            textures.put(Puff, ImageIO.read(new File("assets/projectiles/puff.png")));

            textures.put(CabbagePult, ImageIO.read(new File("assets/plants/cabbage_pult.png")));
            textures.put(CherryBomb, ImageIO.read(new File("assets/plants/cherry_bomb.png")));
            textures.put(Peashooter, ImageIO.read(new File("assets/plants/peashooter.png")));
            textures.put(_PotatoMine_unready, ImageIO.read(new File("assets/plants/potato_mine_not_ready.png")));
            textures.put(PotatoMine, ImageIO.read(new File("assets/plants/potato_mine_ready.png")));
            textures.put(Puffshroom, ImageIO.read(new File("assets/plants/puffshroom.png")));
            textures.put(SnowPeashooter, ImageIO.read(new File("assets/plants/snow_pea.png")));
            textures.put(Sunflower, ImageIO.read(new File("assets/plants/sunflower.png")));
            textures.put(Wallnut, ImageIO.read(new File("assets/plants/wallnut.png")));
            textures.put(WatermelonPult, ImageIO.read(new File("assets/plants/watermelon_pult.png")));
            textures.put(TriplePeashooter, ImageIO.read(new File("assets/plants/triple_peashooter.png")));
            textures.put(Cactus, ImageIO.read(new File("assets/plants/cactus.png")));
            textures.put(TorchWood, ImageIO.read(new File("assets/plants/torch_wood.png")));
            textures.put(IceMushroom, ImageIO.read(new File("assets/plants/ice_mushroom.png")));
            textures.put(Jalapeno, ImageIO.read(new File("assets/plants/jalapeno.png")));
            textures.put(Pumpkin, ImageIO.read(new File("assets/plants/pumpkin.png")));
            textures.put(SpikeRock, ImageIO.read(new File("assets/plants/spike_rock.png")));
            textures.put(SpikeWeed, ImageIO.read(new File("assets/plants/spike_weed.png")));
            textures.put(TallWallnut, ImageIO.read(new File("assets/plants/tall_wallnut.png")));

            textures.put(SeedsCabbagePult, ImageIO.read(new File("assets/seeds/cabbage_pult seeds.png")));
            textures.put(SeedsCherryBomb, ImageIO.read(new File("assets/seeds/cherry_bomb seeds.png")));
            textures.put(SeedsPeashooter, ImageIO.read(new File("assets/seeds/peashooter seeds.png")));
            textures.put(SeedsPotatoMine, ImageIO.read(new File("assets/seeds/potato_mine seeds.png")));
            textures.put(SeedsPuffshroom, ImageIO.read(new File("assets/seeds/puffshroom seeds.png")));
            textures.put(SeedsSnowPeashooter, ImageIO.read(new File("assets/seeds/snow_pea seeds.png")));
            textures.put(SeedsSunflower, ImageIO.read(new File("assets/seeds/sunflower seeds.png")));
            textures.put(SeedsWallnut, ImageIO.read(new File("assets/seeds/wallnut seeds.png")));
            textures.put(SeedsWatermelonPult, ImageIO.read(new File("assets/seeds/watermelon_pult seeds.png")));
            textures.put(SeedsTriplePeashooter, ImageIO.read(new File("assets/seeds/triple_peashooter seeds.png")));
            textures.put(SeedsCactus, ImageIO.read(new File("assets/seeds/cactus seeds.png")));
            textures.put(SeedsTorchWood, ImageIO.read(new File("assets/seeds/torch_wood seeds.png")));
            textures.put(SeedsIceMushroom, ImageIO.read(new File("assets/seeds/ice_mushroom seeds.png")));
            textures.put(SeedsJalapeno, ImageIO.read(new File("assets/seeds/jalapeno seeds.png")));
            textures.put(SeedsPumpkin, ImageIO.read(new File("assets/seeds/pumpkin seeds.png")));
            textures.put(SeedsSpikeRock, ImageIO.read(new File("assets/seeds/spike_rock seeds.png")));
            textures.put(SeedsSpikeWeed, ImageIO.read(new File("assets/seeds/spike_weed seeds.png")));
            textures.put(SeedsTallWallnut, ImageIO.read(new File("assets/seeds/tall_wallnut seeds.png")));

            textures.put(ZImp, ImageIO.read(new File("assets/zombie/imp.png")));
            textures.put(ZBasic, ImageIO.read(new File("assets/zombie/zombie basic.png")));
            textures.put(ZConehead, ImageIO.read(new File("assets/zombie/zombie cone.png")));
            textures.put(ZBuckethead, ImageIO.read(new File("assets/zombie/zombie bucket.png")));
            textures.put(ZDoor, ImageIO.read(new File("assets/zombie/zombie door.png")));
            textures.put(ZJackbox, ImageIO.read(new File("assets/zombie/zombie jackbox.png")));
            textures.put(ZZomboni, ImageIO.read(new File("assets/zombie/zomboni.png")));
            textures.put(ZBalloon, ImageIO.read(new File("assets/zombie/zombie balloon.png")));
            textures.put(ZYeti, ImageIO.read(new File("assets/zombie/yeti.png")));
            textures.put(ZGrave, ImageIO.read(new File("assets/zombie/grave_1.png")));
            textures.put(ZGrave1, ImageIO.read(new File("assets/zombie/grave_1.png")));
            textures.put(ZGrave2, ImageIO.read(new File("assets/zombie/grave_2.png")));
            textures.put(ZFlag, ImageIO.read(new File("assets/zombie/flag.png")));

            textures.put(ZSeedsImp, ImageIO.read(new File("assets/zombie_seeds/imp seeds.png")));
            textures.put(ZSeedsBasic, ImageIO.read(new File("assets/zombie_seeds/zombie basic seeds.png")));
            textures.put(ZSeedsConehead, ImageIO.read(new File("assets/zombie_seeds/zombie cone seeds.png")));
            textures.put(ZSeedsBuckethead, ImageIO.read(new File("assets/zombie_seeds/zombie bucket seeds.png")));
            textures.put(ZSeedsDoor, ImageIO.read(new File("assets/zombie_seeds/zombie door seeds.png")));
            textures.put(ZSeedsJackbox, ImageIO.read(new File("assets/zombie_seeds/zombie jackbox seeds.png")));
            textures.put(ZSeedsZomboni, ImageIO.read(new File("assets/zombie_seeds/zomboni seeds.png")));
            textures.put(ZSeedsBalloon, ImageIO.read(new File("assets/zombie_seeds/zombie balloon seeds.png")));
            textures.put(ZSeedsYeti, ImageIO.read(new File("assets/zombie_seeds/yeti seeds.png")));
            textures.put(ZSeedsGrave, ImageIO.read(new File("assets/zombie_seeds/grave seeds.png")));
            textures.put(ZSeedsFlag, ImageIO.read(new File("assets/zombie_seeds/flag seeds.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage getTexture(GameObjectType objType) {
        return textures.get(objType);
    }
}
