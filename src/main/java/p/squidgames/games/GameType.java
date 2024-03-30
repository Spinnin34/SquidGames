package p.squidgames.games;

import lombok.Getter;
import p.squidgames.pruebas.luzverde.RedGreenLightGame;

public enum GameType {
    RED_LIGHT_GREEN_LIGHT("red_green_light", RedGreenLightGame.class);

    @Getter private final String identifier;
    @Getter private final Class<? extends GameActions> baseGame;

    GameType(String identifier, Class<? extends GameActions> baseGame) {
        this.identifier = identifier;
        this.baseGame = baseGame;
    }
}
