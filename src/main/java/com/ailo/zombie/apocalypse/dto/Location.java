package com.ailo.zombie.apocalypse.dto;


import com.ailo.zombie.apocalypse.dto.enums.Direction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

    private final int coordinateX;
    private final int coordinateY;
    private Direction direction;
    private String route;
    private GridDimension gridDimension;
}
