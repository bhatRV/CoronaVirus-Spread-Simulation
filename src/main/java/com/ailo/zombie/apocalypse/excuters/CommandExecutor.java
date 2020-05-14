package com.ailo.zombie.apocalypse.excuters;

import com.ailo.zombie.apocalypse.FinalStatus;
import com.ailo.zombie.apocalypse.StartInfection;
import com.ailo.zombie.apocalypse.commands.AdvanceCommand;
import com.ailo.zombie.apocalypse.commands.Command;
import com.ailo.zombie.apocalypse.commands.QuitCommand;
import com.ailo.zombie.apocalypse.entities.Location;
import com.ailo.zombie.apocalypse.entities.ZombieGrid;
import com.ailo.zombie.apocalypse.entities.Zombie;
import com.ailo.zombie.apocalypse.entities.enums.Type;
import com.ailo.zombie.apocalypse.exception.SimulationException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Applies the direction command on the Zombie, gets the list of the infections, validates and keeps track of points scored.
 */
public class CommandExecutor implements BiFunction<Zombie, Command, Zombie> {
    private final ZombieGrid[][] siteMap;

    public CommandExecutor(ZombieGrid[][] siteMap) {
        this.siteMap = siteMap;
    }

    @Override
    public Zombie apply(Zombie zombie, Command command) {

        zombie.getCommandList().add(command);

        List<String> lines = zombie.getLines();
        String currentZombieLocation = lines.get(1);
        System.out.println(currentZombieLocation + " :: zombies CurrentLocation : (" + zombie.getCurrentLocation().getX() + " , " + zombie.getCurrentLocation().getY() + ")");

        if (zombie.getCurrentLocation() != null && !(command instanceof QuitCommand)) {

            List<Location> path = command.apply(zombie.getCurrentLocation());

            if (!path.isEmpty()) {
                zombie.setCurrentLocation(path.get(path.size() - 1));
            }

            if (command instanceof AdvanceCommand && !path.isEmpty()) {
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
        zombiePosition = zombiePosition.concat("(" + zombie.getCurrentLocation().getX() + "," + zombie.getCurrentLocation().getY() + ")");
        FinalStatus.setZombiesPosition(zombiePosition);
    }

    private void process(List<Location> locations, Zombie zombie) {
        locations.forEach(p -> {
            try {
                ZombieGrid zombieGrid = siteMap[p.getX()][p.getY()];

                if (zombieGrid.getType() == Type.CREATURE) {
                    zombieGrid.setInfected(true);
                    System.out.println(zombieGrid.getType().name() + " converted to Zombie at (" + p.getX() + "," + p.getY() + ")");
                    ZombieGrid[][] newZombiePath = siteMap.clone();
                    newZombiePath[p.getX()][p.getY()] = new ZombieGrid(Type.ZOMBIE);
                    List<String> lines = generateInfectedZombieInput(zombie, p);
                    Runnable zombieRunnable = new StartInfection(newZombiePath, lines);

                    Thread zombieThread = new Thread(zombieRunnable);
                    zombieThread.setDaemon(true);
                    zombieThread.start();
                    zombieThread.join();

                    int totalPoints=FinalStatus.getZombiesCount();
                    FinalStatus.setZombiesCount(++totalPoints);
                }
            } catch (ArrayIndexOutOfBoundsException | InterruptedException e) {

                throw new SimulationException("\nSession ended because there is an attempt to navigate "
                        + "beyond the boundaries of the ZombieGrid; ");
            }
        });

    }

    private List<String> generateInfectedZombieInput(Zombie zombie, Location p) {
        List<String> lines = new ArrayList<String>(zombie.getLines());
        String creatureLocation = lines.get(2);
        String regex = p.getX() + "," + p.getY();
        String newCreatureLocation = creatureLocation.replaceAll(regex, "");
        lines.set(1, p.getX() + "," + p.getY());
        lines.set(2, newCreatureLocation);
        return lines;
    }
}
