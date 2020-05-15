package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.dto.*;
import com.ailo.zombie.apocalypse.dto.ZombieGrid;
import com.ailo.zombie.apocalypse.exception.SimulationException;
import com.ailo.zombie.apocalypse.excuters.MatrixGenerator;
import com.ailo.zombie.apocalypse.utils.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class ZombieApocalypse {
    private static final Logger logger = LoggerFactory.getLogger(ZombieApocalypse.class);

    public static void main(String[] args) throws SimulationException, InterruptedException {

        DataInput dataInput = validateInputs(args);

        ZombieGrid[][] matrixZombieGrid = new MatrixGenerator().apply(dataInput);

        logger.debug("ZOMBIE INFECTION PATH");
        Runnable zombie = new StartInfection(matrixZombieGrid, dataInput);
        Thread zombieThread = new Thread(zombie);
        zombieThread.setDaemon(true);
        zombieThread.start();
        zombieThread.join();
        logger.info("CONSOLIDATED POINTS TALLY");
        logger.info("Zombies score : [ {} ]", FinalStatus.getZombiesCount());
        logger.info("Zombies positions [ {} ]", FinalStatus.getZombiesPosition());
    }

    private static DataInput validateInputs(String[] args) {
        DataInput inputFile = null;
        String fileName = "inputFile.json";

        if (args.length != 0) {
            fileName = args[0];
        }

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            //Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            JSONObject zombieLocationJSON = (JSONObject) obj.get("zombieLocation");
            JSONArray creatureLocationsJSON = (JSONArray) obj.get("creatureLocations");
            JSONObject gridDimensionJSON = (JSONObject) obj.get("gridDimension");
            String commandJSON = (String) obj.get("command");

            if (null == zombieLocationJSON) {
                throw new SimulationException("zombieLocation is required!!");
            }
            if (null == creatureLocationsJSON) {
                throw new SimulationException("creatureLocations is required!!");
            }
            if (null == gridDimensionJSON) {
                throw new SimulationException("gridDimension is required!!");
            }
            if (commandJSON == null || commandJSON.isEmpty()) {
                throw new SimulationException("command is required!!");
            } else {
                if(!commandJSON.matches(Constants.COMMAND_PATTERN_REGX)){
                    throw new SimulationException("Unknown command, command should match regex : "+Constants.COMMAND_PATTERN_REGX);
                }
            }

            if (Integer.parseInt((String) zombieLocationJSON.get("x")) >= Integer.parseInt((String) gridDimensionJSON.get("x")) ||
                    Integer.parseInt((String) zombieLocationJSON.get("y")) >= Integer.parseInt((String) gridDimensionJSON.get("y"))) {
                throw new SimulationException("coordinates beyond the dimension!!");
            }

            IntStream.range(0, creatureLocationsJSON.size())
                    .forEach(idx ->
                            {
                                JSONObject creature = (JSONObject) creatureLocationsJSON.get(idx);
                                if (Integer.parseInt((String) creature.get("x")) >= Integer.parseInt((String) gridDimensionJSON.get("x")) ||
                                        Integer.parseInt((String) creature.get("y")) >= Integer.parseInt((String) gridDimensionJSON.get("y"))) {
                                    throw new SimulationException("coordinates beyond the dimension!!");
                                }
                            }
                    );

            Coordinates gridDimensionCoordinates = Coordinates.builder()
                    .x(Integer.parseInt((String) gridDimensionJSON.get("x")))
                    .y(Integer.parseInt((String) gridDimensionJSON.get("y")))
                    .build();
            GridDimension gridDimension = GridDimension.builder().dimension(gridDimensionCoordinates).build();

            Coordinates zombieCoordinates = Coordinates.builder()
                    .x(Integer.parseInt((String) zombieLocationJSON.get("x")))
                    .y(Integer.parseInt((String) zombieLocationJSON.get("y")))
                    .build();

            Coordinates[] list = new Coordinates[creatureLocationsJSON.size()];

            IntStream.range(0, creatureLocationsJSON.size())
                    .forEach(idx ->
                            {
                                JSONObject creature = (JSONObject) creatureLocationsJSON.get(idx);
                                Coordinates creatureLocationCoordinates = Coordinates.builder()
                                        .x(Integer.parseInt((String) creature.get("x")))
                                        .y(Integer.parseInt((String) creature.get("y")))
                                        .build();
                                list[idx] = creatureLocationCoordinates;
                            }
                    );
            Creatures creatureLocations = Creatures.builder().positions(list).build();
            ActiveZombie activeZombie = ActiveZombie.builder().position(zombieCoordinates).build();

            inputFile = DataInput.builder()
                    .gridDimension(gridDimension)
                    .activeZombiePosition(activeZombie)
                    .creaturesPosition(creatureLocations)
                    .command(commandJSON)
                    .build();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new SimulationException("ParseException | IOException caught!!");
        }

        return inputFile;
    }
}
