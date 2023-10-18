package com.rxnqst.pvz.threads;

import com.rxnqst.pvz.*;
import com.rxnqst.pvz.peas.Pea;
import com.rxnqst.pvz.peas.SnowPea;
import com.rxnqst.pvz.peas.Needle;
import com.rxnqst.pvz.plants.*;
import com.rxnqst.pvz.plants.boomFamily.CherryBomb;
import com.rxnqst.pvz.plants.boomFamily.IceMushroom;
import com.rxnqst.pvz.plants.boomFamily.Jalapeno;
import com.rxnqst.pvz.plants.boomFamily.PotatoMine;
import com.rxnqst.pvz.plants.helpFamily.SpikeRock;
import com.rxnqst.pvz.plants.helpFamily.SpikeWeed;
import com.rxnqst.pvz.plants.helpFamily.Sunflower;
import com.rxnqst.pvz.plants.helpFamily.TorchWood;
import com.rxnqst.pvz.utils.Rect;
import com.rxnqst.pvz.zombies.*;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.GameEngine.SeedSlot.*;
import static com.rxnqst.pvz.GameSettings.*;
import static com.rxnqst.pvz.utils.Utils.checkBoxesOverlap;
import static com.rxnqst.pvz.utils.Utils.checkCollision;
import static com.rxnqst.pvz.ImageManager.ImgName;

