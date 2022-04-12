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
        private static final ShipType[] types = ShipType.values();
        private static final ShipSize[] sizes = ShipSize.values();

        public static ShipType getRandomType() {
            final int n = getRandomNumber(types.length);
            return types[n];
        }
    
        public static ShipSize getRandomSize() {
            final int n = getRandomNumber(sizes.length);
            return sizes[n];
        }
    }
}
