package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.entities.ZombieGrid;
import com.ailo.zombie.apocalypse.exception.SimulationException;
import com.ailo.zombie.apocalypse.excuters.MatrixGenerator;
import com.ailo.zombie.apocalypse.input.ReadInputs;

import java.util.List;

public class ZombieApocalypse {

    public static void main(String[] args) throws SimulationException, InterruptedException {
        if(args.length == 0)
        {
            System.out.println("Provide a valid fileName with all the inputs: \ninput file format\n "
                + "<Line-1: Grid Dimension( eg, 4)> \n "
                + "<Line 2: Zombie Location (eg. x,y) >\n "
                + "<Line 3: creatures Locations (eg. x1,y1 x2,y2 x3,y3) >\n "
                + "<Line4: Directions for the Zombie eg.DLLURL (where D: Down, R: Right L:Left and U: UP)>    ");
            System.exit(0);
        }

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
