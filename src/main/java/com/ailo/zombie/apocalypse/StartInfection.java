package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.commands.ZombieStop;
import com.ailo.zombie.apocalypse.dto.DataInput;
import com.ailo.zombie.apocalypse.dto.Location;
import com.ailo.zombie.apocalypse.dto.Zombie;
import com.ailo.zombie.apocalypse.dto.ZombieGrid;
import com.ailo.zombie.apocalypse.dto.enums.Direction;
import com.ailo.zombie.apocalypse.excuters.CommandExecutor;
import com.ailo.zombie.apocalypse.input.CommandParser;
import com.ailo.zombie.apocalypse.input.CommandReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartInfection implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(StartInfection.class);

    private final DataInput dataInput;
    private final ZombieGrid[][] matrixZombieGrid;

    public StartInfection(ZombieGrid[][] matrixZombieGrid, DataInput dataInput) {
        this.matrixZombieGrid = matrixZombieGrid;
        this.dataInput = dataInput;
    }

    public void run() {

        Zombie zombie = Zombie.builder()
                .currentLocation(Location.builder()
                        .coordinateX(dataInput.getActiveZombiePosition().getPosition().getX())
                        .coordinateY(dataInput.getActiveZombiePosition().getPosition().getY())
                        .direction(Direction.valueOf(String.valueOf((char) dataInput.getCommand().charAt(0))))
                        .route(dataInput.getCommand().substring(1))
                        .gridDimension(dataInput.getGridDimension())
                        .build())
                .dataInput(dataInput)
                .build();

        logger.debug("Zombie starts infecting from [{},{}}]", dataInput.getActiveZombiePosition().getPosition().getX(), dataInput.getActiveZombiePosition().getPosition().getY());

        new CommandReader().enterCommand(dataInput.getCommand())
                .map(new CommandParser())
                .map(command -> new CommandExecutor(matrixZombieGrid).apply(zombie, command))
                .filter(zombiePath -> zombiePath.getLastCommand() instanceof ZombieStop)
                .findFirst()
                .ifPresent(zombiePath -> logger.debug("Zombie Completes infection from [{},{}}]", dataInput.getActiveZombiePosition().getPosition().getX(), dataInput.getActiveZombiePosition().getPosition().getY()));

    }
}