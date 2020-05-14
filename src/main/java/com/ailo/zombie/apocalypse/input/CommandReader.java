package com.ailo.zombie.apocalypse.input;

import com.ailo.zombie.apocalypse.exception.SimulationException;

import java.util.stream.Stream;

public class CommandReader {

    public Stream<String> enterCommand(String command) throws SimulationException {
        return (command+"Q").codePoints()
                .mapToObj(c -> String.valueOf((char) c));
    }

}

