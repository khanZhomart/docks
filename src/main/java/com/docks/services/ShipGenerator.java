package com.docks.services;

import java.util.stream.IntStream;

import com.docks.models.Ship;
import com.docks.tunnel.Tunnel;
import com.docks.utils.Randomizer;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class ShipGenerator implements Generator, Runnable {
    // private static final Logger logger = LoggerFactory.getLogger(ShipGenerator.class);

    private Tunnel tunnel;
    private int count;

    public ShipGenerator(Tunnel tunnel, int count) {
        this.tunnel = tunnel;
        this.count = count;
    }

    @Override
    public void run() {
        IntStream.range(0, count)
            .forEach((i) -> {
                tunnel.push(getRandomShip());
            });
    }

    @Override
    public Ship getRandomShip() {
        return new Ship.Builder()
            .type(Randomizer.Ship.getRandomType())
            .size(Randomizer.Ship.getRandomSize())
            .count(Randomizer.getRandomNumber(1, 3))
            .build();
    }
}
