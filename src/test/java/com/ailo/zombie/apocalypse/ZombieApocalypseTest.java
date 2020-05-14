package com.ailo.zombie.apocalypse;

import com.ailo.zombie.apocalypse.exception.SimulationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.*;

class ZombieApocalypseTest {
    private ZombieApocalypse zombieApocalypse ;

    @BeforeEach
    public void initEach(){
        zombieApocalypse=new ZombieApocalypse();
        FinalStatus.setZombiesCount(0);
        FinalStatus.setZombiesPosition("");
        System.out.println("enterend");
    }



    private void createTestData(String input){
        try {
            Files.write(Paths.get("input/rv.txt"), input.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testZombieApocalypse_success() {
         createTestData(new String ("4\n2,1\n0,1 1,2 3,1 1,1 2,2\nDLUURR"));
          try {
             zombieApocalypse.main(new String[] {"input/rv.txt"});
             assertEquals(5,  FinalStatus.getZombiesCount());
             assertEquals("(2,1)(3,1)(0,0)(1,0)(2,0)(3,0)", FinalStatus.getZombiesPosition());
          } catch (InterruptedException e) {
            e.printStackTrace();
        }
     }


    @Test
    public void testZombieApocalypse_error_wrongCommand() {
        createTestData(new String ("4\n2,1\n0,1 1,2 3,1 1,1 2,2\n.."));
        try {
            zombieApocalypse.main(new String[] {"input/rv.txt"});
            fail("Should throw SimulationException");
        }
        catch(SimulationException se)
        {
            assertTrue(se.getMessage().startsWith("Invalid Input provided"));
           // assertContains("Invalid Input provided", se.getMessage());
        }
        catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }


    @Test
    public void testZombieApocalypse_success_differentInput() {
        createTestData(new String ("2\n0,0\n0,1 1,0 1,1\nD"));
        try {
            zombieApocalypse.main(new String[] {"input/rv.txt"});
            assertEquals(1,  FinalStatus.getZombiesCount());
            assertEquals("(0,0)(1,0)",  FinalStatus.getZombiesPosition());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testZombieApocalypse_error_wrongDimension() {
        createTestData(new String ("\n2,1\n0,1 1,2 3,1 1,1 2,2\nD"));
        try {
            zombieApocalypse.main(new String[] {"input/rv.txt"});
            fail("Should throw SimulationException");
        }
        catch(SimulationException se)
        {
            assertTrue(se.getMessage().startsWith("Invalid Dimension"));
            // assertContains("Invalid Input provided", se.getMessage());
        }
        catch (InterruptedException e) {
            fail("Should throw SimulationException");
        }
    }

}