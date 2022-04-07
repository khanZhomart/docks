package com.docks.flow;

import java.util.ArrayList;
import java.util.List;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;

public class Tunnel implements Controllable {
    private static final int MAX_CAPACITY = 5;
    private static final int MIN_CAPACITY = 0; 

    private List<Ship> ships;

    public Tunnel() {
        this.ships = new ArrayList<>();
    }

    @Override
    public synchronized void add() {

    }

    @Override
    public synchronized void remove() {

    }

    @Override
    public Ship getShipByType(ShipType type) {
        return this.ships.stream()
            .filter((ship) -> ship.getType() == type)
            .findFirst()
            .get();
    }

    public boolean isFull() {
        return getSize() >= MAX_CAPACITY;
    }

    public boolean isEmpty() {
        return getSize() == MIN_CAPACITY;
    }

    private int getSize() {
        return this.ships.size();
    }
}
