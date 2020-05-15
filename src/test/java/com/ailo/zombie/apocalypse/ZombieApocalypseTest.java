package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.exception.SimulationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.*;

class ZombieApocalypseTest {

    @BeforeEach
    public void initEach() throws IOException {
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
    public void testZombieApocalypse_error_wrongDimension() {
        createTestData(new String("\n2,1\n0,1 1,2 3,1 1,1 2,2\nD"));
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

}