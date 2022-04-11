package com.docks.piers;

import com.docks.models.types.ShipType;
import com.docks.tunnel.Tunnel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PierHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(PierHandler.class);

    private Tunnel tunnel;
    private ShipType type;

    @Override
    public void run() {
        
    }
}
