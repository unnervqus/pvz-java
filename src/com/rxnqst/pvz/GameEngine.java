package com.rxnqst.pvz;

import com.rxnqst.pvz.effects.Effect;
import com.rxnqst.pvz.net.Client;
import com.rxnqst.pvz.net.Server;
import com.rxnqst.pvz.peas.Pea;
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
import com.rxnqst.pvz.threads.Animator;
import com.rxnqst.pvz.threads.Render;
import com.rxnqst.pvz.threads.Updater;
import com.rxnqst.pvz.threads.WaveGenerator;
import com.rxnqst.pvz.utils.Rect;
import com.rxnqst.pvz.zombies.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import static com.rxnqst.pvz.GameSettings.BRAIN_BONUS_COOLDOWN;

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
    public static int sunAmount = 50;
    public static int brainsAmount = 50;
    public static int brainBonus = 250;
    public static int brainCooldown = 0;
    public static int brainBonusCooldown = BRAIN_BONUS_COOLDOWN;
    public static final ArrayList<ChosenSeed> chosenSeeds = new ArrayList<>();
    public static final HashMap<SeedSlot, Rect> seedSlots = new HashMap<>();
    public static ArrayList<Effect> effectList = new ArrayList<>();
    public static ArrayList<Plant> plantList = new ArrayList<>();
    public static ArrayList<Zombie> zombieList = new ArrayList<>();
    public static ArrayList<Grave> graveList = new ArrayList<>();
    public static ArrayList<Sun> sunList = new ArrayList<>();
    public static ArrayList<Pea> peaList = new ArrayList<>();
    public static Rect[][] tiles = new Rect[12][6];
    public static Random randomizer = new Random();

    public static int zombieKilled = 0;
    public static int wavesCompleted = 0;

    public static int SUN_GAME_RELOAD = 250;
    public static boolean drawPlantChoose = true;


    // ! MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER MULTIPLAYER
    public static boolean isMultiplayerOn = false;
    public static boolean isServer = true;

    public static String serverStatus = "Waiting for player";

    public static Server server;
    public static Client client;
    public static boolean isPlantsGameMode = true;
    //TODO: refactor classes Zombie, Plant, Grave, Pea.
    //TODO: refactor enum classes to one enum class
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
    public enum SeedSlot {
        // Plants
        SUNFLOWER(Sunflower.class), PEASHOOTER(Peashooter.class), WALLNUT(Wallnut.class),
        POTATO_MINE(PotatoMine.class), POTATO_MINE_UNREADY(PotatoMine.class), PUFFSHROOM(Puffshroom.class),
        SNOW_PEASHOOTER(SnowPeashooter.class), CHERRY_BOMB(CherryBomb.class), CABBAGE_PULT(CabbagePult.class),
        WATERMELON_PULT(WatermelonPult.class), TRIPLE_PEASHOOTER(TriplePeashooter.class), CACTUS(Cactus.class),
        ICE_MUSHROOM(IceMushroom.class), JALAPENO(Jalapeno.class), PUMPKIN(Pumpkin.class),
        SPIKE_ROCK(SpikeRock.class), SPIKE_WEED(SpikeWeed.class), TALL_WALLNUT(TallWallnut.class),
        TORCH_WOOD(TorchWood.class),
        // Zombies
        ZOMBIE_BASIC(BasicZombie.class), ZOMBIE_CONEHEAD(ZombieConehead.class), ZOMBIE_BUCKETHEAD(ZombieBuckethead.class),
        ZOMBIE_DOOR(ZombieDoor.class), ZOMBIE_BALLOON(BalloonZombie.class), ZOMBIE_JACKBOX(ZombieJackbox.class),
        YETI(Yeti.class), IMP(ZombieImp.class), ZOMBONI(Zomboni.class),
        GRAVE(Grave.class), FLAG(Object.class),
        // Shovel LOL
        SHOVEL(null);
        public final Class<?> objClass;

        SeedSlot(Class<?> plantClass) {
            this.objClass = plantClass;
        }
    }

    public static SeedSlot selectedObject;

    public static void createGame() throws FileNotFoundException {
        loadConfigs();
        isGameRunning = true;
        // 16,14
        seedSlots.put(SeedSlot.SUNFLOWER, new Rect(25, 150, 64, 86));
        seedSlots.put(SeedSlot.PEASHOOTER, new Rect(105, 150, 64, 86));
        seedSlots.put(SeedSlot.WALLNUT, new Rect(185, 150, 64, 86));
        seedSlots.put(SeedSlot.POTATO_MINE, new Rect(265, 150, 64, 86));
        seedSlots.put(SeedSlot.SNOW_PEASHOOTER, new Rect(345, 150, 64, 86));
        seedSlots.put(SeedSlot.CHERRY_BOMB, new Rect(425, 150, 64, 86));
        seedSlots.put(SeedSlot.PUFFSHROOM, new Rect(505, 150, 64, 86));
        seedSlots.put(SeedSlot.CABBAGE_PULT, new Rect(585, 150, 64, 86));
        seedSlots.put(SeedSlot.WATERMELON_PULT, new Rect(665, 150, 64, 86));
        seedSlots.put(SeedSlot.TRIPLE_PEASHOOTER, new Rect(25, 250, 64, 86));
        seedSlots.put(SeedSlot.CACTUS, new Rect(105, 250, 64, 86));
        seedSlots.put(SeedSlot.ICE_MUSHROOM, new Rect(185, 250, 64, 86));
        seedSlots.put(SeedSlot.JALAPENO, new Rect(265, 250, 64, 86));
        seedSlots.put(SeedSlot.PUMPKIN, new Rect(345, 250, 64, 86));
        seedSlots.put(SeedSlot.SPIKE_WEED, new Rect(425, 250, 64, 86));
        seedSlots.put(SeedSlot.SPIKE_ROCK, new Rect(506, 250, 64, 86));
        seedSlots.put(SeedSlot.TALL_WALLNUT, new Rect(585, 250, 64, 86));
        seedSlots.put(SeedSlot.TORCH_WOOD, new Rect(665, 250, 64, 86));
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 6; y++) {
                tiles[x][y] = new Rect(150 * x, 90 + 150 * y, 150, 150);
            }
        }
        brainsAmount = 10000;
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
            chosenSeeds.add(new ChosenSeed(SeedSlot.ZOMBIE_BASIC, new Rect(93, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.ZOMBIE_CONEHEAD, new Rect(93 + 86, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.ZOMBIE_BUCKETHEAD, new Rect(93 + 86 * 2, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.ZOMBIE_DOOR, new Rect(93 + 86 * 3, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.ZOMBIE_JACKBOX, new Rect(93 + 86 * 4, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.ZOMBIE_BALLOON, new Rect(93 + 86 * 5, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.YETI, new Rect(93 + 86 * 6, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.IMP, new Rect(93 + 86 * 7, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.ZOMBONI, new Rect(93 + 86 * 8, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.GRAVE, new Rect(93 + 86 * 9, 5, 64, 86)));
            chosenSeeds.add(new ChosenSeed(SeedSlot.FLAG, new Rect(93 + 86 * 10, 5, 64, 86)));
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
        chosenSeeds.add(new ChosenSeed(SeedSlot.SHOVEL, new Rect(1050, 0, 100, 100)));
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
