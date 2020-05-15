package com.ailo.zombie.apocalypse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataInput {
    GridDimension gridDimension;
    ActiveZombie activeZombiePosition;
    Creatures creaturesPosition;
    String command;
}
