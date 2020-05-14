package com.ailo.zombie.apocalypse.entities;

import com.ailo.zombie.apocalypse.entities.enums.Type;

import java.util.Objects;

public class ZombieGrid {

    private final Type type;
    private boolean infected;

    public ZombieGrid(Type type) {
        this.type = type;
        this.infected = false;
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZombieGrid zombieGrid = (ZombieGrid) o;
        return infected == zombieGrid.infected &&
                type == zombieGrid.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, infected);
    }
}
