package com.ailo.zombie.apocalypse.input;


import com.ailo.zombie.apocalypse.commands.ZombieMove;
import com.ailo.zombie.apocalypse.commands.Command;
import com.ailo.zombie.apocalypse.commands.ZombieStop;
import com.ailo.zombie.apocalypse.utils.Constants;

import java.util.function.Function;
import java.util.regex.Pattern;

public class CommandParser implements Function<String, Command> {


    @Override
    public Command apply(String inputString) {
        return Pattern.matches(Constants.COMMAND_PATTERN_REGX, inputString)
                ? new ZombieMove(1)
                : new ZombieStop("Zombie stopped infecting!!");
    }

}

