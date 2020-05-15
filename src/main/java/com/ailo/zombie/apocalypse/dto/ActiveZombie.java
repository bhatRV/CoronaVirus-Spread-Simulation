package com.ailo.zombie.apocalypse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActiveZombie {
    Coordinates position;
}
