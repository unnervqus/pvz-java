package com.rxnqst.pvz.net;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.GameSettings;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.zombies.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static com.rxnqst.pvz.GameEngine.*;

public class Server {
    ServerSocket serverSocket;
    public Socket clientSocket;
    public ObjectInputStream input;
    public ObjectOutputStream output;
    public Thread serverThread;

    public Server() throws IOException {
        serverSocket = new ServerSocket(GameSettings.port);

        (serverThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    clientSocket = serverSocket.accept();
                    output = new ObjectOutputStream(clientSocket.getOutputStream());
                    input = new ObjectInputStream(clientSocket.getInputStream());
                    while (isGameRunning && clientSocket != null) {
                        sendPlants();
                        sendZombies();
                        sendPeas();
                        sendDeadPlants();
                        sendDeadZombies();
                        sendDeadGraves();
                        output.writeInt(brainsAmount);
                        output.flush();
                        if(isPlantsGameMode) {
                            readZombies();
                            readGraves();
                        }
                        else readPlants();
                        Thread.sleep(25);
                    }
                    clientSocket.close();
                    serverSocket.close();
                } catch (IOException | InterruptedException | ClassNotFoundException | NoSuchFieldException |
                         IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        })).start();
    }

    private void sendDeadGraves() throws IOException {
        output.writeInt(deadGraves.size());
        for (int z = 0; z < deadGraves.size(); z++) output.writeObject(deadGraves.get(z));
        output.flush();
        deadGraves.clear();
    }

    private void readGraves() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        for (int p = 0; p < queueSize; p++) {
            Grave grave = (Grave) input.readObject();
            // im so good at coding XD
            int r = randomizer.nextInt(0, 2);
            switch (r) {
                case 0 -> {
                    grave.image = ImageManager.getTexture(GameObjectType.ZGrave1);
                    grave.hitbox.width = 106;
                    grave.hitbox.height = 122;
                }
                case 1 -> {
                    grave.image = ImageManager.getTexture(GameObjectType.ZGrave2);
                    grave.hitbox.width = 92;
                    grave.hitbox.height = 106;
                    grave.hitbox.x += 10;
                }
            }
            graveList.add(grave);
        }
    }

    private void sendDeadZombies() throws IOException {
        output.writeInt(deadZombies.size());
        for (int z = 0; z < deadZombies.size(); z++) output.writeObject(deadZombies.get(z));
        output.flush();
        deadZombies.clear();
    }

    private void sendDeadPlants() throws IOException {
        output.writeInt(deadPlants.size());
        for (int p = 0; p < deadPlants.size(); p++) output.writeObject(deadPlants.get(p));
        output.flush();
        deadPlants.clear();
    }

    private void sendPeas() throws IOException {
        output.writeInt(serverPeaQueue.size());
        for (int p = 0; p < serverPeaQueue.size(); p++) output.writeObject(serverPeaQueue.get(p));
        output.flush();
        serverPeaQueue.clear();
    }

    private void sendZombies() throws IOException {
        output.writeInt(serverZombieQueue.size());
        for (int z = 0; z < serverZombieQueue.size(); z++) output.writeObject(serverZombieQueue.get(z));
        output.flush();
        serverZombieQueue.clear();
    }

    private void sendPlants() throws IOException {
        output.writeInt(serverPlantQueue.size());
        for (int p = 0; p < serverPlantQueue.size(); p++) output.writeObject(serverPlantQueue.get(p));
        output.flush();
        serverPlantQueue.clear();
    }

    private void readZombies() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        int zombieQueue = input.readInt();
        for (int p = 0; p < zombieQueue; p++) {
            Zombie zombie = (Zombie) input.readObject();
            Zombie realZombie = null;
            //TODO: make abstraction above this garbage of code (somehow)
            if(zombie instanceof BasicZombie) realZombie = new BasicZombie(zombie);
            if(zombie instanceof ZombieConehead) realZombie = new ZombieConehead(zombie);
            if(zombie instanceof ZombieBuckethead) realZombie = new ZombieBuckethead(zombie);
            if(zombie instanceof ZombieDoor) realZombie = new ZombieDoor(zombie);
            if(zombie instanceof ZombieImp) realZombie = new ZombieImp(zombie);
            if(zombie instanceof BalloonZombie) realZombie = new BalloonZombie(zombie);
            if(zombie instanceof Yeti) realZombie = new Yeti(zombie);
            if(zombie instanceof Zomboni) realZombie = new Zomboni(zombie);
            if(zombie instanceof ZombieJackbox) realZombie = new ZombieJackbox(zombie);
            zombieList.add(realZombie);
            brainsAmount -= pvzContainers.get(realZombie.type).COST;
            serverZombieQueue.add(zombie);
        }
    }

    private void readPlants() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        for (int p = 0; p < queueSize; p++) {
            plantList.add((Plant) input.readObject());
        }
    }
}
