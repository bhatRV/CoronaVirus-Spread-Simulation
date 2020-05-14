package com.ailo.zombie.apocalypse.entities.enums;

/**
 * The possible types of squares and relevant activities.
 */
public enum Type {
    PLAIN,
    ZOMBIE,
    CREATURE;

    public static Type of(char c){
        switch (c) {
            case 'Z':
                return Type.ZOMBIE;
            case 'C':
                return Type.CREATURE;
            default:
                return Type.PLAIN;
        }
    }

}
