package com.docks.tunnel;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;

public class Tunnel implements Controllable {
    private ConcurrentLinkedQueue<Ship> ships = new ConcurrentLinkedQueue<>(new ArrayList<>());

    @Override
    public void push(Ship ship) {
        if (!available()) {
            return;
        }

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

    public boolean available() {
        return this.ships.size() < 5;
    }
    
    public int getSize() {
        return this.ships.size();
    }
}
