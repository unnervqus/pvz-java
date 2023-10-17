package com.rxnqst.pvz.net;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.GameSettings;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.zombies.Zombie;

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
                        output.writeInt(brainsAmount);
                        output.flush();
                        if(isPlantsGameMode) readZombies();
                        else readPlants();
                        Thread.sleep(100);
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
            zombieList.add(zombie);
            SeedSlot zombieType = SeedSlot.valueOf(zombie.type.toString());
            brainsAmount -= zombieType.objClass.getField("COST").getInt(null);
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
