package com.docks.utils;

import java.util.Random;

import com.docks.models.types.ShipSize;
import com.docks.models.types.ShipType;

public class Randomizer {
    
    public static int getRandomNumber(int max) {
        return new Random().nextInt(max);
    }

    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) + min;
    }

    public static class Ship {
        private static final int SHIP_TYPE_LENGTH = ShipType.values().length;
        private static final int SHIP_SIZE_LENGTH = ShipSize.values().length;

        public static ShipType getRandomType() {
            final int n = getRandomNumber(SHIP_TYPE_LENGTH);
            return ShipType.values()[n];
        }
    
        public static ShipSize getRandomSize() {
            final int n = getRandomNumber(SHIP_SIZE_LENGTH);
            return ShipSize.values()[n];
        }
    }
}
