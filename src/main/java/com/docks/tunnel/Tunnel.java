package com.docks.tunnel;

import java.util.concurrent.ArrayBlockingQueue;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class Tunnel implements Controllable {
    // private static final Logger logger = LoggerFactory.getLogger(Tunnel.class);

    private ArrayBlockingQueue<Ship> ships = new ArrayBlockingQueue<>(5);

    @Override
    public void push(Ship ship) throws IllegalArgumentException {
        ships.add(ship);  
    }

    @Override
    public Ship pull(ShipType type) {
        Ship ship = findByType(type);
        ships.remove(ship);
        return ship;
    }

    public Ship findByType(ShipType type) {
        return ships.stream()
            .filter((s) -> s.getType().equals(type))
            .findFirst()
            .orElse(null);
    }

    public boolean existsByType(ShipType type) {
        return ships.stream()
            .filter((s) -> s.getType().equals(type))
            .findFirst()
            .isPresent();
    }
    
    public int getSize() {
        return this.ships.size();
    }
}
