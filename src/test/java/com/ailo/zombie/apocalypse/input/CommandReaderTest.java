package com.ailo.zombie.apocalypse.input;


import com.ailo.zombie.apocalypse.exception.SimulationException;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CommandReaderTest {

    private CommandReader commandReader = new CommandReader();

    @Test
    public void testCommandReader_success_validCommand() {
        Stream<String> stringStream = commandReader.enterCommand("DLLLD");
        assertEquals("DLLLDQ", stringStream.collect(Collectors.joining()));
    }

    @Test
    public void testCommandReader_success_singleCommand() {
        Stream<String> stringStream = commandReader.enterCommand("D");
        assertEquals("DQ", stringStream.collect(Collectors.joining()));
    }

    @Test
    public void testCommandReader_success_spaceAsCommand() {
        String command="";
        Stream<String> stringStream = commandReader.enterCommand(command);
        assertEquals("Q", stringStream.collect(Collectors.joining()));
    }
}