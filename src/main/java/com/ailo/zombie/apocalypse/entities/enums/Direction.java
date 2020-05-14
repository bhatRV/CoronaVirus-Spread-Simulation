package com.ailo.zombie.apocalypse.entities.enums;

public enum Direction {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R'),
    U('U'),
    D('D'),
    L('L'),
    R('R');

    Direction(char direction) {
    }

    public Direction moveZombie(){
        switch (this){
            case UP:
                return U;
            case DOWN:
                return D;
            case LEFT:
                return L;
            case RIGHT:
                return R;
            default:
                return this;
        }
    }
}
