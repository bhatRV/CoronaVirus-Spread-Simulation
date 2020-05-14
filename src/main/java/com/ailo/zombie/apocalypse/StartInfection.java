package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.commands.QuitCommand;
import com.ailo.zombie.apocalypse.entities.Location;
import com.ailo.zombie.apocalypse.entities.ZombieGrid;
import com.ailo.zombie.apocalypse.entities.Zombie;
import com.ailo.zombie.apocalypse.entities.enums.Direction;
import com.ailo.zombie.apocalypse.exception.SimulationException;
import com.ailo.zombie.apocalypse.excuters.CommandExecutor;
import com.ailo.zombie.apocalypse.input.CommandParser;
import com.ailo.zombie.apocalypse.input.CommandReader;

import java.util.List;

public class StartInfection implements Runnable {
    private final List<String> lines;
    private final ZombieGrid[][] matrixZombieGrid;

    public StartInfection(ZombieGrid[][] matrixZombieGrid, List<String> lines) {
        this.matrixZombieGrid = matrixZombieGrid;
        this.lines = lines;
    }

    public void run() {

            Zombie zombie = new Zombie(new Location(Integer.parseInt(lines.get(1).split(",")[0]),
                    Integer.parseInt(lines.get(1).split(",")[1]),
                    Direction.valueOf(String.valueOf((char) lines.get(3).charAt(0))), lines.get(3).substring(1), Integer.parseInt(lines.get(0))), lines);


            System.out.println(String.format("\nZombie starts infecting from (%s,%s)", Integer.parseInt(lines.get(1).split(",")[0]), Integer.parseInt(lines.get(1).split(",")[1])));
            new CommandReader().enterCommand(lines.get(3))
                    .map(new CommandParser())
                    .map(command -> new CommandExecutor(matrixZombieGrid).apply(zombie, command))
                    .filter(zombiePath -> zombiePath.getLastCommand() instanceof QuitCommand)
                    .findFirst()
                    .ifPresent(zombiePath -> System.out.println(String.format("Zombie Completes infection from (%s,%s)\n", Integer.parseInt(lines.get(1).split(",")[0]), Integer.parseInt(lines.get(1).split(",")[1]))));

    }
}