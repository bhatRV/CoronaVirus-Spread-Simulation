package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.entities.ZombieGrid;
import com.ailo.zombie.apocalypse.exception.SimulationException;
import com.ailo.zombie.apocalypse.excuters.MatrixGenerator;
import com.ailo.zombie.apocalypse.input.ReadInputs;

import java.util.List;

public class ZombieApocalypse {

    public static void main(String[] args) throws SimulationException, InterruptedException {

        List<String> lines = new ReadInputs().apply(args[0]);
        ZombieGrid[][] matrixZombieGrid = new MatrixGenerator().apply(lines);
        Runnable zombie = new StartInfection(matrixZombieGrid, lines);
        Thread zombieThread = new Thread(zombie);
        zombieThread.setDaemon(true);
        zombieThread.start();
        zombieThread.join();
        System.out.println("---------------------------------------------");
        System.out.println("zombiesCount    : "+FinalStatus.getZombiesCount());
        System.out.println("zombiesPosition : "+FinalStatus.getZombiesPosition());
        System.out.println("---------------------------------------------");
    }
}
