package com.docks.tunnel;

import java.util.concurrent.ArrayBlockingQueue;

import com.docks.utils.constants.Constants;
import com.docks.models.Ship;
import com.docks.models.types.ShipType;

public class Tunnel implements Controllable {
    private ArrayBlockingQueue<Ship> ships = new ArrayBlockingQueue<>(Constants.MAX_TUNNEL_CAPACITY);

    @Override
    public void push(Ship ship) {
        try {
            ships.add(ship);
        } catch (IllegalArgumentException e) {
            return;
        }
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

    public boolean available() {
        return this.ships.remainingCapacity() > 0;
    }
    
    public int getSize() {
        return this.ships.size();
    }
}
