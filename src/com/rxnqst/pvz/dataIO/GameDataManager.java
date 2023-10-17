package com.rxnqst.pvz.dataIO;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.zombies.Zombie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameDataManager {
    public static void saveData() throws IOException {
        GameEngine.stopGame();
        File saveDir = new File("./saves");
        File newSaveFile;
        File[] saveFilesList;
        int[] saveNumbersList = new int[100];
        saveNumbersList[0] = 0;
        if(saveDir.exists()) {
            saveFilesList = saveDir.listFiles();
            int index = 1;
            for(File saveFile : saveFilesList) {
                if(saveFile.getName().startsWith("save_")) {
                    int saveNumber = Integer.parseInt(saveDir.getName().substring(5, 6));
                    saveNumbersList[index++] = saveNumber;
                }
            }
        }
        // 1 6 17 23 25 93
        int newSaveNumber = 0;
        for (int j = 0; j < 100; j++) {
            for (int k : saveNumbersList) {
                if (newSaveNumber != k) {
                    newSaveFile = new File("./saves/save_" + newSaveNumber + ".save");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newSaveFile));
                    for (Plant plant : GameEngine.plantList) {
                        writer.write(String.format("{\n" +
                                "\nTYPE: " + plant.type +
                                "\nTILE_X: " + plant.column +
                                "\nTILE_Y: " + plant.line +
                                "\nLVL: " + plant.level +
                                "\nHP: " + plant.hp +
                                "\ncanShoot: " + plant.canShoot +
                                "}"));
                        writer.newLine();
                    }
                    for (Zombie zombie : GameEngine.zombieList) {
                        writer.write(String.format("{\n" +
                                "\nTYPE: " + zombie.type +
                                "\nPOS_X: " + zombie.hitbox.x +
                                "\nPOS_Y: " + zombie.hitbox.y +
                                "\nHP: " + zombie.hp +
                                "\nSPEED: " + zombie.speed +
                                "\nDMG: " + zombie.dmg +
                                "\nFREEZE_DELAY: " + zombie.freezeDelay +
                                "}"));
                        writer.newLine();
                    }
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
    public static void loadData() {}
}
