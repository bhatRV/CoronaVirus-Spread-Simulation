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
 * Applied commands on the Bulldozer, gets the list of next positions, validates and keeps count of cost-items.
 */
public class CommandExecutor implements BiFunction<Zombie, Command, Zombie> {

    private static int totalInfected = 0;
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

            if (!path.isEmpty()) { //to check paint-scratch scenario
                zombie.setCurrentLocation(path.get(path.size() - 1));
            }

            if (command instanceof AdvanceCommand && !path.isEmpty() && zombie.getCurrentLocation().getDirection() != null) {
                zombie.setPlaced(true);
                try {
                    process(path, zombie);
                } catch (SimulationException e) {
                    zombie.getCommandList().add(new QuitCommand(e.getMessage()));
                }
            } else {
                String zombiePosition = FinalStatus.getZombiesPosition();
                zombiePosition = zombiePosition.concat("(" + zombie.getCurrentLocation().getX() + "," + zombie.getCurrentLocation().getY() + ")");
                FinalStatus.setZombiesPosition(zombiePosition);
            }
        }
        return zombie;
    }

    private void process(List<Location> locations, Zombie zombie) {
        locations.forEach(p -> {
            try {
                ZombieGrid zombieGrid = siteMap[p.getX()][p.getY()];

                if (zombieGrid.getType() == Type.CREATURE) {
                    zombieGrid.setInfected(true);
                    totalInfected++;
                    System.out.println(zombieGrid.getType().name() + " converted to Zombie at (" + p.getX() + "," + p.getY() + ")");
                    ZombieGrid[][] newZombiePath = siteMap.clone();
                    newZombiePath[p.getX()][p.getY()] = new ZombieGrid(Type.ZOMBIE);
                    List<String> lines = new ArrayList<String>(zombie.getLines());
                    String creatureLocation = lines.get(2);
                    String regex = p.getX() + "," + p.getY();
                    String newCreatureLocation = creatureLocation.replaceAll(regex, "");
                    lines.set(1, p.getX() + "," + p.getY());
                    lines.set(2, newCreatureLocation);
                    Runnable zombieRunnable = new StartInfection(newZombiePath, lines);

                    Thread zombieThread = new Thread(zombieRunnable);
                    zombieThread.setDaemon(true);
                    zombieThread.start();
                    zombieThread.join();

                    FinalStatus.setZombiesCount(totalInfected);
                }
            } catch (ArrayIndexOutOfBoundsException | InterruptedException e) {

                throw new SimulationException("\nSession ended because there is an attempt to navigate "
                        + "beyond the boundaries of the site; ");
            }
        });

    }
}
