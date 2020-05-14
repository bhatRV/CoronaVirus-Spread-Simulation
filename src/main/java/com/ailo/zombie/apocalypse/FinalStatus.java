package com.ailo.zombie.apocalypse;

public class FinalStatus {
    public static int getZombiesCount() {
        return zombiesCount;
    }

    public static void setZombiesCount(int zombiesCount) {
        FinalStatus.zombiesCount = zombiesCount;
    }

    public static String getZombiesPosition() {
        return zombiesPosition;
    }

    public static void setZombiesPosition(String zombiesPosition) {
        FinalStatus.zombiesPosition = zombiesPosition;
    }

    static int zombiesCount = 0;
    static String zombiesPosition = "";

}