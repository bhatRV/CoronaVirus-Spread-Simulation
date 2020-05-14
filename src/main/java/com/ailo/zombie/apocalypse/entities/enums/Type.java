package com.ailo.zombie.apocalypse.entities.enums;

/**
 * @Rashmi.Vishnu
 * The possible types of ZombieGrid and relevant activities.
 */
public enum Type {
    NONE,
    ZOMBIE,
    CREATURE;

    public static Type of(char c){
        switch (c) {
            case 'Z':
                return Type.ZOMBIE;
            case 'C':
                return Type.CREATURE;
            default:
                return Type.NONE;
        }
    }

}
