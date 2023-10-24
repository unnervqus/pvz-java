package com.rxnqst.pvz;

import com.rxnqst.pvz.effects.Effect;
import com.rxnqst.pvz.net.Client;
import com.rxnqst.pvz.net.Server;
import com.rxnqst.pvz.peas.Pea;
import com.rxnqst.pvz.plants.*;
import com.rxnqst.pvz.threads.Animator;
import com.rxnqst.pvz.threads.Render;
import com.rxnqst.pvz.threads.Updater;
import com.rxnqst.pvz.threads.WaveGenerator;
import com.rxnqst.pvz.utils.Rect;
import com.rxnqst.pvz.zombies.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import static com.rxnqst.pvz.GameSettings.*;
import static com.rxnqst.pvz.ImageManager.getTexture;
import static com.rxnqst.pvz.AnimationManager.AtlasName;

public class GameEngine implements KeyListener, MouseMotionListener, MouseListener {
    public static final Thread renderThread = new Thread(new Render());
    public static final Thread updateThread = new Thread(new Updater());
    public static final Thread animationThread = new Thread(new Animator());
    public static final WaveGenerator waveGenerator = new WaveGenerator();
    public static final Thread waveGenThread = new Thread(waveGenerator);
    public static boolean isGameRunning = false;
    public static boolean[] keys = new boolean[120];
    public static boolean[] keysOnHold = new boolean[120];
    public static int mouseX = 0;
    public static int mouseY = 0;
    public static int clickX = 0;
    public static int clickY = 0;
    public static boolean isMouseClicked = false;
    public static boolean isGameOver = false;
    public static boolean drawHP = false;
    public static int fogPosition = 1500;
    public static int sunAmount = SUN_AMOUNT_ON_START;
    public static int brainsAmount = BRAINS_AMOUNT_ON_START;
    public static int brainBonus = 250;
    public static int brainCooldown = 0;
    public static int brainBonusCooldown = BRAIN_BONUS_COOLDOWN;
    public static final ArrayList<ChosenSeed> chosenSeeds = new ArrayList<>();
    public static final HashMap<GameObjectType, Rect> menuSeedSlots = new HashMap<>();
    public static ArrayList<Effect> effectList = new ArrayList<>();
    public static ArrayList<Plant> plantList = new ArrayList<>();
    public static ArrayList<Zombie> zombieList = new ArrayList<>();
    public static ArrayList<Grave> graveList = new ArrayList<>();
    public static ArrayList<Sun> sunList = new ArrayList<>();
    public static ArrayList<Pea> peaList = new ArrayList<>();

