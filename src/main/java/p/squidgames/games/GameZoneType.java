package p.squidgames.games;

import lombok.Getter;

public enum GameZoneType {
    RED_GREEN_LIGHT_SPAWN_ZONE(GameType.RED_LIGHT_GREEN_LIGHT, "spawn_zone"),
    RED_GREEN_LIGHT_KILL_ZONE(GameType.RED_LIGHT_GREEN_LIGHT, "kill_zone"),
    RED_GREEN_LIGHT_SAFE_ZONE(GameType.RED_LIGHT_GREEN_LIGHT, "safe_zone");

    @Getter private final GameType gameType;
    @Getter private final String identifier;

    GameZoneType(GameType gameType, String identifier) {
        this.gameType = gameType;
        this.identifier = identifier;
    }
}
