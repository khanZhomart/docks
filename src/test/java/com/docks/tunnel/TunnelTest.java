package com.docks.tunnel;

import java.util.stream.Stream;

import com.docks.models.Ship;
import com.docks.models.types.ShipSize;
import com.docks.models.types.ShipType;
import com.docks.utils.Randomizer.ShipRandomizer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class TunnelTest {
    private static final Tunnel tunnel = new Tunnel();
    private static final ShipType[] types = ShipType.values();
    private static Ship ship;
    
    @After
    public void cleanTunnel() {
        Stream.of(types)
            .forEach((type) -> tunnel.pull(type));
    }

    @Test
    public void should_ReturnFalseOnPush_When_TooManyShips() {
        boolean result = true;

        for (int i = 0; i < 6; i++) {
            Ship ship = ShipRandomizer.getRandomShip();
            result = tunnel.push(ship);
        }

        Assert.assertFalse(result);
    }

    @Test 
    public void should_ReturnTrueOnPush() {
        ship = new Ship.Builder()
            .size(ShipSize.SMALL)
            .type(ShipType.CLOTHING)
            .build();

        boolean result = tunnel.push(ship);
        Assert.assertTrue(result);
    }

    @Test
    public void should_ReturnNull_When_InvalidShipProvided() {
        Ship result = tunnel.findByType(ShipType.BANANA);
        Assert.assertNull(result);
    }

    @Test
    public void should_PullShipsByTypeFromTunnel_When_ValidShipProvided() {
        ship = new Ship.Builder()
            .size(ShipSize.SMALL)
            .type(ShipType.CLOTHING)
            .build();

        tunnel.push(ship);
        tunnel.pull(ShipType.CLOTHING);

        boolean result = tunnel.existsByType(ShipType.CLOTHING);
        Assert.assertFalse(result);
    }

    @Test
    public void should_ReturnCorrectCount_On_ShipsPush() {
        ship = new Ship.Builder()
            .size(ShipSize.SMALL)
            .type(ShipType.CLOTHING)
            .build();

        tunnel.push(ship);

        ship = new Ship.Builder()
            .size(ShipSize.SMALL)
            .type(ShipType.BANANA)
            .build();

        tunnel.push(ship);

        int expected = 2;
        int actual = tunnel.getSize();
        Assert.assertEquals(expected, actual);
    }
}
