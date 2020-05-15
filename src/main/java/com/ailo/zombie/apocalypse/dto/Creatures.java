package com.ailo.zombie.apocalypse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Creatures {
    Coordinates[] positions;
}
