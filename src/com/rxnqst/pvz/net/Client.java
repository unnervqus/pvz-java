package com.rxnqst.pvz.net;

import com.rxnqst.pvz.GameSettings;
import com.rxnqst.pvz.peas.Pea;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.zombies.Zombie;

import java.io.*;
import java.net.Socket;

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
                        brainsAmount = input.readInt();
                        if(isPlantsGameMode) sendPlants();
                        else sendZombies();
                        Thread.sleep(100);
                    }
                }
                socket.close();
            } catch (InterruptedException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        })).start();
    }

    private void readPeas() throws IOException, ClassNotFoundException {
        int peaQueue = input.readInt();
        for (int p = 0; p < peaQueue; p++) {
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
        int zombieQueue = input.readInt();
        for (int p = 0; p < zombieQueue; p++) {
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
