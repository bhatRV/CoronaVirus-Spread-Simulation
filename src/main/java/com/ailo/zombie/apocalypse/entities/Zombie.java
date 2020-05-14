package com.ailo.zombie.apocalypse.entities;


import com.ailo.zombie.apocalypse.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Zombie {
    private Location currentLocation;
    private final List<Command> commandList;
    private List<String> lines;
    private boolean placed;

    public Zombie(Location location) {
        this.currentLocation = location;
        this.commandList = new ArrayList<>();
        this.placed = true;
    }

    public Zombie(Location location, List<String> lines) {
        this.currentLocation = location;
        this.commandList = new ArrayList<>();
        this.placed = true;
        this.lines = lines;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public Command getLastCommand() {
        return commandList.get(commandList.size() - 1);
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
