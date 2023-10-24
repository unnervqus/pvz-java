package com.rxnqst.pvz.threads;

import com.rxnqst.pvz.*;
import com.rxnqst.pvz.effects.ZomboniBoom;
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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.GameObjectSpawner.*;
import static com.rxnqst.pvz.GameObjectType.*;
import static com.rxnqst.pvz.GameSettings.*;
import static com.rxnqst.pvz.utils.Utils.checkBoxesOverlap;
import static com.rxnqst.pvz.utils.Utils.checkCollision;

public class Updater implements Runnable {
    private final Random r = new Random();

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            checkKeyboardInput();
            while (isGameRunning) {
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
                isMouseClicked = false;
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
        for (GameObjectType type : menuSeedSlots.keySet()) {
            if (isMouseClicked) {
                if (checkCollision(menuSeedSlots.get(type), clickX, clickY)) {
                    if (chosenSeeds.size() < 11) {
                        if (chosenSeeds.stream().filter(chosenSeed -> chosenSeed.seedType == type).toList().size() == 0) {
                            chosenSeeds.add(new ChosenSeed(type, new Rect(93 + 86 * chosenSeeds.size(), 5, 64, 86)));
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
            if (keys[KeyEvent.VK_ESCAPE]) {
                selectedObject = null;
            }
            if (keys[KeyEvent.VK_Z] && !keysOnHold[KeyEvent.VK_Z]) {
                newZombie(ZZomboni, 2);
            }
            if (isServer) if (keys[KeyEvent.VK_S]) stopGame();
            if (isServer) if (keys[KeyEvent.VK_B]) continueGame();
            if (keys[KeyEvent.VK_H] && !keysOnHold[KeyEvent.VK_H]) drawHP = !drawHP;
            if (keys[KeyEvent.VK_1]) selectedObject = chosenSeeds.get(0);
            if (keys[KeyEvent.VK_2] && chosenSeeds.size() > 2) selectedObject = chosenSeeds.get(1);
            if (keys[KeyEvent.VK_3] && chosenSeeds.size() > 3) selectedObject = chosenSeeds.get(2);
            if (keys[KeyEvent.VK_4] && chosenSeeds.size() > 4) selectedObject = chosenSeeds.get(3);
            if (keys[KeyEvent.VK_5] && chosenSeeds.size() > 5) selectedObject = chosenSeeds.get(4);
            if (keys[KeyEvent.VK_6] && chosenSeeds.size() > 6) selectedObject = chosenSeeds.get(5);
            if (keys[KeyEvent.VK_7] && chosenSeeds.size() > 7) selectedObject = chosenSeeds.get(6);
            if (keys[KeyEvent.VK_8] && chosenSeeds.size() > 8) selectedObject = chosenSeeds.get(7);
            if (keys[KeyEvent.VK_9] && chosenSeeds.size() > 9) selectedObject = chosenSeeds.get(8);
            if (keys[KeyEvent.VK_0] && chosenSeeds.size() > 10) selectedObject = chosenSeeds.get(9);
            if (keys[KeyEvent.VK_MINUS] && chosenSeeds.size() > 9) selectedObject = chosenSeeds.get(10);
        }
    }

    private void updateFogPosition() {
        fogPosition = 1500 - (zombieKilled / 3);
        if (fogPosition < 0) fogPosition = 0;
    }

    private void calculatePlants() {
        for (int p = 0; p < plantList.size(); ++p) {
            Plant plant = plantList.get(p);
            if (plant.type == PotatoMine || plant.type == _PotatoMine_unready)
                PlantActions.potatoMine((PotatoMine) plant);
            else if (plant.type == CherryBomb)
                PlantActions.cherryBomb((CherryBomb) plant);
            else if (plant.type == IceMushroom)
                PlantActions.iceMushroom((IceMushroom) plant);
            else if (plant.type == Jalapeno)
                PlantActions.jalapeno((Jalapeno) plant);
            else if (plant.type == SpikeRock)
                PlantActions.spikeRock((SpikeRock) plant);
            else if (plant.type == SpikeWeed)
                PlantActions.spikeWeed((SpikeWeed) plant);
            else if (plant.type == TorchWood)
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

    private void checkPlantsCooldown() {
        for (GameObjectType type : menuSeedSlots.keySet()) {
            GameObjectType seedsType = GameObjectType.valueOf(type.toString().substring(5));
            if (pvzContainers.get(seedsType).reloading > 0) {
                --pvzContainers.get(seedsType).reloading;
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
            if (zombie.type == ZJackbox) ZombieActions.jackInTheBox((ZombieJackbox) zombie);
            if (zombie.hp <= 0) {
                if (zombie.type == ZZomboni) {
                    effectList.add(new ZomboniBoom(zombie.hitbox.x, zombie.hitbox.y));
                }
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
                    if (plant.canShoot && zombieList.get(z).hitbox.x < 1850) {
                        if (plant.reloadCooldown == 0) {
                            PeaCreator.check(plant, zombieList.get(z));
                        }
                    }
                }
                for (int g = 0; g < graveList.size(); g++) {
                    if (plant.canShoot) {
                        if (plant.reloadCooldown == 0) {
                            PeaCreator.check(plant, graveList.get(g));
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
                            if (zombie.type != ZDoor && zombie.type != ZZomboni && zombie.type != ZYeti) {
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
                    if (grave.hp <= 0) {
                        if (isMultiplayerOn) deadGraves.add(grave);
                        graveList.remove(grave);
                    }
                    peaList.remove(pea);
                }
            }
            pea.hitbox.x += PROJECTILE_SPEED;
            if (pea.hitbox.x > 2000) peaList.remove(pea);
        }
    }

    private void generateSun() {
        if (SUN_GAME_RELOAD > 0) --SUN_GAME_RELOAD;
        else if (SUN_GAME_RELOAD == 0) {
            SUN_GAME_RELOAD = SKY_SUN_PROD_COOLDOWN;
            sunList.add(new Sun(r.nextInt(10, 1800), -400, r.nextInt(60, 1020), 25));
        }
        for (Plant plant : plantList) {
            if (plant.type == Sunflower) {
                if (((Sunflower) plant).sunReload == 0) {
                    sunList.add(new Sun(plant.hitbox.x, plant.hitbox.y - 10, plant.hitbox.y + 10, 25 + 25 * (plant.level - 1)));
                    ((Sunflower) plant).sunReload = SUNFLOWER_PROD_TIME_COOLDOWN;
                } else {
                    --((Sunflower) plant).sunReload;
                }
            }
        }
    }

    private void checkMouseClick() {
        if (isMouseClicked) {
            if (!checkAnyBoxCollision()) {
                if (selectedObject != null) {
                    if (isPlantsGameMode) {
                        if (selectedObject.seedType == Shovel) {
                            checkShovelClick();
                        } else checkPlantClick();
                    } else {
                        checkZombieClick();
                        checkMiscClick();
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

    private void checkMiscClick() {
        if (selectedObject.objType == ZGrave) {
            for (int x = 0; x < 12; x++) {
                for (int y = 0; y < 6; y++) {
                    if (checkCollision(tiles[x][y], clickX, clickY)) {
                        if (x < 9) {
                            selectedObject = null;
                            return;
                        }
                        for (int p = 0; p < plantList.size(); p++) {
                            Plant plant = plantList.get(p);
                            if (plant.line == y && plant.column == x) {
                                selectedObject = null;
                                return;
                            }
                        }
                        for (int g = 0; g < graveList.size(); g++) {
                            Grave grave = graveList.get(g);
                            if (grave.line == y && grave.column == x) {
                                selectedObject = null;
                                return;
                            }
                        }
                        if (brainsAmount >= pvzContainers.get(selectedObject.objType).COST) {
                            Grave grave = new Grave(x, y);
                            if (isMultiplayerOn) {
                                if (isServer) {
                                    serverGraveQueue.add(grave);
                                    //do nothing
                                } else {
                                    clientGraveQueue.add(grave);
                                }
                            }
                            graveList.add(grave);
                            brainsAmount -= 500;
                            selectedObject = null;
                            return;
                        }
                    }
                }
            }
        } else if (selectedObject.objType == ZFlag) {
            if (brainsAmount >= pvzContainers.get(selectedObject.objType).COST) {
                brainsAmount -= pvzContainers.get(selectedObject.objType).COST;
                for (int g = 0; g < graveList.size(); g++) {
                    Grave grave = graveList.get(g);
                    int r = randomizer.nextInt(0, 5);
                    Zombie zombie = null;
                    switch (r) {
                        case 0 -> zombie = new BasicZombie(grave.column * 150, grave.line);
                        case 1 -> zombie = new ZombieConehead(grave.column * 150, grave.line);
                        case 2 -> zombie = new ZombieBuckethead(grave.column * 150, grave.line);
                        case 3 -> zombie = new ZombieDoor(grave.column * 150, grave.line);
                        case 4 -> zombie = new ZombieImp(grave.column * 150, grave.line);
                    }
                    if (isMultiplayerOn) {
                        if (isServer) {
                            zombieList.add(zombie);
                            serverZombieQueue.add(zombie);
                        } else {
                            clientZombieQueue.add(zombie);
                        }
                    } else {
                        zombieList.add(zombie);
                    }
                }
            }
        }
        selectedObject = null;
    }

    private void checkZombieClick() {
        if (selectedObject.objType != ZGrave && selectedObject.objType != ZFlag) {
            for (int y = 0; y < 6; y++) {
                if ((clickY - 100) / 150 == y) {
                    newZombie(selectedObject.objType, 0, y);
                    break;
                }
            }
        }
    }

    //TODO: ONLY SERVER CAN PLAY BY PLANTS AT THE MOMENT
    private void checkPlantClick() {
        Plant target = null;
        Plant targetPumpkin = null;
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 6; y++) {
                if (checkCollision(tiles[x][y], clickX, clickY)) {
                    for (int g = 0; g < graveList.size(); g++) {
                        if (graveList.get(g).column == x && graveList.get(g).line == y) {
                            selectedObject = null;
                            return;
                        }
                    }
                    for (Plant plant : plantList) {
                        if (plant.column == x && plant.line == y) {
                            if (plant.type == Pumpkin) targetPumpkin = plant;
                            else target = plant;
                        }
                    }
                    //TODO: Is there better way to implement this?
                    if (selectedObject != null) {
                        if (pvzContainers.get(selectedObject.objType).reloading == 0 &&
                                sunAmount >= pvzContainers.get(selectedObject.objType).COST) {
                            if (target != null) {
                                if (selectedObject.objType == target.type) {
                                    upgradePlant(target);
                                    Sound.playPlantPlantedSound();
                                    return;
                                } else if (selectedObject.objType == Pumpkin) {
                                    if (targetPumpkin == null) {
                                        newPlant(selectedObject.objType, x, y);
                                    } else {
                                        upgradePlant(targetPumpkin);
                                    }
                                    Sound.playPlantPlantedSound();
                                    return;
                                }
                            } else {
                                if (selectedObject.objType == Pumpkin) {
                                    if (targetPumpkin == null) {
                                        newPlant(selectedObject.objType, x, y);
                                    } else {
                                        upgradePlant(targetPumpkin);
                                    }
                                } else {
                                    newPlant(selectedObject.objType, x, y);
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

    private void upgradePlant(Plant target) {
        if (target.level < 10) {
            target.levelUP();
            pvzContainers.get(selectedObject.objType).reloading =
                    pvzContainers.get(selectedObject.objType).RELOAD_TIME * SEED_RELOAD_MODIFIER;
            GameEngine.sunAmount -= pvzContainers.get(selectedObject.objType).COST;
            GameEngine.selectedObject = null;
        }
    }

    private boolean checkAnyBoxCollision() {
        for (ChosenSeed chosenSeed : chosenSeeds) {
            if (checkCollision(chosenSeed.box, clickX, clickY)) {
                if (isPlantsGameMode) {
                    if (chosenSeed.seedType == Shovel) {
                        Sound.playShovelSound();
                        selectedObject = chosenSeeds.get(chosenSeeds.size() - 1);
                    } else {
                        //TODO: mark for future
                        if (pvzContainers.get(chosenSeed.objType).reloading == 0) {
                            selectedObject = chosenSeed;
                            return true;
                        }
                    }
                } else if (chosenSeed.seedType != Shovel) {
                    selectedObject = chosenSeed;
                    return true;
                }
            }
        }
        return false;
    }

    private void checkShovelClick() {
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 6; y++) {
                if (checkCollision(tiles[x][y], clickX, clickY)) {
                    for (Plant plant : plantList) {
                        if (plant.column == x && plant.line == y) {
                            deadPlants.add(plant);
                            plantList.remove(plant);
                            selectedObject = null;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkJackboxSound() {
        for (int z = 0; z < zombieList.size(); ++z) {
            Zombie zombie = zombieList.get(z);
            if (zombie.type == ZJackbox) {
                return;
            }
        }
        Sound.stopJackintheboxSound();
    }

    private void showGameOverDialog() {
        gameOver();
    }
}