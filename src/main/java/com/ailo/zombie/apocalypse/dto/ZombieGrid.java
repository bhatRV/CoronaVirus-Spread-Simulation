package com.ailo.zombie.apocalypse.dto;

import com.ailo.zombie.apocalypse.dto.enums.Type;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZombieGrid {

    private final Type type;
    private boolean infected;

}
