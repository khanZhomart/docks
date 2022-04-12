package com.docks.models;

import java.util.concurrent.Semaphore;

import com.docks.models.types.ShipType;
import com.docks.tunnel.Tunnel;

public class Pier {
    protected Semaphore semaphore;

    protected Tunnel tunnel;
    protected ShipType type;

    public Pier(Tunnel tunnel, ShipType type, Semaphore semaphore) {
        this.tunnel = tunnel;
        this.type = type;
        this.semaphore = semaphore;
    }
}
