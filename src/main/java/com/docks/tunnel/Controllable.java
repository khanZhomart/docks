package com.docks.tunnel;

import com.docks.models.Ship;
import com.docks.models.types.ShipType;

public interface Controllable {
    boolean add(Ship ship);
    Ship pull(ShipType type);
}
