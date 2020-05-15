package com.ailo.zombie.apocalypse.excuters;

import com.ailo.zombie.apocalypse.dto.Coordinates;
import com.ailo.zombie.apocalypse.dto.DataInput;
import com.ailo.zombie.apocalypse.dto.ZombieGrid;
import com.ailo.zombie.apocalypse.dto.enums.Type;
import com.ailo.zombie.apocalypse.exception.SimulationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;


public class MatrixGenerator implements Function<DataInput, ZombieGrid[][]> {
    private static final Logger logger = LoggerFactory.getLogger(MatrixGenerator.class);

    @Override
    public ZombieGrid[][] apply(DataInput dataInput) throws SimulationException {

        ZombieGrid[][] sqr = new ZombieGrid[dataInput.getGridDimension().getDimension().getX()][dataInput.getGridDimension().getDimension().getY()];

        for (int x = 0; x < sqr.length; x++) {
            for (int y = 0; y < sqr[x].length; y++) {
                sqr[x][y] = ZombieGrid.builder().type(Type.NONE).build();
            }
        }

        sqr[dataInput.getActiveZombiePosition().getPosition().getX()][dataInput.getActiveZombiePosition().getPosition().getY()] =
                ZombieGrid.builder().type(Type.ZOMBIE).build();

        for (Coordinates creature : dataInput.getCreaturesPosition().getPositions()) {
            sqr[creature.getX()][creature.getY()] =
                    ZombieGrid.builder().type(Type.CREATURE).build();
        }
        if (logger.isDebugEnabled()) {
            printGrid(sqr);
        }
        return sqr;
    }

    private static void printGrid(ZombieGrid[][] matrix) {
        logger.debug("Initial layout of Zombie and creature position ");
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                if (y == 0 || y % (matrix.length - 1) != 0) {
                    System.out.print(String.format("%10s", matrix[x][y].getType()) + "(" + x + "," + y + ") | ");
                } else {
                    System.out.println(String.format("%10s", matrix[x][y].getType()) + "(" + x + "," + y + ") | ");
                }
            }
        }
    }
}
