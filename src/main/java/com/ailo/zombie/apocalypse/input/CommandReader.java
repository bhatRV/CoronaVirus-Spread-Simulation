package com.ailo.zombie.apocalypse.input;

import java.util.stream.Stream;

public class CommandReader {


    public Stream<String> enterCommand(String command) {

        return (command+"Q").codePoints()
                .mapToObj(c -> String.valueOf((char) c));


    }

}

