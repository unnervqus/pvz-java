package com.rxnqst.pvz.net;

import com.rxnqst.pvz.GameSettings;
import com.rxnqst.pvz.peas.Pea;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.zombies.Zombie;

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
                        brainsAmount = input.readInt();
                        if(isPlantsGameMode) sendPlants();
                        else sendZombies();
                        Thread.sleep(25);
                    }
                }
                socket.close();
            } catch (InterruptedException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        })).start();
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


    private void readPeas() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        for (int p = 0; p < queueSize; p++) {
            peaList.add((Pea) input.readObject());
        }
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
            zombieList.add((Zombie) input.readObject());
        }
    }

    private void readPlants() throws IOException, ClassNotFoundException {
        int queueSize = input.readInt();
        for (int p = 0; p < queueSize; p++) {
            plantList.add((Plant) input.readObject());
        }
    }
}
