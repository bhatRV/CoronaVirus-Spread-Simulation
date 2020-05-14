package com.ailo.zombie.apocalypse.excuters;

import com.ailo.zombie.apocalypse.entities.enums.Type;
import com.ailo.zombie.apocalypse.exception.SimulationException;
import com.ailo.zombie.apocalypse.entities.ZombieGrid;

import java.util.List;
import java.util.function.Function;


public class MatrixGenerator implements Function<List<String>, ZombieGrid[][]> {

    @Override
    public ZombieGrid[][] apply(List<String> lines) throws SimulationException {

        ZombieGrid[][] sqr = new ZombieGrid[Integer.parseInt(lines.get(0))][Integer.parseInt(lines.get(0))];

        for (int x = 0; x < sqr.length; x++) {
            for (int y = 0; y < sqr[0].length; y++) {
                sqr[x][y] = new ZombieGrid(Type.PLAIN);
            }
        }

        sqr[Integer.parseInt(lines.get(1).split(",")[0])][Integer.parseInt(lines.get(1).split(",")[1])] =
                new ZombieGrid(Type.ZOMBIE);
        String[] creatures = lines.get(2).split(" ");

        for (String creature : creatures) {
            if (!creature.isEmpty()) {
                sqr[Integer.parseInt(creature.split(",")[0])][Integer.parseInt(creature.split(",")[1])] =
                        new ZombieGrid(Type.CREATURE);
            }
        }
        System.out.println("Initial layout of Zombie and creature position: ");
        printGrid(sqr);

        return sqr;
    }

    public static void printGrid(ZombieGrid[][] metrix) {
        for (int x = 0; x < metrix.length; x++) {
            for (int y = 0; y < metrix[x].length; y++) {
                if (y == 0 || y % (metrix.length - 1) != 0) {
                    System.out.print(String.format("%10s", metrix[x][y]) + "(" + x + "," + y + ") | ");
                } else {
                    System.out.println(String.format("%10s", metrix[x][y]) + "(" + x + "," + y + ") | ");
                }
            }
        }
    }

}
