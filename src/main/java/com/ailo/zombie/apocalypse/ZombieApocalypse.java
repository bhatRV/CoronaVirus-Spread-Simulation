package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.entities.ZombieGrid;
import com.ailo.zombie.apocalypse.exception.SimulationException;
import com.ailo.zombie.apocalypse.excuters.MatrixGenerator;
import com.ailo.zombie.apocalypse.input.ReadInputs;
import com.ailo.zombie.apocalypse.utils.Constants;

import java.util.List;

public class ZombieApocalypse {

    public static void main(String[] args) throws SimulationException, InterruptedException {

        List<String> lines = validateInputs(args);
        ZombieGrid[][] matrixZombieGrid = new MatrixGenerator().apply(lines);
        System.out.println("-----------ZOMBIE INFECTION PATH--------------");

        Runnable zombie = new StartInfection(matrixZombieGrid, lines);
        Thread zombieThread = new Thread(zombie);
        zombieThread.setDaemon(true);
        zombieThread.start();
        zombieThread.join();
        System.out.println("-------CONSOLIDATED POINTS TALLY--------------");
        System.out.println("zombiesCount    : "+FinalStatus.getZombiesCount());
        System.out.println("zombiesPosition : "+FinalStatus.getZombiesPosition());
        System.out.println("---------------------------------------------");
    }

    private static List<String> validateInputs(String[] args) {
        if(args.length == 0)
        {
            System.out.println("Provide a valid fileName with all the inputs: \ninput file format\n "
                    + "<Line-1: Grid Dimension( eg, 4)> \n "
                    + "<Line 2: Zombie Location (eg. x,y) >\n "
                    + "<Line 3: creatures Locations (eg. x1,y1 x2,y2 x3,y3) >\n "
                    + "<Line4: Directions for the Zombie eg.DLLURL (where D: Down, R: Right L:Left and U: UP)>    ");
            throw new SimulationException("Provide complete fileName as input. Filename should be in right format.");
        }
        List<String> lines = new ReadInputs().apply(args[0]);
        if(lines.get(0).isEmpty() || !lines.get(0).matches(Constants.ARRAY_DIMENSION)) {
            throw new SimulationException("Invalid Dimension : "+lines.get(0));
        }
        if(!lines.get(3).matches(Constants.COMMAND_PATTERN_REGX)) {
             throw new SimulationException("Invalid Input provided :"+lines.get(3));
        }

        return lines;

    }


}
