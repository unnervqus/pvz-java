package com.rxnqst.pvz.threads;

import com.rxnqst.pvz.*;
import com.rxnqst.pvz.effects.Effect;
import com.rxnqst.pvz.gui.GameFrame;
import com.rxnqst.pvz.peas.*;
import com.rxnqst.pvz.plants.*;
import com.rxnqst.pvz.plants.boomFamily.CherryBomb;
import com.rxnqst.pvz.plants.boomFamily.PotatoMine;
import com.rxnqst.pvz.plants.defenceFamily.Pumpkin;
import com.rxnqst.pvz.utils.Rect;
import com.rxnqst.pvz.zombies.*;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.ImageManager.getTexture;
import static com.rxnqst.pvz.utils.Utils.checkBoxesOverlap;
import static com.rxnqst.pvz.utils.Utils.checkCollision;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render implements Runnable {
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
                    drawZombies(g2D);
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
        for (GameObjectType type : menuSeedSlots.keySet()) {
            g2D.drawImage(getTexture(type), menuSeedSlots.get(type).x, menuSeedSlots.get(type).y, null);
        }
        for (int i = 0; i < chosenSeeds.size(); ++i) {
            ChosenSeed chosenSeed = chosenSeeds.get(i);
            g2D.drawImage(getTexture(chosenSeed.seedType), chosenSeed.box.x, chosenSeed.box.y, null);
        }
    }

    private void drawUI(Graphics2D g2D) {
        g2D.drawImage(getTexture(GameObjectType.SeedInterface), 0, 0, null);
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
        g2D.drawImage(getTexture(GameObjectType.Bush_1), -65, 950, null);
        g2D.drawImage(getTexture(GameObjectType.Bush_1), -65 + 550, 950, null);
        g2D.drawImage(getTexture(GameObjectType.Bush_1), -65 + 1100, 950, null);
        g2D.drawImage(getTexture(GameObjectType.Bush_1), -65 + 1650, 950, null);
        g2D.drawImage(getTexture(GameObjectType.Bush_R), 1740, 0, null);
        if (isPlantsGameMode) {
            g2D.drawImage(getTexture(GameObjectType.Fog), fogPosition, 0, null);
            g2D.drawImage(getTexture(GameObjectType.SeedInterface), 0, 0, null);
            g2D.drawImage(getTexture(GameObjectType.ShovelIcon), 1050, 0, null);
            for (ChosenSeed chosenSeed : chosenSeeds) {
                Rect box = chosenSeed.box;
                // TODO: mark for future
                GameObjectType plantType;
                if(chosenSeed.seedType != GameObjectType.Shovel) {
                    plantType = GameObjectType.valueOf(chosenSeed.seedType.toString().substring(5));
                    if (pvzContainers.get(plantType).reloading == 0)
                        g2D.drawImage(getTexture(chosenSeed.seedType), box.x, box.y, null);
                }
            }
            g2D.setColor(Color.WHITE);
            g2D.drawString("" + sunAmount, 20, 85);
            if (!isMultiplayerOn) {
                g2D.drawString("Zombie killed: " + zombieKilled + "/500 000", 1500, 50);
                g2D.drawString("Wave #: " + wavesCompleted, 1500, 80);
            }
        } else {
            g2D.drawImage(getTexture(GameObjectType.ZombieInterface), 0, 0, null);
            g2D.drawString("" + brainsAmount, 20, 85);
            for (ChosenSeed chosenSeed : chosenSeeds) {
                Rect box = chosenSeed.box;
                g2D.drawImage(getTexture(chosenSeed.seedType), box.x, box.y, null);
            }
        }
    }

    private void drawZombies(Graphics2D g2D) {
        for (int z = 0; z < zombieList.size(); z++) {
            Zombie zombie = zombieList.get(z);
            PvZContainer container = pvzContainers.get(zombie.type);
            Point frameSizeEat = container.frameSizeEat;
            Point frameSizeWalk = container.frameSizeWalk;
            BufferedImage image = container.image;
            if(frameSizeEat != null && zombie.isEating) {
                image = container.eatAtlas.getSubimage(zombie.frameIndex * frameSizeEat.x, 0, frameSizeEat.x, frameSizeEat.y);
            } else if(frameSizeWalk != null) {
                image = container.walkAtlas.getSubimage(zombie.frameIndex * frameSizeWalk.x, 0, frameSizeWalk.x, frameSizeWalk.y);
            }
            g2D.drawImage(image, zombie.hitbox.x, zombie.hitbox.y, null);
        }
    }

    private void drawPlants(Graphics2D g2D) {
        for (int p = 0; p < plantList.size(); ++p) {
            Plant plant = plantList.get(p);
            if (!(plant instanceof Pumpkin))
                g2D.drawImage(getTexture(plant.type), plant.hitbox.x, plant.hitbox.y, null);
        }
        for (int p = 0; p < plantList.size(); ++p) {
            Plant plant = plantList.get(p);
            if (plant instanceof Pumpkin)
                g2D.drawImage(getTexture(plant.type), plant.hitbox.x, plant.hitbox.y, null);
        }
    }

    private void drawMouse(Graphics2D g2D) {
        if (selectedObject != null) {
            g2D.drawImage(getTexture(selectedObject.objType), mouseX, mouseY, null);
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
            g2D.drawImage(getTexture(GameObjectType.Sun), sun.hitbox.x, sun.hitbox.y, null);
        }
    }
    private void drawBottom(Graphics2D g2D) {
        g2D.drawImage(getTexture(GameObjectType.BG), 0, 0, null);
        g2D.drawImage(getTexture(GameObjectType.Field), 0, 90, null);
    }
}
