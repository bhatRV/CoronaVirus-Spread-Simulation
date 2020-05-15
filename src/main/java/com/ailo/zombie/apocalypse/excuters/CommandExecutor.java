package com.ailo.zombie.apocalypse.excuters;

import com.ailo.zombie.apocalypse.FinalStatus;
import com.ailo.zombie.apocalypse.StartInfection;
import com.ailo.zombie.apocalypse.commands.ZombieMove;
import com.ailo.zombie.apocalypse.commands.Command;
import com.ailo.zombie.apocalypse.commands.ZombieStop;
import com.ailo.zombie.apocalypse.dto.ActiveZombie;
import com.ailo.zombie.apocalypse.dto.Coordinates;
import com.ailo.zombie.apocalypse.dto.Creatures;
import com.ailo.zombie.apocalypse.dto.DataInput;
import com.ailo.zombie.apocalypse.dto.Location;
import com.ailo.zombie.apocalypse.dto.Zombie;
import com.ailo.zombie.apocalypse.dto.ZombieGrid;
import com.ailo.zombie.apocalypse.dto.enums.Type;
import com.ailo.zombie.apocalypse.exception.SimulationException;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Applies the direction command on the Zombie, gets the list of the infections, validates and keeps track of points scored.
 */
public class CommandExecutor implements BiFunction<Zombie, Command, Zombie> {

    private static final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);
    private final ZombieGrid[][] siteMap;

    public CommandExecutor(ZombieGrid[][] siteMap) {
        this.siteMap = siteMap;
    }

    @Override
    public Zombie apply(Zombie zombie, Command command) {

        zombie.getCommandList().add(command);

        DataInput dataInput = zombie.getDataInput();
        String currentZombieLocation = dataInput.getActiveZombiePosition().getPosition().getX() + "," + dataInput.getActiveZombiePosition().getPosition().getY();
        logger.debug("{} :: zombies CurrentLocation [ ({},{}) ]",
                currentZombieLocation,
                dataInput.getActiveZombiePosition().getPosition().getX(),
                dataInput.getActiveZombiePosition().getPosition().getY());

        if (zombie.getCurrentLocation() != null && !(command instanceof ZombieStop)) {

            List<Location> path = command.apply(zombie.getCurrentLocation());

            if (!path.isEmpty()) {
                zombie.setCurrentLocation(path.get(path.size() - 1));
            }

            if (command instanceof ZombieMove && !path.isEmpty()) {
                process(path, zombie);
            } else {
                setZombiePosition(zombie);
            }
        } else {
            setZombiePosition(zombie);
        }
        return zombie;
    }

    private void setZombiePosition(Zombie zombie) {
        String zombiePosition = FinalStatus.getZombiesPosition();
        zombiePosition = zombiePosition.concat("(" + zombie.getDataInput().getActiveZombiePosition().getPosition().getX() + "," + zombie.getDataInput().getActiveZombiePosition().getPosition().getY() + ")");
        FinalStatus.setZombiesPosition(zombiePosition);
    }

    private void process(List<Location> locations, Zombie zombie) {
        locations.forEach(p -> {
            try {
                ZombieGrid zombieGrid = siteMap[(int) p.getCoordinateX()][(int) p.getCoordinateY()];

                if (zombieGrid.getType() == Type.CREATURE) {
                    zombieGrid.setInfected(true);
                    logger.debug("{} :: converted to Zombie at [ ({},{}) ]",
                            zombieGrid.getType().name(),
                            p.getCoordinateX(),
                            p.getCoordinateY());

                    ZombieGrid[][] newZombiePath = siteMap.clone();
                    newZombiePath[p.getCoordinateX()][p.getCoordinateY()] = ZombieGrid.builder().type(Type.ZOMBIE).build();
                    DataInput newDataInput = generateInfectedZombieInput(zombie, p);
                    Runnable zombieRunnable = new StartInfection(newZombiePath, newDataInput);

                    Thread zombieThread = new Thread(zombieRunnable);
                    zombieThread.setDaemon(true);
                    zombieThread.start();
                    zombieThread.join();

                    int totalPoints = FinalStatus.getZombiesCount();
                    FinalStatus.setZombiesCount(++totalPoints);
                }
            } catch (ArrayIndexOutOfBoundsException | InterruptedException e) {

                throw new SimulationException("\nSession ended because there is an attempt to navigate "
                        + "beyond the boundaries of the ZombieGrid; ");
            }
        });

    }

    private DataInput generateInfectedZombieInput(Zombie zombie, Location p) {

        DataInput dataInput = DataInput.builder().build();
        Coordinates newZombieCoordinates = Coordinates.builder().x(p.getCoordinateX()).y(p.getCoordinateY()).build();
        Coordinates[] newCreatureLocation = getNewCreatureLocation(zombie.getDataInput().getCreaturesPosition().getPositions(), newZombieCoordinates);
        Creatures newCreature = Creatures.builder().positions(newCreatureLocation).build();
        ActiveZombie newActiveZombie = ActiveZombie.builder().position(newZombieCoordinates).build();

        dataInput.setGridDimension(zombie.getDataInput().getGridDimension());
        dataInput.setCommand(zombie.getDataInput().getCommand());
        dataInput.setCreaturesPosition(newCreature);
        dataInput.setActiveZombiePosition(newActiveZombie);

        return dataInput;
    }

    private Coordinates[] getNewCreatureLocation(Coordinates[] currentCreatureLocation, Coordinates infectedCreature) {
        for (int i = 0; i < currentCreatureLocation.length; i++) {
            if (currentCreatureLocation[i].equals(infectedCreature)) {
                currentCreatureLocation = ArrayUtils.remove(currentCreatureLocation, i);
                break;
            }
        }
        return currentCreatureLocation;
    }
}
