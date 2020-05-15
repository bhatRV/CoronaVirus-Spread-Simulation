package com.ailo.zombie.apocalypse.commands;


import com.ailo.zombie.apocalypse.dto.Location;
import com.ailo.zombie.apocalypse.dto.enums.Direction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZombieStopTest {

    @Test
    public void quitCommandWorks() {

        ZombieStop zombieStop = new ZombieStop("test");

        Location currentLocation = Location.builder().coordinateX(0).coordinateY(0).direction(Direction.U).route("DLLU").build();

        List<Location> actual = zombieStop.apply(currentLocation);

        assertTrue(actual.isEmpty());
    }

}