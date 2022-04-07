package com.docks.flow;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;

public interface Controllable {
    void add();
    void remove();
    Ship getShipByType(ShipType type);
}
