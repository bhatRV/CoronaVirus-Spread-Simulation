package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.exception.SimulationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ZombieApocalypseTest {

    @BeforeEach
    public void initEach() {
        FinalStatus.setZombiesCount(0);
        FinalStatus.setZombiesPosition("");
    }

    private void createTestData(String input) {
        try {
            Files.write(Paths.get("testInput.json"), input.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testZombieApocalypse_success() {
        String inputData = "{\"gridDimension\": {\"x\": \"4\",\"y\": \"4\"}," +
                "\"zombieLocation\":{\"x\": \"2\",\"y\": \"1\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"2\"},{\"x\": \"3\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"1\"},{\"x\": \"2\",\"y\": \"2\"}]," +
                "\"command\":\"DLUURR\"}";
        createTestData(inputData);
        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            assertEquals(5, FinalStatus.getZombiesCount());
            assertEquals("(1,2)(2,2)(3,1)(0,1)(1,1)(2,1)", FinalStatus.getZombiesPosition());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testZombieApocalypse_error_wrongCommand() {
        String inputData = "{\"gridDimension\": {\"x\": \"4\",\"y\": \"4\"}," +
                "\"zombieLocation\":{\"x\": \"2\",\"y\": \"1\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"2\"},{\"x\": \"3\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"1\"},{\"x\": \"2\",\"y\": \"2\"}]," +
                "\"command\":\"\"}";
        createTestData(inputData);
        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            fail("Should throw SimulationException");
        } catch (SimulationException se) {
            assertTrue(se.getMessage().startsWith("command is required"));
        } catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }


    @Test
    public void testZombieApocalypse_success_differentInput() {
        String inputData = "{\"gridDimension\": {\"x\": \"2\",\"y\": \"2\"}," +
                "\"zombieLocation\":{\"x\": \"0\",\"y\": \"0\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"0\"},{\"x\": \"1\",\"y\": \"1\"}]," +
                "\"command\":\"D\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            assertEquals(1, FinalStatus.getZombiesCount());
            assertEquals("(1,0)(0,0)", FinalStatus.getZombiesPosition());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testZombieApocalypse_success_Edge1() {
        String inputData = "{\"gridDimension\": {\"x\": \"2\",\"y\": \"2\"}," +
                "\"zombieLocation\":{\"x\": \"0\",\"y\": \"0\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"0\"},{\"x\": \"1\",\"y\": \"1\"}]," +
                "\"command\":\"DL\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            assertEquals(3, FinalStatus.getZombiesCount());
            assertEquals("(1,1)(0,1)(1,0)(0,0)", FinalStatus.getZombiesPosition());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testZombieApocalypse_success_Edge2() {
        String inputData = "{\"gridDimension\": {\"x\": \"2\",\"y\": \"2\"}," +
                "\"zombieLocation\":{\"x\": \"0\",\"y\": \"0\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"0\"},{\"x\": \"1\",\"y\": \"1\"}]," +
                "\"command\":\"DR\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            assertEquals(3, FinalStatus.getZombiesCount());
            assertEquals("(1,1)(0,1)(1,0)(0,0)", FinalStatus.getZombiesPosition());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testZombieApocalypse_error_wrongDimension() {
        String inputData = "{\"gridDimension\": {\"x\": \"2\",\"y\": \"2\"}," +
                "\"zombieLocation\":{\"x\": \"0\",\"y\": \"0\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"2\"},{\"x\": \"3\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"1\"},{\"x\": \"2\",\"y\": \"2\"}]," +
                "\"command\":\"D\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            fail("Should throw SimulationException");
        } catch (SimulationException se) {
            assertTrue(se.getMessage().startsWith("coordinates beyond the dimension"));
        } catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }

    @Test
    public void testZombieApocalypse_validation_missingDimension() {
        String inputData = "{\"zombieLocation\":{\"x\": \"0\",\"y\": \"0\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"2\"},{\"x\": \"3\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"1\"},{\"x\": \"2\",\"y\": \"2\"}]," +
                "\"command\":\"D\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            fail("Should throw SimulationException");
        } catch (SimulationException se) {
            assertTrue(se.getMessage().startsWith("gridDimension is required"));
        } catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }
    @Test
    public void testZombieApocalypse_validation_missingZombieLocation() {
        String inputData = "{\"gridDimension\": {\"x\": \"2\",\"y\": \"2\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"0\"},{\"x\": \"1\",\"y\": \"1\"}]," +
                "\"command\":\"DR\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            fail("Should throw SimulationException");
        } catch (SimulationException se) {
            assertTrue(se.getMessage().startsWith("zombieLocation is required"));
        } catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }

    @Test
    public void testZombieApocalypse_validation_missingCreatureLocations() {
        String inputData = "{\"gridDimension\": {\"x\": \"2\",\"y\": \"2\"}," +
                "\"zombieLocation\":{\"x\": \"0\",\"y\": \"0\"}," +
                "\"command\":\"DR\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            fail("Should throw SimulationException");
        } catch (SimulationException se) {
            assertTrue(se.getMessage().startsWith("creatureLocations is required"));
        } catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }
    @Test
    public void testZombieApocalypse_validation_coordinateBeyondDimension() {
        String inputData = "{\"gridDimension\": {\"x\": \"2\",\"y\": \"2\"}," +
                "\"zombieLocation\":{\"x\": \"3\",\"y\": \"1\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"3\"},{\"x\": \"1\",\"y\": \"0\"},{\"x\": \"1\",\"y\": \"1\"}]," +
                "\"command\":\"DR\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            fail("Should throw SimulationException");
        } catch (SimulationException se) {
            assertTrue(se.getMessage().startsWith("coordinates beyond the dimension"));
        } catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }

    @Test
    public void testZombieApocalypse_validation_invalidCommand() {
        String inputData = "{\"gridDimension\": {\"x\": \"2\",\"y\": \"2\"}," +
                "\"zombieLocation\":{\"x\": \"1\",\"y\": \"1\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"0\"},{\"x\": \"1\",\"y\": \"1\"}]," +
                "\"command\":\"AS\"}";
        createTestData(inputData);

        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            fail("Should throw SimulationException");
        } catch (SimulationException se) {
            assertTrue(se.getMessage().startsWith("Unknown command, command should match regex"));
        } catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }

  @Test
    public void testZombieApocalypse_validation_FileNotFound() {
         try {
            ZombieApocalypse.main(new String[]{"xy.json"});
            fail("Should throw SimulationException");
        } catch (SimulationException se) {
            assertTrue(se.getMessage().startsWith("ParseException | IOException caught!!"));
        } catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }


    @Test
    public void testZombieApocalypse_success_withNonSquareGrid() {
        String inputData = "{\"gridDimension\": {\"x\": \"8\",\"y\": \"4\"}," +
                "\"zombieLocation\":{\"x\": \"2\",\"y\": \"1\"}," +
                "\"creatureLocations\":[{\"x\": \"0\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"2\"},{\"x\": \"3\",\"y\": \"1\"},{\"x\": \"1\",\"y\": \"1\"},{\"x\": \"2\",\"y\": \"2\"}]," +
                "\"command\":\"DLUURR\"}";
        createTestData(inputData);
        try {
            ZombieApocalypse.main(new String[]{"testInput.json"});
            assertEquals(5, FinalStatus.getZombiesCount());
            assertEquals("(3,1)(2,2)(1,2)(0,1)(1,1)(2,1)", FinalStatus.getZombiesPosition());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}