package com.docks.models;

import com.docks.models.types.ShipSize;
import com.docks.models.types.ShipType;

public class Ship {
    private ShipType type;
    private ShipSize size;

    private Ship(Builder builder) {
        this.type = builder.type;
        this.size = builder.size;
    }

    public ShipType getType() {
        return this.type;
    }

    public int getSize() {
        return this.size.getValue();
    }

    public static class Builder {
        private ShipType type;
        private ShipSize size;

        public Builder type(ShipType type) {
            this.type = type;
            return this;
        }

        public Builder size(ShipSize size) {
            this.size = size;
            return this;
        }

        public Ship build() {
            return new Ship(this);
        }
    }
}