public class Updater implements Runnable {
    private final Random r = new Random();

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            checkKeyboardInput();
            while (isGameRunning) {
                try {
                    checkKeyboardInput();
                    if (drawPlantChoose) {
                        checkSeedsChoice();
                    } else {
                        checkMouseClick();
                        if (isMultiplayerOn) {
                            if (isServer && server.clientSocket == null) {
                                continue;
                            } else if (!isServer && client.socket == null) {
                                continue;
                            }
                        }
                        checkPlantsCooldown();
                        if (isServer) {
                            if (isPlantsGameMode) generateSun();
                            calculateSunsMove();
                            calculateBrainsAmount();
                        }
                        calculatePeas();
                        calculatePlants();
                        calculateZombies();
                        updateFogPosition();
                        checkJackboxSound();
                    }
                    checkKeyboardHoldInput();
                    clickX = 0;
                    clickY = 0;
                    isMouseClicked = false;
                } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException | InstantiationException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(25);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    private void calculateBrainsAmount() {
        if (brainCooldown > 0) {
            --brainCooldown;
        } else if (brainCooldown == 0) {
            brainsAmount++;
            brainCooldown = BRAIN_INCREASE_COOLDOWN;
        }
        if (brainBonusCooldown > 0) --brainBonusCooldown;
        else {
            brainsAmount += brainBonus;
            brainBonus += 75;
            brainBonusCooldown = BRAIN_BONUS_COOLDOWN;
        }
    }

    private void checkSeedsChoice() {
        for (SeedSlot seedSlot : seedSlots.keySet()) {
            if (isMouseClicked) {
                if (checkCollision(seedSlots.get(seedSlot), clickX, clickY)) {
                    if (chosenSeeds.size() < 11) {
                        if (chosenSeeds.stream().filter(chosenSeed -> chosenSeed.seedSlot == seedSlot).toList().size() == 0) {
                            chosenSeeds.add(new ChosenSeed(seedSlot, new Rect(93 + 86 * chosenSeeds.size(), 5, 64, 86)));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < chosenSeeds.size(); i++) {
            ChosenSeed chosenSeed = chosenSeeds.get(i);
            if (isMouseClicked) {
                if (checkCollision(chosenSeed.box, clickX, clickY)) {
                    chosenSeeds.remove(chosenSeed);
                    break;
                }
            }
        }
    }

    private void checkKeyboardHoldInput() {
        if (keys[KeyEvent.VK_H]) keysOnHold[KeyEvent.VK_H] = true;
        if (keys[KeyEvent.VK_Z]) keysOnHold[KeyEvent.VK_Z] = true;
        if (keys[KeyEvent.VK_G]) keysOnHold[KeyEvent.VK_G] = true;
        if (keys[KeyEvent.VK_F]) keysOnHold[KeyEvent.VK_F] = true;
        if (keys[KeyEvent.VK_D]) keysOnHold[KeyEvent.VK_D] = true;
    }

    private void checkKeyboardInput() {
        if (drawPlantChoose) {
            if (keys[KeyEvent.VK_ENTER]) if (isPlantsGameMode) {
                if (chosenSeeds.size() > 1 && !waveGenThread.isAlive()) {
                    drawPlantChoose = false;
                    GameEngine.startGame();
                }
            } else {
                drawPlantChoose = false;
                GameEngine.startGame();
            }
            if (keys[KeyEvent.VK_G] && !keysOnHold[KeyEvent.VK_G]) isMultiplayerOn = !isMultiplayerOn;
            if (keys[KeyEvent.VK_F] && !keysOnHold[KeyEvent.VK_F]) isServer = !isServer;
            if (keys[KeyEvent.VK_D] && !keysOnHold[KeyEvent.VK_D]) isPlantsGameMode = !isPlantsGameMode;
        } else {
            if (keys[KeyEvent.VK_ESCAPE]) selectedObject = null;
            if (keys[KeyEvent.VK_Z] && !keysOnHold[KeyEvent.VK_Z]) {
                zombieList.add(new BasicZombie(1700, 1));
                zombieList.add(new ZombieConehead(1700, 2));
                zombieList.add(new ZombieBuckethead(1700, 3));
            }
            if (isServer) if (keys[KeyEvent.VK_S]) stopGame();
            if (isServer) if (keys[KeyEvent.VK_B]) continueGame();
            if (keys[KeyEvent.VK_H] && !keysOnHold[KeyEvent.VK_H]) drawHP = !drawHP;
            if (keys[KeyEvent.VK_1]) selectedObject = chosenSeeds.get(0).seedSlot;
            if (keys[KeyEvent.VK_2] && chosenSeeds.size() > 2) selectedObject = chosenSeeds.get(1).seedSlot;
            if (keys[KeyEvent.VK_3] && chosenSeeds.size() > 3) selectedObject = chosenSeeds.get(2).seedSlot;
            if (keys[KeyEvent.VK_4] && chosenSeeds.size() > 4) selectedObject = chosenSeeds.get(3).seedSlot;
            if (keys[KeyEvent.VK_5] && chosenSeeds.size() > 5) selectedObject = chosenSeeds.get(4).seedSlot;
            if (keys[KeyEvent.VK_6] && chosenSeeds.size() > 6) selectedObject = chosenSeeds.get(5).seedSlot;
            if (keys[KeyEvent.VK_7] && chosenSeeds.size() > 7) selectedObject = chosenSeeds.get(6).seedSlot;
            if (keys[KeyEvent.VK_8] && chosenSeeds.size() > 8) selectedObject = chosenSeeds.get(7).seedSlot;
            if (keys[KeyEvent.VK_9] && chosenSeeds.size() > 9) selectedObject = chosenSeeds.get(8).seedSlot;
            if (keys[KeyEvent.VK_0] && chosenSeeds.size() > 10) selectedObject = chosenSeeds.get(9).seedSlot;
            if (keys[KeyEvent.VK_MINUS] && chosenSeeds.size() > 9) selectedObject = chosenSeeds.get(10).seedSlot;
        }
    }

    private void updateFogPosition() {
        fogPosition = 1500 - (zombieKilled / 3);
        if (fogPosition < 0) fogPosition = 0;
    }

    private void calculatePlants() {
        for (int p = 0; p < plantList.size(); ++p) {
            Plant plant = plantList.get(p);
            if (plant.type == SeedSlot.POTATO_MINE || plant.type == POTATO_MINE_UNREADY)
                PlantActions.potatoMine((PotatoMine) plant);
            else if (plant.type == SeedSlot.CHERRY_BOMB)
                PlantActions.cherryBomb((CherryBomb) plant);
            else if (plant.type == SeedSlot.ICE_MUSHROOM)
                PlantActions.iceMushroom((IceMushroom) plant);
            else if (plant.type == SeedSlot.JALAPENO)
                PlantActions.jalapeno((Jalapeno) plant);
            else if (plant.type == SeedSlot.SPIKE_ROCK)
                PlantActions.spikeRock((SpikeRock) plant);
            else if (plant.type == SeedSlot.SPIKE_WEED)
                PlantActions.spikeWeed((SpikeWeed) plant);
            else if (plant.type == TORCH_WOOD)
                PlantActions.torchWood((TorchWood) plant);
            if (plant.reloadCooldown > 0) --plant.reloadCooldown;
        }
        Iterator<Plant> plantIter = plantList.iterator();
        while (plantIter.hasNext()) {
            Plant plant = plantIter.next();
            if (plant.hp <= 0) {
                deadPlants.add(plant);
                plantIter.remove();
            }
        }
    }

    private void calculateSunsMove() {
        for (Sun sun : sunList) {
            if (sun.hitbox.y < sun.targetY) ++sun.hitbox.y;
        }
    }

    private void checkPlantsCooldown() throws NoSuchFieldException, IllegalAccessException {
        for (SeedSlot seedSlot : seedSlots.keySet()) {
            int reload = seedSlot.objClass.getField("RELOAD").getInt(null);
            if (reload > 0) {
                seedSlot.objClass.getField("RELOAD").setInt(null, --reload);
            }
        }
    }

    private void calculateZombies() {
        for (int z = 0; z < zombieList.size(); ++z) {
            Zombie zombie = zombieList.get(z);
            for (int p = 0; p < plantList.size(); p++) {
                Plant plant = plantList.get(p);
                if (checkBoxesOverlap(zombie.head, plant.hitbox) && zombie.freezeDelay == 0) {
                    brainsAmount += Math.min(plant.hp, zombie.dmg);
                    plant.hp -= zombie.dmg;
                    zombie.isEating = true;
                    break;
                }
                zombie.isEating = false;
            }
            if (plantList.size() == 0) zombie.isEating = false;
            if (!zombie.isEating) {
                if (zombie.freezeDelay > 0) --zombie.freezeDelay;
                else {
                    zombie.hitbox.x -= zombie.speed;
                    zombie.head.x -= zombie.speed;
                }
            }
        }
        for (int i = 0; i < zombieList.size(); i++) {
            Zombie zombie = zombieList.get(i);
            if (zombie.type == ImgName.ZOMBIE_JACKBOX) ZombieActions.jackInTheBox((ZombieJackbox) zombie);
            if (zombie.hp <= 0) {
                zombieKilled += 1;
                deadZombies.add(zombie);
                zombieList.remove(zombie);
            }
        }
        for (int z = 0; z < zombieList.size(); ++z) {
            Zombie zombie = zombieList.get(z);
            if (zombie.hitbox.x <= 0) {
                showGameOverDialog();
            }
        }
    }

    private void calculatePeas() {
        if (isServer) {
            for (int p = 0; p < plantList.size(); ++p) {
                Plant plant = plantList.get(p);
                for (int z = 0; z < zombieList.size(); ++z) {
                    Zombie zombie = zombieList.get(z);
                    if (plant.canShoot && zombie.hitbox.x < 1850) {
                        if (plant.reloadCooldown == 0) {
                            PeaCreator.check(plant, zombie);
                        }
                    }
                }
                for (int g = 0; g < graveList.size(); g++) {
                    Grave grave = graveList.get(g);
                    if (plant.canShoot) {
                        if (plant.reloadCooldown == 0) {
                            PeaCreator.check(plant, grave);
                        }
                    }
                }
            }
        }
        for (int p = 0; p < peaList.size(); ++p) {
            Pea pea = peaList.get(p);
            for (int z = 0; z < zombieList.size(); ++z) {
                Zombie zombie = zombieList.get(z);
                if (checkBoxesOverlap(zombie.hitbox, pea.hitbox)) {
                    if (pea instanceof SnowPea) {
                        if (!pea.isFired) {
                            if (zombie.type != ImgName.ZOMBIE_DOOR && zombie.type != ImgName.ZOMBONI && zombie.type != ImgName.YETI) {
                                if (zombie.freezeDelay < ((SnowPea) pea).freezeTime)
                                    zombie.freezeDelay = ((SnowPea) pea).freezeTime;
                            }
                        }
                        zombie.hp -= pea.dmg;
                        peaList.remove(pea);
                        break;
                    } else if (pea instanceof Needle) {
                        if (Arrays.stream(((Needle) pea).damagedZombies).filter(zombie1 -> zombie1 == zombie).toList().size() == 0) {
                            zombie.hp -= pea.dmg;
                            ((Needle) pea).damagedZombies[((Needle) pea).zombieHits++] = zombie;
                        }
                        if (((Needle) pea).zombieHits == ((Needle) pea).maxZombieHits) {
                            peaList.remove(pea);
                            break;
                        }
                    } else {
                        zombie.hp -= pea.dmg;
                        peaList.remove(pea);
                        break;
                    }
                }
            }
            for (int g = 0; g < graveList.size(); g++) {
                Grave grave = graveList.get(g);
                if (checkBoxesOverlap(grave.hitbox, pea.hitbox)) {
                    grave.hp -= pea.dmg;
                    peaList.remove(pea);
                }
            }
            pea.hitbox.x += PEA_SPEED;
            if (pea.hitbox.x > 2000) peaList.remove(pea);
        }
    }

    private void generateSun() {
        if (SUN_GAME_RELOAD > 0) --SUN_GAME_RELOAD;
        else if (SUN_GAME_RELOAD == 0) {
            SUN_GAME_RELOAD = SUN_PROD;
            sunList.add(new Sun(r.nextInt(10, 1800), -400, r.nextInt(60, 1020), 25));
        }
        for (Plant plant : plantList) {
            if (plant.type == SeedSlot.SUNFLOWER) {
                if (((Sunflower) plant).sunReload == 0) {
                    sunList.add(new Sun(plant.hitbox.x, plant.hitbox.y - 10, plant.hitbox.y + 10, 25 + 25 * (plant.level - 1)));
                    ((Sunflower) plant).sunReload = SUNFLOWER_PROD_TIME;
                } else {
                    --((Sunflower) plant).sunReload;
                }
            }
        }
    }

    private void checkMouseClick() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        if (!checkAnyBoxCollision()) {
            if (selectedObject != null) {
                if (isMouseClicked) {
                    if (isPlantsGameMode) {
                        if (selectedObject == SeedSlot.SHOVEL) {
                            checkShovelClick();
                        } else checkPlantClick();
                    } else {
                        checkZombieClick();
                        checkGraveClick();
                    }
                }
            }
        } else {
            Sound.playSeedSelectSound();
        }
        if (isServer) {
            for (int s = 0; s < sunList.size(); ++s) {
                Sun sun = sunList.get(s);
                if (checkCollision(sun.hitbox, mouseX, mouseY)) {
                    sunList.remove(sun);
                    Sound.playSunCollectedSound();
                    sunAmount += sun.value;
                }
            }
        }
    }

    private void checkGraveClick() {
        if (selectedObject == GRAVE) {
            for (int x = 0; x < 12; x++) {
                for (int y = 0; y < 6; y++) {
                    if (checkCollision(tiles[x][y], clickX, clickY)) {
                        if(x < 9) {
                            selectedObject = null;
                            return;
                        }
                        for (int p = 0; p < plantList.size(); p++) {
                            Plant plant = plantList.get(p);
                            if(plant.line == y && plant.column == x) {
                               selectedObject = null;
                               return;
                            }
                        }
                        for (int g = 0; g < graveList.size(); g++) {
                            Grave grave = graveList.get(g);
                            if(grave.line == y && grave.column == x) {
                                selectedObject = null;
                                return;
                            }
                        }
                        graveList.add(new Grave(x, y));
                        brainsAmount -= 500;
                        selectedObject = null;
                        return;
                    }
                }
            }
        }
    }

    private void checkZombieClick() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        if (selectedObject != GRAVE) {
            for (int y = 0; y < 6; y++) {
                if ((clickY - 100) / 150 == y) {
                    if (selectedObject.objClass.getField("COST").getInt(null) <= brainsAmount) {
                        if (isServer) {
                            Zombie zombie = (Zombie) selectedObject.objClass.getConstructors()[0].newInstance(2000, y);
                            zombieList.add(zombie);
                            serverZombieQueue.add(zombie);
                        } else {
                            clientZombieQueue.add((Zombie) selectedObject.objClass.getConstructors()[0].newInstance(2000, y));
                        }
                        brainsAmount -= selectedObject.objClass.getField("COST").getInt(null);
                        selectedObject = null;
                        break;
                    }
                }
            }
        }
    }

    //TODO: ONLY SERVER CAN PLAY BY PLANTS AT THE MOMENT
    private void checkPlantClick() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Plant target = null;
        Plant targetPumpkin = null;
        for (Integer x = 0; x < 12; x++) {
            for (int y = 0; y < 6; y++) {
                if (checkCollision(tiles[x][y], clickX, clickY)) {
                    for (int g = 0; g < graveList.size(); g++) {
                        Grave grave = graveList.get(g);
                        if (grave.column == x && grave.line == y) {
                            selectedObject = null;
                            return;
                        }
                    }
                    for (Plant plant : plantList) {
                        if (plant.column == x && plant.line == y) {
                            if (plant.type == PUMPKIN) targetPumpkin = plant;
                            else target = plant;
                        }
                    }
                    //TODO: Is there better way to implement this?
                    if (selectedObject != null) {
                        if (selectedObject.objClass.getField("RELOAD").getInt(null) == 0
                                && sunAmount >= selectedObject.objClass.getField("COST").getInt(null)) {
                            if (target != null) {
                                if (selectedObject == target.type) {
                                    upgradePlant(target);
                                    Sound.playPlantPlantedSound();
                                    return;
                                } else if (selectedObject == PUMPKIN) {
                                    if (targetPumpkin == null) {
                                        Plant plant = (Plant) selectedObject.objClass.getConstructors()[0].newInstance(x, y);
                                        plantList.add(plant);
                                        serverPlantQueue.add(plant);
                                    } else {
                                        upgradePlant(targetPumpkin);
                                    }
                                    Sound.playPlantPlantedSound();
                                    return;
                                }
                            } else {
                                if (selectedObject == PUMPKIN) {
                                    if (targetPumpkin == null) {
                                        Plant plant = (Plant) selectedObject.objClass.getConstructors()[0].newInstance(x, y);
                                        plantList.add(plant);
                                        serverPlantQueue.add(plant);
                                    } else {
                                        upgradePlant(targetPumpkin);
                                    }
                                } else {
                                    Plant plant = (Plant) selectedObject.objClass.getConstructors()[0].newInstance(x, y);
                                    plantList.add(plant);
                                    serverPlantQueue.add(plant);
                                }
                                Sound.playPlantPlantedSound();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void upgradePlant(Plant target) throws NoSuchFieldException, IllegalAccessException {
        if (target.level < 10) {
            target.levelUP();
            selectedObject.objClass.getField("RELOAD").set(null, selectedObject.objClass.getField("RELOAD_TIME").getInt(null) * SEED_RELOAD_MODIFIER);
            GameEngine.sunAmount -= selectedObject.objClass.getField("COST").getInt(null);
            GameEngine.selectedObject = null;
        }
    }

    private boolean checkAnyBoxCollision() throws NoSuchFieldException, IllegalAccessException {
        for (ChosenSeed chosenSeed : chosenSeeds) {
            if (checkCollision(chosenSeed.box, clickX, clickY)) {
                if (isPlantsGameMode) {
                    if (chosenSeed.seedSlot == SHOVEL) {
                        Sound.playShovelSound();
                        selectedObject = SHOVEL;
                    } else {
                        if (chosenSeed.seedSlot.objClass.getField("RELOAD").getInt(null) == 0) {
                            selectedObject = chosenSeed.seedSlot;
                            return true;
                        }
                    }
                } else if (chosenSeed.seedSlot != SHOVEL) {
                    selectedObject = chosenSeed.seedSlot;
                    return true;
                }
            }
        }
        return false;
    }

    private void checkShovelClick() {
        int lineX = 0;
        int lineY = 0;
        boolean isPlantDugOut = false;
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 6; y++) {
                if (checkCollision(tiles[x][y], clickX, clickY)) {
                    lineX = x;
                    lineY = y;
                    isPlantDugOut = true;
                }
            }
        }
        if (isPlantDugOut) {
            for (Plant plant : plantList) {
                if (plant.column == lineX && plant.line == lineY) {
                    deadPlants.add(plant);
                    plantList.remove(plant);
                    selectedObject = null;
                    break;
                }
            }
        }
    }

    private void checkJackboxSound() {
        for (int z = 0; z < zombieList.size(); ++z) {
            Zombie zombie = zombieList.get(z);
            if (zombie.type == ImgName.ZOMBIE_JACKBOX) {
                return;
            }
        }
        Sound.stopJackintheboxSound();
    }

    private void showGameOverDialog() {
        gameOver();
    }
}