package com.docks.models.types;

public enum ShipSize {
    SMALL(10),
    MEDIUM(50),
    LARGE(100);

    private int value;

    private ShipSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
