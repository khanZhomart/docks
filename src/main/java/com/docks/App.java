package com.docks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.docks.models.types.ShipType;
import com.docks.piers.PierHandler;
import com.docks.services.ShipGenerator;
import com.docks.tunnel.Tunnel;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Tunnel tunnel = new Tunnel();
        Semaphore sem = new Semaphore(1);

        ShipGenerator generator = new ShipGenerator(tunnel, 5);
        
        PierHandler breadHandler = new PierHandler(tunnel, ShipType.BREAD, sem);
        PierHandler bananaHandler = new PierHandler(tunnel, ShipType.BANANA, sem);
        PierHandler clothingHandler = new PierHandler(tunnel, ShipType.CLOTHING, sem);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.execute(generator);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.execute(breadHandler);
        executor.execute(bananaHandler);
        executor.execute(clothingHandler);
    }
}
