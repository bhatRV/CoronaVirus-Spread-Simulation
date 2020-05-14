package com.ailo.zombie.apocalypse.commands;


import com.ailo.zombie.apocalypse.entities.Location;

import java.util.List;

/**
 * Commands interface for all the commands to be run.
 */
public interface Command {

    List<Location> apply(Location currentLocation);
}
