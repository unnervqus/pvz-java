package com.rxnqst.pvz.net;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.GameSettings;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.peas.*;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.zombies.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static com.rxnqst.pvz.GameEngine.*;

public class Client {
    public Socket socket;
    public ObjectInputStream input;
    public ObjectOutputStream output;
    public Thread clientThread;
    public Client() throws IOException {
        socket = new Socket(GameSettings.serverAddress, GameSettings.port);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        (clientThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    while (isGameRunning) {
                        readPlants();
                        readZombies();
                        readPeas();
                        readDeadPlants();
                        readDeadZombies();
                        readDeadGraves();
                        brainsAmount = input.readInt();
                        if(isPlantsGameMode) sendPlants();
                        else {
                            sendZombies();
                            sendGraves();
                        }
                        Thread.sleep(25);
                    }
                }
                socket.close();
            } catch (InterruptedException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        })).start();
    }

    private void readDeadGraves() throws IOException, ClassNotFoundException {

        int queueSize = input.readInt();
        ArrayList<Grave> deadGraves = new ArrayList<>();
        for (int g = 0; g < queueSize; g++) {
            deadGraves.add((Grave) input.readObject());
        }
        graveList.forEach(grave -> {
            if(deadGraves.contains(grave)) grave.hp = 0;
        });
        deadGraves.clear();
    }

    private void sendGraves() throws IOException {
        //TODO: currently idk how i gonna work later with this project...
        output.writeInt(clientGraveQueue.size());
        for (int z = 0; z < clientGraveQueue.size(); z++) output.writeObject(clientGraveQueue.get(z));
        output.flush();
        clientGraveQueue.clear();
    }

    private void readDeadZombies() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        ArrayList<Zombie> deadZombies = new ArrayList<>();
        for (int p = 0; p < queueSize; p++) {
            deadZombies.add((Zombie) input.readObject());
        }
        zombieList.forEach(zombie -> {
            if(deadZombies.contains(zombie)) zombie.hp = 0;
        });
        deadZombies.clear();
    }

    private void readDeadPlants() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        ArrayList<Plant> deadPlants = new ArrayList<>();
        for (int p = 0; p < queueSize; p++) {
            deadPlants.add((Plant) input.readObject());
        }
        plantList.forEach(plant -> {
            if(deadPlants.contains(plant)) plant.hp = 0;
        });
        deadPlants.clear();
    }

    private void sendZombies() throws IOException {
        output.writeInt(clientZombieQueue.size());
        for (int z = 0; z < clientZombieQueue.size(); z++) output.writeObject(clientZombieQueue.get(z));
        output.flush();
        clientZombieQueue.clear();
    }

    private void sendPlants() throws IOException {
        output.writeInt(clientPlantQueue.size());
        for (int p = 0; p < clientPlantQueue.size(); p++) output.writeObject(clientPlantQueue.get(p));
        output.flush();
        clientPlantQueue.clear();
    }

    private void readZombies() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        for (int p = 0; p < queueSize; p++) {
            //TODO: make abstraction above this garbage of code (somehow)
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
        }
    }

    private void readPlants() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        for (int p = 0; p < queueSize; p++) {
            plantList.add((Plant) input.readObject());
        }
    }
    private void readPeas() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        for (int p = 0; p < queueSize; p++) {
            Pea pea = (Pea) input.readObject();
            if(pea.isFired) {
                if(!(pea instanceof Cabbage) && !(pea instanceof Watermelon) && !(pea instanceof Puff)) {
                    if(pea instanceof Needle) pea.image = ImageManager.getTexture(GameObjectType.FireNeedle);
                    else pea.image = ImageManager.getTexture(GameObjectType.FirePea);
                }
            } else {
                if(pea instanceof SnowPea) pea.image = ImageManager.getTexture(GameObjectType.SnowPea);
                if(pea instanceof Cabbage) pea.image = ImageManager.getTexture(GameObjectType.Cabbage);
                if(pea instanceof Watermelon) pea.image = ImageManager.getTexture(GameObjectType.Watermelon);
                if(pea instanceof Puff) pea.image = ImageManager.getTexture(GameObjectType.Puff);
                else pea.image = ImageManager.getTexture(GameObjectType.Pea);
            }
            peaList.add(pea);
        }
    }
}
