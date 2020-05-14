package com.ailo.zombie.apocalypse.entities;


import com.ailo.zombie.apocalypse.entities.enums.Direction;

import java.util.Objects;

public class Location {

    private int x;
    private int y;
    private Direction direction;
    private String route;
    private int gridLimit;

    public Location(int x, int y, Direction direction, String route) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.route = route;
    }

    public Location(int x, int y, Direction direction, String route, int gridLimit) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.route = route;
        this.gridLimit = gridLimit;
    }


    public int getX() {
        return x;
    }
    public int getGridLimit() {
        return gridLimit;
    }
    public void setGridLimit(int gridLimit) {
        this.gridLimit = gridLimit;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y &&
                direction == location.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    @Override
    public String toString() {
        return "Current Location{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", route=" + route +
                '}';
    }
}
