package com.docks.utils;

import java.util.concurrent.Semaphore;

import com.docks.models.types.ShipType;
import com.docks.piers.PierHandler;
import com.docks.tunnel.Tunnel;

public class PierUtility {
    private static Tunnel tunnel;
    private static Semaphore semaphore;

    public static void init(Tunnel t, Semaphore s) {
        tunnel = t;
        semaphore = s;
    }

    public static PierHandler getBananaHandler() {
        return new PierHandler(tunnel, ShipType.BANANA, semaphore);
    }

    public static PierHandler getBreadHandler() {
        return new PierHandler(tunnel, ShipType.BREAD, semaphore);
    }

    public static PierHandler getClothingHandler() {
        return new PierHandler(tunnel, ShipType.CLOTHING, semaphore);
    }
}