    public static HashMap<GameObjectType, PvZContainer> pvzContainers = new HashMap<>();
    static {
        // * PLANTS PLANTS PLANTS PLANTS PLANTS PLANTS PLANTS PLANTS PLANTS PLANTS PLANTS PLANTS
        pvzContainers.put(GameObjectType.Peashooter, new PvZContainer(
                getTexture(GameObjectType.Peashooter),
                null, 100, 200 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.Sunflower, new PvZContainer(
                getTexture(GameObjectType.Sunflower),
                null, 50, 125 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.Wallnut, new PvZContainer(
                getTexture(GameObjectType.Wallnut),
                null, 50, 200 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.PotatoMine, new PvZContainer(
                getTexture(GameObjectType.PotatoMine),
                null, 25, 1000 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.CherryBomb, new PvZContainer(
                getTexture(GameObjectType.CherryBomb),
                null, 150, 1000 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.Puffshroom, new PvZContainer(
                getTexture(GameObjectType.Puffshroom),
                null, 0, 400 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.SnowPeashooter, new PvZContainer(
                getTexture(GameObjectType.SnowPeashooter),
                null, 300, 500 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.TriplePeashooter, new PvZContainer(
                getTexture(GameObjectType.TriplePeashooter),
                null, 500, 700 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.TorchWood, new PvZContainer(
                getTexture(GameObjectType.TorchWood),
                null, 550, 600 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.CabbagePult, new PvZContainer(
                getTexture(GameObjectType.CabbagePult),
                null, 150, 500 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.Cactus, new PvZContainer(
                getTexture(GameObjectType.Cactus),
                null, 600, 1000 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.WatermelonPult, new PvZContainer(
                getTexture(GameObjectType.WatermelonPult),
                null, 700, 1000 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.IceMushroom, new PvZContainer(
                getTexture(GameObjectType.IceMushroom),
                null, 500, 1400 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.Jalapeno, new PvZContainer(
                getTexture(GameObjectType.Jalapeno),
                null, 200, 1000 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.Pumpkin, new PvZContainer(
                getTexture(GameObjectType.Pumpkin),
                null, 250, 700 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.TallWallnut, new PvZContainer(
                getTexture(GameObjectType.TallWallnut),
                null, 150, 1200 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.SpikeRock, new PvZContainer(
                getTexture(GameObjectType.SpikeRock),
                null, 550, 600 * SEED_RELOAD_MODIFIER));
        pvzContainers.put(GameObjectType.SpikeWeed, new PvZContainer(
                getTexture(GameObjectType.SpikeWeed),
                null, 150, 200 * SEED_RELOAD_MODIFIER));
        // ? ZOMBIES ZOMBIES ZOMBIES ZOMBIES ZOMBIES ZOMBIES ZOMBIES ZOMBIES ZOMBIES ZOMBIES
        pvzContainers.put(GameObjectType.ZBasic, new PvZContainer(
                getTexture(GameObjectType.ZBasic),
                AnimationManager.getTexture(AtlasName.ZOMBIE_BASIC_EAT),
                AnimationManager.getTexture(AtlasName.ZOMBIE_BASIC_WALK),
                new Point(62, 88), new Point(58, 94), 100));
        pvzContainers.put(GameObjectType.ZConehead, new PvZContainer(
                getTexture(GameObjectType.ZConehead),
                AnimationManager.getTexture(AtlasName.ZOMBIE_CONE_EAT),
                AnimationManager.getTexture(AtlasName.ZOMBIE_CONE_WALK),
                new Point(62, 108),new Point(58, 110), 200));
        pvzContainers.put(GameObjectType.ZBuckethead, new PvZContainer(
                getTexture(GameObjectType.ZBuckethead),
                AnimationManager.getTexture(AtlasName.ZOMBIE_BUCKET_EAT),
                AnimationManager.getTexture(AtlasName.ZOMBIE_BUCKET_WALK),
                new Point(64, 100), new Point(64, 102), 400));
        pvzContainers.put(GameObjectType.ZDoor, new PvZContainer(
                getTexture(GameObjectType.ZDoor),
                AnimationManager.getTexture(AtlasName.ZOMBIE_DOOR_EAT),
                AnimationManager.getTexture(AtlasName.ZOMBIE_DOOR_WALK),
                new Point(62, 94), new Point(68, 100), 550));
        pvzContainers.put(GameObjectType.ZBalloon, new PvZContainer(
                getTexture(GameObjectType.ZBalloon),
                AnimationManager.getTexture(AtlasName.ZOMBIE_BALLOON_FLY),
                AnimationManager.getTexture(AtlasName.ZOMBIE_BALLOON_FLY),
                new Point(70, 114), new Point(70, 114), 300));
        pvzContainers.put(GameObjectType.ZImp, new PvZContainer(
                getTexture(GameObjectType.ZImp), 50));
        pvzContainers.put(GameObjectType.ZZomboni, new PvZContainer(
                getTexture(GameObjectType.ZZomboni),
                AnimationManager.getTexture(AtlasName.ZOMBONI_RIDE),
                AnimationManager.getTexture(AtlasName.ZOMBONI_RIDE),
                new Point(120, 122), new Point(120, 122), 1000));
        pvzContainers.put(GameObjectType.ZJackbox, new PvZContainer(
                getTexture(GameObjectType.ZJackbox),
                AnimationManager.getTexture(AtlasName.ZOMBIE_JACKBOX_WALK),
                AnimationManager.getTexture(AtlasName.ZOMBIE_JACKBOX_WALK),
                new Point(74, 108), new Point(74, 108), 150));
        pvzContainers.put(GameObjectType.ZYeti, new PvZContainer(
                getTexture(GameObjectType.ZYeti), 2500));
        pvzContainers.put(GameObjectType.ZGrave, new PvZContainer(
                getTexture(GameObjectType.ZGrave), 500));
        pvzContainers.put(GameObjectType.ZFlag, new PvZContainer(
                getTexture(GameObjectType.ZFlag), 1000));
    }

    public static Rect[][] tiles = new Rect[12][6];
    public static Random randomizer = new Random();

    public static int zombieKilled = 0;
    public static int wavesCompleted = 0;

    public static int SUN_GAME_RELOAD = 250;
    public static boolean drawPlantChoose = true;


    // ! MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER
    public static boolean isMultiplayerOn = false;
    public static boolean isServer = true;

    public static Server server;
    public static Client client;
    public static boolean isPlantsGameMode = true;
    public static ArrayList<Plant> clientPlantQueue = new ArrayList<>();
    public static ArrayList<Zombie> clientZombieQueue = new ArrayList<>();
    public static ArrayList<Grave> clientGraveQueue = new ArrayList<>();

    public static ArrayList<Plant> serverPlantQueue = new ArrayList<>();
    public static ArrayList<Zombie> serverZombieQueue = new ArrayList<>();
    public static ArrayList<Pea> serverPeaQueue = new ArrayList<>();
    public static ArrayList<Grave> serverGraveQueue = new ArrayList<>();

    public static ArrayList<Plant> deadPlants = new ArrayList<>();
    public static ArrayList<Zombie> deadZombies = new ArrayList<>();
    public static ArrayList<Grave> deadGraves = new ArrayList<>();
    // ! MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER

    public static ChosenSeed selectedObject;

    public static void createGame() throws FileNotFoundException {
        loadConfigs();
        isGameRunning = true;
        // 16,14
        menuSeedSlots.put(GameObjectType.SeedsSunflower, new Rect(25, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsPeashooter, new Rect(105, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsWallnut, new Rect(185, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsPotatoMine, new Rect(265, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsSnowPeashooter, new Rect(345, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsCherryBomb, new Rect(425, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsPuffshroom, new Rect(505, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsCabbagePult, new Rect(585, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsWatermelonPult, new Rect(665, 150, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsTriplePeashooter, new Rect(25, 250, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsCactus, new Rect(105, 250, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsIceMushroom, new Rect(185, 250, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsJalapeno, new Rect(265, 250, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsPumpkin, new Rect(345, 250, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsSpikeWeed, new Rect(425, 250, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsSpikeRock, new Rect(506, 250, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsTallWallnut, new Rect(585, 250, 64, 86));
        menuSeedSlots.put(GameObjectType.SeedsTorchWood, new Rect(665, 250, 64, 86));
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 6; y++) {
                tiles[x][y] = new Rect(150 * x, 90 + 150 * y, 150, 150);
            }
        }
        renderThread.start();
        updateThread.start();
        animationThread.start();
    }

    private static void loadConfigs() throws FileNotFoundException {
        File configs = new File("configs.txt");
        BufferedReader reader = new BufferedReader(new FileReader(configs));
        for(String s : reader.lines().toList()) {
            if(s.startsWith("serverAddress")) GameSettings.serverAddress = s.substring(s.indexOf("=")+1);
            else if(s.startsWith("port")) GameSettings.port = Integer.parseInt(s.substring(s.indexOf("=")+1));
        }
    }

    public static void startGame() {
        if(!isPlantsGameMode) {
            drawPlantChoose = false;
            chosenSeeds.clear();
            // Fill seeds UI with zombies "seeds"
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsBasic, new Rect(93, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsConehead, new Rect(93 + 86, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsBuckethead, new Rect(93 + 86 * 2, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsDoor, new Rect(93 + 86 * 3, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsJackbox, new Rect(93 + 86 * 4, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsBalloon, new Rect(93 + 86 * 5, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsYeti, new Rect(93 + 86 * 6, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsImp, new Rect(93 + 86 * 7, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsZomboni, new Rect(93 + 86 * 8, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsGrave, new Rect(93 + 86 * 9, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(GameObjectType.ZSeedsFlag, new Rect(93 + 86 * 10, 5, 64, 86)));
        }
        if(isMultiplayerOn) {
            try {
                if (isServer) {
                    server = new Server();

                } else {
                    client = new Client();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(isPlantsGameMode) chosenSeeds.add(new ChosenSeed(GameObjectType.Shovel, new Rect(1050, 0, 100, 100)));
        Sound.playReadySetPlantSound();
        Sound.playBgMusic();
        if(!isMultiplayerOn) waveGenThread.start();
    }

    public static void stopGame() {
        isGameRunning = false;
        Sound.stopBgMusic();
    }

    public static void continueGame() {
        isGameRunning = true;
        Sound.playBgMusic();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            keys[e.getKeyCode()] = true;
        } catch (Exception ignored) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        keysOnHold[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickX = e.getX();
        clickY = e.getY();
        isMouseClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMouseClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public static void gameOver() {
        isGameRunning = false;
        if (!isGameOver) {
            isGameOver = true;
            renderThread.interrupt();
            updateThread.interrupt();
            if(isMultiplayerOn) {
                if(isServer) server.serverThread.interrupt();
                else client.clientThread.interrupt();
            }
            Sound.stopBgMusic();
            JFrame gameOverFrame = new JFrame();
            gameOverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            gameOverFrame.setSize(200, 200);
            JLabel msg = new JLabel("<html>ZombiesKilled: " + zombieKilled + "<br/> Wave: " + wavesCompleted + "<html/>");
            gameOverFrame.add(msg);
            gameOverFrame.setVisible(true);
        }
    }
}
