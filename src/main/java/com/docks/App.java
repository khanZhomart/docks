package com.docks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.docks.piers.PierHandler;
import com.docks.services.generator.ShipGenerator;
import com.docks.tunnel.Tunnel;
import com.docks.utils.PierUtility;

public class App {
    
    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel();
        Semaphore sem = new Semaphore(1);

        ShipGenerator generator = new ShipGenerator(tunnel, 10);
        
        PierUtility.init(tunnel, sem);
        PierHandler breadHandler = PierUtility.getBreadHandler();
        PierHandler bananaHandler = PierUtility.getBananaHandler();
        PierHandler clothingHandler = PierUtility.getClothingHandler();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        executor.execute(generator);

        try {
            executor.awaitTermination(5_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.execute(breadHandler);
        executor.execute(bananaHandler);
        executor.execute(clothingHandler);
    }
}
