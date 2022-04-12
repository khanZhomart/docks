package com.docks.services.generator;

import com.docks.tunnel.Tunnel;
import com.docks.utils.Randomizer.ShipRandomizer;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class ShipGenerator implements Runnable {
    // private static final Logger logger = LoggerFactory.getLogger(ShipGenerator.class);

    private Tunnel tunnel;
    private int count;

    public ShipGenerator(Tunnel tunnel, int count) {
        this.tunnel = tunnel;
        this.count = count;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("GENERATOR");

        for (int i = 0; i < count; i++) {
            if (!tunnel.push(ShipRandomizer.getRandomShip())) {
                break;
            }
        }
    }
}
