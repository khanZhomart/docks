package com.docks.piers;

import java.util.concurrent.Semaphore;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;
import com.docks.tunnel.Tunnel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PierHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(PierHandler.class);

    private Semaphore semaphore;

    private Tunnel tunnel;
    private ShipType type;

    public PierHandler(Tunnel tunnel, ShipType type, Semaphore semaphore) {
        this.tunnel = tunnel;
        this.type = type;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(type + " Handler");

        try {
            logger.warn("[SEMAPHORE] waiting for permission.");
            semaphore.acquire();
            unloadShips();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        } finally {
            semaphore.release();
            logger.warn("[SEMAPHORE] released semaphore\n");
        }
    }

    public void unloadShips() throws InterruptedException {
        Ship ship;

        if (!tunnel.existsByType(type)) {
            logger.warn("No <" + type + "> was found.");
            return;
        }

        while (tunnel.existsByType(type)) {
            ship = tunnel.pull(type);
            logger.info((2 * ship.getCount()) + "s. - " + "Unloading <" + type + "> ships from tunnel.");
            wait(ship.getCount());
            logger.info("Done.");
        }
    }

    private void wait(int count) throws InterruptedException {
        Thread.sleep(2000 * count);
    }
}
