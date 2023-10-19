package com.rxnqst.pvz.threads;

import com.rxnqst.pvz.ChosenSeed;
import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.Sun;
import com.rxnqst.pvz.effects.Effect;
import com.rxnqst.pvz.gui.GameFrame;
import com.rxnqst.pvz.peas.*;
import com.rxnqst.pvz.plants.*;
import com.rxnqst.pvz.plants.attackFamily.*;
import com.rxnqst.pvz.plants.boomFamily.CherryBomb;
import com.rxnqst.pvz.plants.boomFamily.IceMushroom;
import com.rxnqst.pvz.plants.boomFamily.Jalapeno;
import com.rxnqst.pvz.plants.boomFamily.PotatoMine;
import com.rxnqst.pvz.plants.defenceFamily.Pumpkin;
import com.rxnqst.pvz.plants.defenceFamily.TallWallnut;
import com.rxnqst.pvz.plants.defenceFamily.Wallnut;
import com.rxnqst.pvz.plants.helpFamily.SpikeRock;
import com.rxnqst.pvz.plants.helpFamily.SpikeWeed;
import com.rxnqst.pvz.plants.helpFamily.Sunflower;
import com.rxnqst.pvz.plants.helpFamily.TorchWood;
import com.rxnqst.pvz.utils.Rect;
import com.rxnqst.pvz.zombies.*;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.ImageManager.getTexture;
import static com.rxnqst.pvz.ImageManager.ImgName;
import static com.rxnqst.pvz.utils.Utils.checkBoxesOverlap;
import static com.rxnqst.pvz.utils.Utils.checkCollision;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render implements Runnable {
    BufferedImage BG = getTexture(ImgName.BG);
    BufferedImage BUSH_1 = getTexture(ImgName.BUSH_1);
    BufferedImage BUSH_R = getTexture(ImgName.BUSH_R);
    BufferedImage FIELD = getTexture(ImgName.FIELD);
    BufferedImage FOG = getTexture(ImgName.FOG);
    BufferedImage SEED_INTERFACE = getTexture(ImgName.SEED_INTERFACE);
    BufferedImage ZOMBIE_INTERFACE = getTexture(ImgName.ZOMBIE_INTERFACE);
    BufferedImage SHOVEL_ICON = getTexture(ImgName.SHOVEL_ICON);
    BufferedImage SUNFLOWER_SEEDS = getTexture(ImgName.SUNFLOWER_SEEDS);
    BufferedImage PEASHOOTER_SEEDS = getTexture(ImgName.PEASHOOTER_SEEDS);
    BufferedImage WALLNUT_SEEDS = getTexture(ImgName.WALLNUT_SEEDS);
    BufferedImage POTATO_MINE_SEEDS = getTexture(ImgName.POTATO_MINE_SEEDS);
    BufferedImage SNOW_PEASHOOTER_SEEDS = getTexture(ImgName.SNOW_PEASHOOTER_SEEDS);
    BufferedImage PUFFSHROOM_SEEDS = getTexture(ImgName.PUFFSHROOM_SEEDS);
    BufferedImage CABBAGE_PULT_SEEDS = getTexture(ImgName.CABBAGE_PULT_SEEDS);
    BufferedImage CHERRY_BOMB_SEEDS = getTexture(ImgName.CHERRY_BOMB_SEEDS);
    BufferedImage TRIPLE_PEASHOOTER_SEEDS = getTexture(ImgName.TRIPLE_PEASHOOTER_SEEDS);
    BufferedImage WATERMELON_PULT_SEEDS = getTexture(ImgName.WATERMELON_PULT_SEEDS);
    BufferedImage CACTUS_SEEDS = getTexture(ImgName.CACTUS_SEEDS);
    BufferedImage PUMPKIN_SEEDS = getTexture(ImgName.PUMPKIN_SEEDS);
    BufferedImage TALL_WALLNUT_SEEDS = getTexture(ImgName.TALL_WALLNUT_SEEDS);
    BufferedImage TORCH_WOOD_SEEDS = getTexture(ImgName.TORCH_WOOD_SEEDS);
    BufferedImage ICE_MUSHROOM_SEEDS = getTexture(ImgName.ICE_MUSHROOM_SEEDS);
    BufferedImage JALAPENO_SEEDS = getTexture(ImgName.JALAPENO_SEEDS);
    BufferedImage SPIKE_ROCK_SEEDS = getTexture(ImgName.SPIKE_ROCK_SEEDS);
    BufferedImage SPIKE_WEED_SEEDS = getTexture(ImgName.SPIKE_WEED_SEEDS);

    BufferedImage ZOMBIE_BALLOON_SEEDS = getTexture(ImgName.ZOMBIE_BALLOON_SEEDS);
    BufferedImage ZOMBIE_BASIC_SEEDS = getTexture(ImgName.ZOMBIE_BASIC_SEEDS);
    BufferedImage ZOMBIE_CONEHEAD_SEEDS = getTexture(ImgName.ZOMBIE_CONEHEAD_SEEDS);
    BufferedImage ZOMBIE_BUCKETHEAD_SEEDS = getTexture(ImgName.ZOMBIE_BUCKETHEAD_SEEDS);
    BufferedImage YETI_SEEDS = getTexture(ImgName.YETI_SEEDS);
    BufferedImage ZOMBIE_ZOMBONI_SEEDS = getTexture(ImgName.ZOMBONI_SEEDS);
    BufferedImage ZOMBIE_DOOR_SEEDS = getTexture(ImgName.ZOMBIE_DOOR_SEEDS);
    BufferedImage ZOMBIE_IMP_SEEDS = getTexture(ImgName.IMP_SEEDS);
    BufferedImage ZOMBIE_JACKBOX_SEEDS = getTexture(ImgName.ZOMBIE_JACKBOX_SEEDS);
    BufferedImage GRAVE_SEEDS = getTexture(ImgName.GRAVE_SEEDS);
    BufferedImage FLAG_SEEDS = getTexture(ImgName.FLAG_SEEDS);
    BufferedImage SUN = getTexture(ImgName.SUN);

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            while (GameEngine.isGameRunning) {
                BufferedImage newScene = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2D = newScene.createGraphics();
                g2D.setFont(new Font("Times new Roman", Font.BOLD, 15));
                if (GameEngine.drawPlantChoose) {
                    drawUI(g2D);
                    drawSeeds(g2D);
                } else {
                    drawBottom(g2D);
                    drawPlants(g2D);
                    drawGraves(g2D);
                    drawPeas(g2D);
                    try {
                        drawZombies(g2D);
                    } catch (NoSuchFieldException | IllegalAccessException ignored) {
                    }
                    if (drawHP) drawHP(g2D);
                    drawLVL(g2D);
                    drawEffects(g2D);
                    drawTop(g2D);
                    drawSun(g2D);
                    drawMouse(g2D);
                    drawHitboxes(g2D);
                }
                g2D.dispose();
                GameFrame.gamePanel.setScene(newScene);
                try {
                    Thread.sleep(16);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    private void drawGraves(Graphics2D g2D) {
        for (int g = 0; g < graveList.size(); g++) {
            Grave grave = graveList.get(g);
            g2D.drawImage(grave.image, grave.hitbox.x, grave.hitbox.y, null);
        }
    }

    private void drawEffects(Graphics2D g2D) {
        for (int e = 0; e < effectList.size(); e++) {
            Effect ef = effectList.get(e);
            BufferedImage img = ef.atlas.getSubimage(
                    ef.hitbox.width * ef.frameIndex,
                    0,
                    ef.hitbox.width,
                    ef.hitbox.height);
            g2D.drawImage(img, ef.hitbox.x, ef.hitbox.y, null);
        }
    }

    private void drawHitboxes(Graphics2D g2D) {
        for (int p = 0; p < plantList.size(); p++) {
            Plant plant = plantList.get(p);
            g2D.setColor(Color.WHITE);
            if (checkBoxesOverlap(plant.hitbox, new Rect(mouseX, mouseY, 10, 10)))
                g2D.setColor(Color.RED);
            g2D.drawRect(plant.hitbox.x, plant.hitbox.y, plant.hitbox.width, plant.hitbox.height);
            if (plant instanceof PotatoMine)
                g2D.drawRect(
                        ((PotatoMine) plant).boomArea.x,
                        ((PotatoMine) plant).boomArea.y,
                        ((PotatoMine) plant).boomArea.width,
                        ((PotatoMine) plant).boomArea.height);
            if (plant instanceof CherryBomb)
                g2D.drawRect(
                        ((CherryBomb) plant).boomArea.x,
                        ((CherryBomb) plant).boomArea.y,
                        ((CherryBomb) plant).boomArea.width,
                        ((CherryBomb) plant).boomArea.height);
        }
        g2D.setColor(Color.WHITE);
        for (int z = 0; z < zombieList.size(); z++) {
            Zombie zombie = zombieList.get(z);
            g2D.setColor(Color.WHITE);
            if (checkBoxesOverlap(zombie.hitbox, new Rect(mouseX, mouseY, 10, 10)))
                g2D.setColor(Color.RED);
            g2D.drawRect(zombie.hitbox.x, zombie.hitbox.y, zombie.hitbox.width, zombie.hitbox.height);
            g2D.drawRect(zombie.head.x, zombie.head.y, zombie.head.width, zombie.head.height);
        }
        g2D.setColor(Color.WHITE);
        for (int p = 0; p < peaList.size(); p++) {
            Pea pea = peaList.get(p);
            g2D.setColor(Color.WHITE);
            if (checkBoxesOverlap(pea.hitbox, new Rect(mouseX, mouseY, 10, 10)))
                g2D.setColor(Color.RED);
            g2D.drawRect(pea.hitbox.x, pea.hitbox.y, pea.hitbox.width, pea.hitbox.height);
        }
        g2D.setColor(Color.WHITE);
    }

    private void drawSeeds(Graphics2D g2D) {
        for (SeedSlot seedSlot : SeedSlot.values()) {
            if (seedSlot != SeedSlot.SHOVEL && seedSlot != SeedSlot.POTATO_MINE_UNREADY && seedSlots.get(seedSlot) != null) {
                g2D.drawImage(
                        getTexture(ImgName.valueOf(seedSlot.toString() + "_SEEDS")),
                        seedSlots.get(seedSlot).x,
                        seedSlots.get(seedSlot).y,
                        null);
            }
        }
        for (int i = 0; i < chosenSeeds.size(); ++i) {
            ChosenSeed chosenSeed = chosenSeeds.get(i);
            if (chosenSeed.seedSlot != SeedSlot.SHOVEL && chosenSeed.seedSlot != SeedSlot.POTATO_MINE_UNREADY) {
                g2D.drawImage(
                        getTexture(ImgName.valueOf(chosenSeed.seedSlot.toString() + "_SEEDS")),
                        chosenSeed.box.x,
                        chosenSeed.box.y,
                        null);
            }
        }
    }

    private void drawUI(Graphics2D g2D) {
        g2D.drawImage(SEED_INTERFACE, 0, 0, null);
        g2D.drawString(
                "Press Enter to start!",
                Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        g2D.drawString(
                "G - to toggle Multiplayer/Solo mode",
                Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 + 20);
        g2D.drawString(
                "F - to chose Server/Client mode",
                Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 + 40);
        g2D.drawString(
                "D - to toggle Zombie/Plants mode",
                Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 + 60);
        g2D.drawString(
                "Current: " + (isMultiplayerOn ? "Multiplayer" : "Solo"),
                Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 + 80);
        if (isMultiplayerOn) {
            g2D.drawString(
                    "Current: " + (isServer ? "Server" : "Client"),
                    Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                    Toolkit.getDefaultToolkit().getScreenSize().height / 2 + 100);
            g2D.drawString(
                    "Mode: " + (isPlantsGameMode ? "Plants" : "Zombies"),
                    Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                    Toolkit.getDefaultToolkit().getScreenSize().height / 2 + 120);
        }
    }

    private void drawHP(Graphics2D g2D) {
        for (int z = 0; z < zombieList.size(); z++) {
            Zombie zombie = zombieList.get(z);
            g2D.drawString(zombie.hp + "", zombie.hitbox.x + zombie.hitbox.width / 4, zombie.hitbox.y + zombie.hitbox.height + 15);
        }
        for (int p = 0; p < plantList.size(); p++) {
            Plant plant = plantList.get(p);
            if (plant instanceof Pumpkin)
                g2D.drawString(plant.hp + "", plant.hitbox.x + plant.hitbox.width / 4, plant.hitbox.y - 15);
            else
                g2D.drawString(plant.hp + "", plant.hitbox.x + plant.hitbox.width / 4, plant.hitbox.y + plant.hitbox.height + 15);
        }
        for (int g = 0; g < graveList.size(); g++) {
            Grave grave = graveList.get(g);
            g2D.drawString(grave.hp + "", grave.hitbox.x + grave.hitbox.width / 4, grave.hitbox.y + grave.hitbox.height + 15);
        }
    }

    private void drawLVL(Graphics2D g2D) {
        for (int p = 0; p < plantList.size(); p++) {
            Plant plant = plantList.get(p);
            if (plant instanceof Pumpkin)
                g2D.drawString("LVL " + plant.level, plant.hitbox.x + 10, plant.hitbox.y - 30);
            else
                g2D.drawString("LVL " + plant.level, plant.hitbox.x + 10, plant.hitbox.y + plant.hitbox.height + 30);
        }
    }

    private void drawPeas(Graphics2D g2D) {
        for (int p = 0; p < peaList.size(); ++p) {
            Pea pea = peaList.get(p);
            g2D.drawImage(pea.image, pea.hitbox.x, pea.hitbox.y, null);
        }
    }

    private void drawTop(Graphics2D g2D) {
        g2D.drawImage(BUSH_1, -65, 950, null);
        g2D.drawImage(BUSH_1, -65 + 550, 950, null);
        g2D.drawImage(BUSH_1, -65 + 1100, 950, null);
        g2D.drawImage(BUSH_1, -65 + 1650, 950, null);
        g2D.drawImage(BUSH_R, 1740, 0, null);
        if (isPlantsGameMode) {
            g2D.drawImage(FOG, fogPosition, 0, null);
            g2D.drawImage(SEED_INTERFACE, 0, 0, null);
            g2D.drawImage(SHOVEL_ICON, 1050, 0, null);
            for (ChosenSeed chosenSeed : chosenSeeds) {
                Rect box = chosenSeed.box;
                if (chosenSeed.seedSlot == SeedSlot.SUNFLOWER && Sunflower.RELOAD == 0)
                    g2D.drawImage(SUNFLOWER_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.PEASHOOTER && Peashooter.RELOAD == 0)
                    g2D.drawImage(PEASHOOTER_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.WALLNUT && Wallnut.RELOAD == 0)
                    g2D.drawImage(WALLNUT_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.TALL_WALLNUT && TallWallnut.RELOAD == 0)
                    g2D.drawImage(TALL_WALLNUT_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.SNOW_PEASHOOTER && SnowPeashooter.RELOAD == 0)
                    g2D.drawImage(SNOW_PEASHOOTER_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.POTATO_MINE && PotatoMine.RELOAD == 0)
                    g2D.drawImage(POTATO_MINE_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.TORCH_WOOD && TorchWood.RELOAD == 0)
                    g2D.drawImage(TORCH_WOOD_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.TRIPLE_PEASHOOTER && TriplePeashooter.RELOAD == 0)
                    g2D.drawImage(TRIPLE_PEASHOOTER_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.WATERMELON_PULT && WatermelonPult.RELOAD == 0)
                    g2D.drawImage(WATERMELON_PULT_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.PUMPKIN && Pumpkin.RELOAD == 0)
                    g2D.drawImage(PUMPKIN_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.PUFFSHROOM && Puffshroom.RELOAD == 0)
                    g2D.drawImage(PUFFSHROOM_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.JALAPENO && Jalapeno.RELOAD == 0)
                    g2D.drawImage(JALAPENO_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.ICE_MUSHROOM && IceMushroom.RELOAD == 0)
                    g2D.drawImage(ICE_MUSHROOM_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.SPIKE_ROCK && SpikeRock.RELOAD == 0)
                    g2D.drawImage(SPIKE_ROCK_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.SPIKE_WEED && SpikeWeed.RELOAD == 0)
                    g2D.drawImage(SPIKE_WEED_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.CHERRY_BOMB && CherryBomb.RELOAD == 0)
                    g2D.drawImage(CHERRY_BOMB_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.CACTUS && Cactus.RELOAD == 0)
                    g2D.drawImage(CACTUS_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.CABBAGE_PULT && CabbagePult.RELOAD == 0)
                    g2D.drawImage(CABBAGE_PULT_SEEDS, box.x, box.y, null);
            }
            g2D.setColor(Color.WHITE);
            g2D.drawString("" + sunAmount, 20, 85);
            if (!isMultiplayerOn) {
                g2D.drawString("Zombie killed: " + zombieKilled + "/500 000", 1500, 50);
                g2D.drawString("Wave #: " + wavesCompleted, 1500, 80);
            }
        } else {
            g2D.drawImage(ZOMBIE_INTERFACE, 0, 0, null);
            g2D.drawString("" + brainsAmount, 20, 85);
            for (ChosenSeed chosenSeed : chosenSeeds) {
                Rect box = chosenSeed.box;
                if (chosenSeed.seedSlot == SeedSlot.ZOMBIE_BASIC)
                    g2D.drawImage(ZOMBIE_BASIC_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.ZOMBIE_CONEHEAD)
                    g2D.drawImage(ZOMBIE_CONEHEAD_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.ZOMBIE_BUCKETHEAD)
                    g2D.drawImage(ZOMBIE_BUCKETHEAD_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.ZOMBIE_DOOR)
                    g2D.drawImage(ZOMBIE_DOOR_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.IMP)
                    g2D.drawImage(ZOMBIE_IMP_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.ZOMBIE_JACKBOX)
                    g2D.drawImage(ZOMBIE_JACKBOX_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.YETI)
                    g2D.drawImage(YETI_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.ZOMBIE_BALLOON)
                    g2D.drawImage(ZOMBIE_BALLOON_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.ZOMBONI)
                    g2D.drawImage(ZOMBIE_ZOMBONI_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.GRAVE)
                    g2D.drawImage(GRAVE_SEEDS, box.x, box.y, null);
                if (chosenSeed.seedSlot == SeedSlot.FLAG)
                    g2D.drawImage(FLAG_SEEDS, box.x, box.y, null);
            }
        }
    }

    private void drawZombies(Graphics2D g2D) throws NoSuchFieldException, IllegalAccessException {
        for (int i = 0; i < zombieList.size(); i++) {
            Zombie zombie = zombieList.get(i);
            Point frameSizeEat = null;
            Point frameSizeWalk = null;
            BufferedImage image = null;
            if (zombie instanceof BasicZombie || zombie instanceof ZombieConehead || zombie instanceof ZombieBuckethead
                    || zombie instanceof Zomboni || zombie instanceof ZombieJackbox || zombie instanceof ZombieDoor
                    || zombie instanceof BalloonZombie) {
                frameSizeEat = (Point) zombie.getClass().getField("frameSizeEat").get(null);
                frameSizeWalk = (Point) zombie.getClass().getField("frameSizeWalk").get(null);
            }
            if (frameSizeEat != null && frameSizeWalk != null) {
                if (!zombie.isEating && zombie.walkAtlas != null) {
                    image = zombie.walkAtlas.getSubimage(frameSizeWalk.x * zombie.frameIndex, 0, frameSizeWalk.x, frameSizeWalk.y);
                } else if (zombie.eatAtlas != null) {
                    image = zombie.eatAtlas.getSubimage(frameSizeEat.x * zombie.frameIndex, 0, frameSizeEat.x, frameSizeEat.y);
                }
                if (image != null) {
                    g2D.drawImage(
                            image,
                            zombie.hitbox.x,
                            zombie.hitbox.y,
                            null);
                }
            } else {
                g2D.drawImage(getTexture(zombie.type), zombie.hitbox.x, zombie.hitbox.y, null);
            }
        }
    }

    private void drawPlants(Graphics2D g2D) {
        for (int p = 0; p < plantList.size(); ++p) {
            Plant plant = plantList.get(p);
            if (!(plant instanceof Pumpkin))
                g2D.drawImage(getTexture(ImgName.valueOf(plant.type.toString())), plant.hitbox.x, plant.hitbox.y, null);
        }
        for (int p = 0; p < plantList.size(); ++p) {
            Plant plant = plantList.get(p);
            if (plant instanceof Pumpkin)
                g2D.drawImage(getTexture(ImgName.valueOf(plant.type.toString())), plant.hitbox.x, plant.hitbox.y, null);
        }
    }

    private void drawMouse(Graphics2D g2D) {
        //TODO: move all images to its classes (make them as class field)
        if (selectedObject != null) {
            if (selectedObject == SeedSlot.GRAVE) {
                g2D.drawImage(getTexture(ImgName.GRAVE_1), mouseX, mouseY, null);
            } else if (selectedObject == SeedSlot.FLAG) {
                g2D.drawImage(getTexture(ImgName.FLAG), mouseX, mouseY, null);
            } else {
                g2D.drawImage(getTexture(ImgName.valueOf(selectedObject.toString())), mouseX, mouseY, null);
            }
        }
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 6; y++) {
                if (checkCollision(tiles[x][y], mouseX, mouseY)) {
                    g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    if (isPlantsGameMode) {
                        g2D.drawRect(x * 150, y * 150 + 90, 150, 150);
                    } else {
                        g2D.drawRect(-10, y * 150 + 90, 2000, 150);
                    }
                    g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    break;
                }
            }
        }
    }
    private void drawSun(Graphics2D g2D) {
        for (int i = 0; i < sunList.size(); i++) {
            Sun sun = sunList.get(i);
            g2D.drawImage(SUN, sun.hitbox.x, sun.hitbox.y, null);
        }
    }
    private void drawBottom(Graphics2D g2D) {
        g2D.drawImage(BG, 0, 0, null);
        g2D.drawImage(FIELD, 0, 90, null);
    }
}
