package com.docks.tunnel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;
import com.docks.utils.constants.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tunnel implements Controllable {
    private static final Logger logger = LoggerFactory.getLogger(Tunnel.class);

    private List<Ship> ships;
    private int shipsCount;

    public Tunnel() {
        this.ships = new ArrayList<>();
        this.shipsCount = 0;
    }

    @Override
    public synchronized boolean push(Ship ship) {
        if (!isAbleToPush(ship)) {
            logger.warn("Tunnel is full.");
            return false;
        }

        ships.add(ship);
        updateShipsCount();
        return true;
    }

    @Override
    public synchronized Ship pull(ShipType type) {
        if (isEmpty()) {
            return null;
        }

        Ship target = findByType(type);

        if (isNonValid(target)) {
            return null;
        }

        ships.remove(target);
        updateShipsCount();
        return target;
    }

    public boolean isAbleToPush(Ship ship) {
        return shipsCount + ship.getCount() <= Constants.MAX_TUNNEL_CAPACITY;
    }

    public boolean isFull() {
        return shipsCount >= Constants.MAX_TUNNEL_CAPACITY;
    }

    public boolean isEmpty() {
        return shipsCount == Constants.MIN_TUNNEL_CAPACITY;
    }

    public boolean isNonValid(Object o) {
        return o == null;
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

    private void updateShipsCount() {
        shipsCount = ships.stream()
            .map(Ship::getCount)
            .reduce(0, Integer::sum);
    }
}
