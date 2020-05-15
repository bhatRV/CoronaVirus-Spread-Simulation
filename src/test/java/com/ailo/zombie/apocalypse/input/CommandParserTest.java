package com.ailo.zombie.apocalypse.input;

import com.ailo.zombie.apocalypse.commands.Command;
import com.ailo.zombie.apocalypse.commands.ZombieMove;
import com.ailo.zombie.apocalypse.commands.ZombieStop;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandParserTest {

    private CommandParser commandParser = new CommandParser();


    @Test
    public void commandParser_success_MOVECommand() {
        Command command = commandParser.apply("DLLLD");
        assertNotNull( command);
        assert(command instanceof ZombieMove);
    }

    @Test
    public void commandParser_success_StopCommand() {
        Command command = commandParser.apply("Q");
        assertNotNull( command);
        assert(command instanceof ZombieStop);
    }
}