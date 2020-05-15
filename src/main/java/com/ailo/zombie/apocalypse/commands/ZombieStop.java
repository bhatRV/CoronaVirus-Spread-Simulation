package com.ailo.zombie.apocalypse.commands;


import com.ailo.zombie.apocalypse.dto.Location;

import java.util.ArrayList;
import java.util.List;

public class ZombieStop implements Command {

    private String reason;

    public ZombieStop(String reason) {
        this.reason = reason;
    }

    @Override
    public List<Location> apply(Location currentLocation) {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Execute QUIT";
    }

    public String getReason() {
        return reason;
    }
}
