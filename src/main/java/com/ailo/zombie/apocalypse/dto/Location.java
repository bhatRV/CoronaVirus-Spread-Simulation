package com.ailo.zombie.apocalypse.dto;


import com.ailo.zombie.apocalypse.dto.enums.Direction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

    private final long coordinateX;
    private final long coordinateY;
    private Direction direction;
    private String route;
    private GridDimension gridDimension;
}
