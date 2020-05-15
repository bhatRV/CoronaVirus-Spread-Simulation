package com.ailo.zombie.apocalypse.commands;


import com.ailo.zombie.apocalypse.dto.Location;
import com.ailo.zombie.apocalypse.dto.enums.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Rashmi.Vishnu
 * Calculates the list of positions for Every command. Advances in the Given Direction.
 * Assumption: START FROM NEXT SQUARE, variable step count holds the steps, currently defaults to 1, can be extended
 */
public class ZombieMove implements Command {

    private final int step;

    public ZombieMove(int step) {
        this.step = step;
    }

    public List<Location> apply(Location currentLocation) {

        List<Location> path = new ArrayList<>();
        if (currentLocation != null) {
            Direction newDirection = null;
            String newRoute = null;
            if (currentLocation.getRoute() != null && !currentLocation.getRoute().isEmpty()) {
                newDirection = Direction.valueOf(String.valueOf(currentLocation.getRoute().charAt(0)));
                newRoute = currentLocation.getRoute().substring(1);
            }

            int gridLimitX = currentLocation.getGridDimension().getDimension().getX();
            int gridLimitY = currentLocation.getGridDimension().getDimension().getY();
            int row = currentLocation.getCoordinateX();
            int col = currentLocation.getCoordinateY();

            switch (currentLocation.getDirection()) {
                case R:
                    String finalNewRoute = newRoute;
                    Direction finalNewDirection = newDirection;
                    return IntStream.iterate(col - 1, i -> 1)
                            .limit(step)
                            .mapToObj(i -> {
                                if (i < 0) {
                                    i = gridLimitY - 1;
                                }
                                return Location.builder()
                                        .coordinateX(row)
                                        .coordinateY(i)
                                        .direction(finalNewDirection)
                                        .route(finalNewRoute)
                                        .gridDimension(currentLocation.getGridDimension())
                                        .build();
                            })
                            .collect(Collectors.toList());
                case L:
                    Direction finalNewDirection1 = newDirection;
                    String finalNewRoute1 = newRoute;
                    return IntStream.iterate(col + 1, i -> 1)
                            .limit(step)
                            .mapToObj(i -> {
                                if (i >= gridLimitY) {
                                    i = 0;
                                }
                                return Location.builder()
                                        .coordinateX(row)
                                        .coordinateY(i)
                                        .direction(finalNewDirection1)
                                        .route(finalNewRoute1)
                                        .gridDimension(currentLocation.getGridDimension())
                                        .build();
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
                                    i = gridLimitX - 1;
                                }
                                return Location.builder()
                                        .coordinateX(i)
                                        .coordinateY(col)
                                        .direction(finalNewDirection2)
                                        .route(finalNewRoute2)
                                        .gridDimension(currentLocation.getGridDimension())
                                        .build();
                            })
                            .collect(Collectors.toList());
                case U:
                    Direction finalNewDirection3 = newDirection;
                    String finalNewRoute3 = newRoute;
                    return IntStream.iterate(row + 1, i -> 1)
                            .limit(step)
                            .mapToObj(i -> {
                                if (i >= gridLimitX) {
                                    i = 0;
                                }
                                return Location.builder()
                                        .coordinateX(i)
                                        .coordinateY(col)
                                        .direction(finalNewDirection3)
                                        .route(finalNewRoute3)
                                        .gridDimension(currentLocation.getGridDimension())
                                        .build();
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
