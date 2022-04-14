package com.docks.piers;

import java.util.concurrent.Semaphore;

import com.docks.models.Pier;
import com.docks.models.Ship;
import com.docks.models.types.ShipType;
import com.docks.tunnel.Tunnel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PierHandler extends Pier implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(PierHandler.class);

    public PierHandler(Tunnel tunnel, ShipType type, Semaphore semaphore) {
        super(tunnel, type, semaphore);
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.type + " Handler");

        try {
            acquirePermission();
            unloadShips();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        } finally {
            permit();
        }
    }

    public void unloadShips() throws InterruptedException {
        if (!this.tunnel.existsByType(type)) {
            logger.info("No <" + this.type + "> was found.");
            return;
        }

        while (true) {
            Ship ship = this.tunnel.pull(this.type);

            if (ship == null) {
                break;
            }

            try {
                int time = 1000 + (ship.getSize() * 20);
                
                logger.info(time + " ms. - " + "Unloading <" + this.type + "> ship from tunnel.");
                Thread.sleep(time);
                logger.info("Done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void acquirePermission() throws InterruptedException {
        if (semaphore != null) {
            logger.info("[SEMAPHORE] waiting for permission.");
            this.semaphore.acquire();
        }
    }

    private void permit() {
        if (this.semaphore != null) {
            this.semaphore.release();
            logger.info("[SEMAPHORE] released semaphore");
        }
    }
}
