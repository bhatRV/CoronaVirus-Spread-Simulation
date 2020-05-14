package com.ailo.zombie.apocalypse.input;


import com.ailo.zombie.apocalypse.commands.AdvanceCommand;
import com.ailo.zombie.apocalypse.commands.Command;
import com.ailo.zombie.apocalypse.commands.QuitCommand;
import com.ailo.zombie.apocalypse.commands.UnknownCommand;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser implements Function<String, Command> {

    private static final Pattern ADVANCE_REGEX = Pattern.compile("(ADVANCE|A)\\s([1-9][0-9]*)");
    private static final String LEFT_REGEX = "(LEFT|L)";
    private static final String RIGHT_REGEX = "(RIGHT|R)";
    private static final String DOWN_REGEX = "(DOWN|D)";
    private static final String UP_REGEX = "(UP|U)";

    @Override
    public Command apply(String inputString) {
        if (inputString.startsWith("A")) {
            Matcher matcher = ADVANCE_REGEX.matcher(inputString);
            if (matcher.matches()) {
                return new AdvanceCommand(Integer.parseInt(matcher.group(2)));
            }
        } else if (Pattern.matches(LEFT_REGEX, inputString)) {
            return new AdvanceCommand(1);
        } else if (Pattern.matches(RIGHT_REGEX, inputString)) {
            return new AdvanceCommand(1);
        } else if (Pattern.matches(DOWN_REGEX, inputString)) {
            return new AdvanceCommand(1);
        } else if (Pattern.matches(UP_REGEX, inputString)) {
            return new AdvanceCommand(1);
        } else if (Pattern.matches("(QUIT|Q)", inputString)) {
            return new QuitCommand("Zombie stopped infecting!!");
        }

        return new UnknownCommand(inputString);
    }

}

