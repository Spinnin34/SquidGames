package p.squidgames.games;

import lombok.Getter;

public enum LocationType {
    SPAWN("spawn");

    @Getter private final String identifier;

    LocationType(String identifier) {
        this.identifier = identifier;
    }
}
