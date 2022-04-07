package com.docks.tunnel;

import java.util.ArrayList;
import java.util.List;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tunnel implements Controllable {
    private static final Logger logger = LoggerFactory.getLogger(Tunnel.class);
    private static final int MAX_CAPACITY = 5;
    private static final int MIN_CAPACITY = 0; 

    private List<Ship> ships;
    private int shipsCount;

    public Tunnel() {
        this.ships = new ArrayList<>();
        this.shipsCount = 0;
    }

    @Override
    public boolean add(Ship ship) {
        if (isFull(ship)) {
            logger.error("Tunnel is full.");
            return false;
        }

        ships.add(ship);
        updateShipsCount();
        return true;
    }

    @Override
    public Ship pull(ShipType type) {
        if (isEmpty()) {
            logger.error("Tunner is empty.");
            return null;
        }

        Ship target = null;

        for (Ship ship : ships) {
            if (ship.getType().equals(type)) {
                target = ship;
                break;
            }
        }

        if (target == null) {
            logger.error("Ship with type [" + type + "] was not found.");
            return null;
        }

        ships.remove(target);
        updateShipsCount();
        return target;
    }

    public boolean isFull(Ship ship) {
        return shipsCount + ship.getCount() > MAX_CAPACITY;
    }

    public boolean isEmpty() {
        return shipsCount == MIN_CAPACITY;
    }

    private void updateShipsCount() {
        for (Ship ship : ships) {
            this.shipsCount += ship.getCount();
        }
    }
}
