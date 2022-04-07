package com.docks.utils;

import java.util.Random;

import com.docks.models.types.ShipSize;
import com.docks.models.types.ShipType;

public class Randomizer {

    public static class Ship {

        public static ShipType getRandomType() {
            return ShipType.values()[getRandomNumber(ShipType.values().length)];
        }
    
        public static ShipSize getRandomSize() {
            return ShipSize.values()[getRandomNumber(ShipSize.values().length)];
        }
    }
    
    public static int getRandomNumber(int max) {
        return new Random().nextInt(max);
    }

    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) + min;
    }
}
