package com.ailo.zombie.apocalypse.dto;


import com.ailo.zombie.apocalypse.commands.Command;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Zombie {
    private Location currentLocation;
    private final List<Command> commandList = new ArrayList<>();
    private DataInput dataInput;
    private boolean placed;

    public Command getLastCommand() {
        return commandList.get(commandList.size() - 1);
    }
}
