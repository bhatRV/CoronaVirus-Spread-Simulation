package com.ailo.zombie.apocalypse.entities;


import com.ailo.zombie.apocalypse.entities.enums.Direction;

import java.util.Objects;

public class Location {

    private int cordinateX;
    private int cordinateY;
    private Direction direction;
    private String route;
    private int gridLimit;

    public Location(int cordinateX, int cordinateY, Direction direction, String route) {
        this.cordinateX = cordinateX;
        this.cordinateY = cordinateY;
        this.direction = direction;
        this.route = route;
    }

    public Location(int cordinateX, int cordinateY, Direction direction, String route, int gridLimit) {
        this.cordinateX = cordinateX;
        this.cordinateY = cordinateY;
        this.direction = direction;
        this.route = route;
        this.gridLimit = gridLimit;
    }


    public int getX() {
        return cordinateX;
    }
    public int getGridLimit() {
        return gridLimit;
    }
    public void setGridLimit(int gridLimit) {
        this.gridLimit = gridLimit;
    }

    public int getY() {
        return cordinateY;
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
        return cordinateX == location.cordinateX &&
                cordinateY == location.cordinateY &&
                direction == location.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cordinateX, cordinateY, direction);
    }

    @Override
    public String toString() {
        return "Current Location{" +
                "x=" + cordinateX +
                ", y=" + cordinateY +
                ", direction=" + direction +
                ", route=" + route +
                '}';
    }
}
