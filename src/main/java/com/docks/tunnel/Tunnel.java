package com.docks.tunnel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;
import com.docks.utils.constants.Constants;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class Tunnel implements Controllable {
    // private static final Logger logger = LoggerFactory.getLogger(Tunnel.class);

    private List<Ship> ships = Collections.synchronizedList(new ArrayList<>());

    @Override
    public boolean push(Ship ship) {
        if (isFull()) {
            return false;
        }

        ships.add(ship);
        return true;
    }

    @Override
    public Ship pull(ShipType type) {
        if (isEmpty()) {
            return null;
        }

        Ship ship = findByType(type);

        if (isNonValid(ship)) {
            return null;
        }

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
    
    public boolean isFull() {
        return ships.size() >= Constants.MAX_TUNNEL_CAPACITY;
    }

    public boolean isEmpty() {
        return ships.isEmpty();
    }

    public boolean isNonValid(Ship ship) {
        return ship == null;
    }

    public int getSize() {
        return this.ships.size();
    }
}
