package com.docks.models;

import com.docks.models.types.ShipSize;
import com.docks.models.types.ShipType;

public class Ship {
    private ShipType type;
    private ShipSize size;
    private int count;

    private Ship(Builder builder) {
        this.type = builder.type;
        this.size = builder.size;
        this.count = builder.count;
    }

    public ShipType getType() {
        return this.type;
    }

    public int getSize() {
        return this.size.getValue();
    }

    public int getCount() {
        return this.count;
    }

    public static class Builder {
        private ShipType type;
        private ShipSize size;
        private int count;

        public Builder type(ShipType type) {
            this.type = type;
            return this;
        }

        public Builder size(ShipSize size) {
            this.size = size;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Ship build() {
            return new Ship(this);
        }
    }
}
