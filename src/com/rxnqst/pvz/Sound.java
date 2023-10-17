package com.rxnqst.pvz;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sound {
    static String soundPath = "sounds/";
    static File sunCollectedSoundFile = new File(soundPath + "sun collected.wav");
    static File plantPlantedSoundFile = new File(soundPath + "planted.wav");
    static File explosionSoundFile = new File(soundPath + "explosion.wav");
    static File hugeWaveSoundFile = new File(soundPath + "Huge wave.wav");
    static File jackInTheBoxSoundFile = new File(soundPath + "jackinthebox.wav");
    static File sirenSoundFile = new File(soundPath + "siren.wav");
    static File bgMusicFile = new File(soundPath + "Ultimate-battle.wav");
    static File shovelSoundFile = new File(soundPath + "shovel.wav");
    static File readySetPlantSoundFile = new File(soundPath + "readysetplant.wav");
    static File rainSoundFile = new File(soundPath + "rain.wav");
    static File peaHitSoundFile = new File(soundPath + "pea hit.wav");
    static File seedSelectSoundFile = new File(soundPath + "seed select.wav");
    static File thunderSoundFile = new File(soundPath + "thunder.wav");
    static File zombieEatSoundFile = new File(soundPath + "zombie eat.wav");
    static File potatoMineBoomSoundFile = new File(soundPath + "potato mine boom.wav");
    static File cherryBombBoomSoundFile = new File(soundPath + "cherry bomb boom.wav");

    static Clip sunCollectedSound;
    static Clip plantPlantedSound;
    static Clip explosionSound;
    static Clip hugeWaveSound;
    static Clip jackInTheBoxSound;
    static Clip sirenSound;
    static Clip bgMusic;
    static Clip shovelSound;
    static Clip readySetPlantSound;
    static Clip rainSound;
    static Clip peaHitSound;
    static Clip seedSelectSound;
    static Clip thunderSound;
    static Clip zombieEatSound;
    static Clip potatoMineBoomSound;
    static Clip cherryBombBoomSound;

    static {
        try {
            sunCollectedSound = AudioSystem.getClip();
            plantPlantedSound = AudioSystem.getClip();
            explosionSound = AudioSystem.getClip();
            hugeWaveSound = AudioSystem.getClip();
            jackInTheBoxSound = AudioSystem.getClip();
            sirenSound = AudioSystem.getClip();
            bgMusic = AudioSystem.getClip();
            shovelSound = AudioSystem.getClip();
            readySetPlantSound = AudioSystem.getClip();
            rainSound  = AudioSystem.getClip();
            peaHitSound = AudioSystem.getClip();
            seedSelectSound = AudioSystem.getClip();
            thunderSound = AudioSystem.getClip();
            zombieEatSound = AudioSystem.getClip();
            potatoMineBoomSound = AudioSystem.getClip();
            cherryBombBoomSound = AudioSystem.getClip();


            sunCollectedSound.open(AudioSystem.getAudioInputStream(sunCollectedSoundFile));
            plantPlantedSound.open(AudioSystem.getAudioInputStream(plantPlantedSoundFile));
            explosionSound.open(AudioSystem.getAudioInputStream(explosionSoundFile));
            hugeWaveSound.open(AudioSystem.getAudioInputStream(hugeWaveSoundFile));
            jackInTheBoxSound.open(AudioSystem.getAudioInputStream(jackInTheBoxSoundFile));
            sirenSound.open(AudioSystem.getAudioInputStream(sirenSoundFile));
            bgMusic.open(AudioSystem.getAudioInputStream(bgMusicFile));
            shovelSound.open(AudioSystem.getAudioInputStream(shovelSoundFile));
            readySetPlantSound.open(AudioSystem.getAudioInputStream(readySetPlantSoundFile));
            rainSound.open(AudioSystem.getAudioInputStream(rainSoundFile));
            peaHitSound.open(AudioSystem.getAudioInputStream(peaHitSoundFile));
            seedSelectSound.open(AudioSystem.getAudioInputStream(seedSelectSoundFile));
            thunderSound.open(AudioSystem.getAudioInputStream(thunderSoundFile));
            zombieEatSound.open(AudioSystem.getAudioInputStream(zombieEatSoundFile));
            potatoMineBoomSound.open(AudioSystem.getAudioInputStream(potatoMineBoomSoundFile));
            cherryBombBoomSound.open(AudioSystem.getAudioInputStream(cherryBombBoomSoundFile));
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ignored) {}
    }

    public static void playSunCollectedSound() {
        sunCollectedSound.stop();
        sunCollectedSound.setFramePosition(0);
        sunCollectedSound.start();
    }
    public static void playPlantPlantedSound() {
        plantPlantedSound.stop();
        plantPlantedSound.setFramePosition(0);
        plantPlantedSound.start();
    }
    public static void playBgMusic() {
        bgMusic.setFramePosition(0);
        bgMusic.loop(999);
        bgMusic.start();
    }
    public static void stopBgMusic() {
        bgMusic.stop();
        bgMusic.setFramePosition(0);
    }
    public static void playHugeWaveSound() {
        hugeWaveSound.setFramePosition(0);
        hugeWaveSound.start();
    }
    public static void playSirenSound() {
        sirenSound.setFramePosition(0);
        sirenSound.start();
    }
    public static void playExplosionSound() {
        explosionSound.setFramePosition(0);
        explosionSound.start();
    }
    public static void playShovelSound() {
        shovelSound.setFramePosition(0);
        shovelSound.start();
    }
    public static void playReadySetPlantSound() {
        readySetPlantSound.setFramePosition(0);
        readySetPlantSound.start();
    }
    public static void playPeaHitSound() {
        peaHitSound.setFramePosition(0);
        peaHitSound.start();
    }
    public static void playSeedSelectSound() {
        seedSelectSound.setFramePosition(0);
        seedSelectSound.start();
    }
    public static void playRainSound() {
        rainSound.setFramePosition(0);
        rainSound.loop(999);
        rainSound.start();
    }
    public static void playThunderSound() {
        thunderSound.setFramePosition(0);
        thunderSound.start();
    }

    public static void playPotatoMineBoomSound() {
        potatoMineBoomSound.setFramePosition(0);
        potatoMineBoomSound.start();
    }
    public static void playCherryBombBoomSound() {
        cherryBombBoomSound.setFramePosition(0);
        cherryBombBoomSound.start();
    }
    public static void playJackintheboxSound() {
        jackInTheBoxSound.setFramePosition(0);
        jackInTheBoxSound.loop(999);
        jackInTheBoxSound.start();
    }
    public static void stopJackintheboxSound() {
        jackInTheBoxSound.stop();
        jackInTheBoxSound.setFramePosition(0);
    }
    public static void playZombieEatSound() {
        if(!zombieEatSound.isRunning()) {
            zombieEatSound.setFramePosition(0);
            zombieEatSound.start();
        }
    }
}