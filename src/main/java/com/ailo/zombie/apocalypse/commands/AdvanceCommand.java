package com.ailo.zombie.apocalypse.commands;


import com.ailo.zombie.apocalypse.FinalStatus;
import com.ailo.zombie.apocalypse.entities.Location;
import com.ailo.zombie.apocalypse.entities.enums.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Calculated the list of positions for an Advance command.
 * Assumption: START FROM NEXT SQUARE
 */
public class AdvanceCommand implements Command {

    private int step;

    public AdvanceCommand(int step) {
        this.step = step;
    }

    public List<Location> apply(Location currentLocation) {


        List<Location> path = new ArrayList<>();
        if (currentLocation != null) {
            Direction newDirection = null;
            String newRoute = null;
            if (currentLocation.getRoute() != null && !currentLocation.getRoute().isEmpty()) {
                newDirection = Direction.valueOf(String.valueOf((char) currentLocation.getRoute().charAt(0)));
                newRoute = currentLocation.getRoute().substring(1);
            }

            int gridLimit = currentLocation.getGridLimit();
            int row = currentLocation.getX();
            int col = currentLocation.getY();

            switch (currentLocation.getDirection()) {
                case R:
                    Direction finalNewDirection = newDirection;
                    String finalNewRoute = newRoute;
                    return IntStream.iterate(col - 1, i -> 1)
                            .limit(step)
                            .mapToObj(i -> {
                                if (i < 0) {
                                    i = gridLimit - 1;
                                }
                                return new Location(row, i, finalNewDirection, finalNewRoute, gridLimit);
                            })
                            .collect(Collectors.toList());
                case L:
                    Direction finalNewDirection1 = newDirection;
                    String finalNewRoute1 = newRoute;
                    return IntStream.iterate(col + 1, i -> 1)
                            .limit(step)
                            .mapToObj(i -> {
                                if (i >= gridLimit) {
                                    i = 0;
                                }
                                return new Location(row, i, finalNewDirection1, finalNewRoute1, gridLimit);
                            })
                            .collect(Collectors.toList());
                case D:
                    Direction finalNewDirection2 = newDirection;
                    String finalNewRoute2 = newRoute;
                    return IntStream.iterate(row - 1, i -> 1)
                            .limit(step)
                            .mapToObj(i ->
                            {
                                if (i < 0) {
                                    i = gridLimit - 1;
                                }
                                return new Location(i, col, finalNewDirection2, finalNewRoute2, gridLimit);
                            })
                            .collect(Collectors.toList());
                case U:
                    Direction finalNewDirection3 = newDirection;
                    String finalNewRoute3 = newRoute;
                    return IntStream.iterate(row + 1, i -> 1)
                            .limit(step)
                            .mapToObj(i -> {
                                if (i >= gridLimit) {
                                    i = 0;
                                }
                                return new Location(i, col, finalNewDirection3, finalNewRoute3, gridLimit);
                            })
                            .collect(Collectors.toList());
                default:
                    return path;
            }
        }

        return path;
    }

    public int getStep() {
        return step;
    }

    @Override
    public String toString() {
        return " FORWARD " + step;
    }
}
