package com.docks.services.generator;

import com.docks.models.Ship;
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
            try {
                Ship ship = ShipRandomizer.getRandomShip();
                tunnel.push(ship);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                break;
            } 
        }
    }
}
