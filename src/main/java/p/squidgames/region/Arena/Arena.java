package p.squidgames.region.Arena;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.Location;
import p.squidgames.SquidGames;
import p.squidgames.games.BaseGame;
import p.squidgames.games.GameType;
import p.squidgames.pruebas.player.GamePlayer;
import p.squidgames.utils.toggle.ChangeGameTypeTask;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Accessors(chain = true)
public class Arena {
    @Getter @Setter private GameType gameType;
    @Getter @Setter private BaseGame baseGame;

    public Arena() {
    }

    public void start() {
        new ChangeGameTypeTask(this);
    }

    public void startGame(GameType gameType) {
        this.gameType = gameType;

        try {
            this.baseGame = (BaseGame) gameType.getBaseGame().getConstructor(Arena.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException exception) {
            throw new RuntimeException(exception.getCause());
        }
    }

    public List<GamePlayer> getPlayers() {
        return SquidGames.getInstance().getGamePlayerManager().getList().stream()
                .filter(target -> target.getGameArena().equals(this)).collect(Collectors.toList());
    }
    public void doGlobally(Consumer<GamePlayer> consumer) {
        getPlayers().forEach(consumer);
    }
    public void teleport(Location bukkitLocation) {
        doGlobally(gamePlayer -> gamePlayer.getBukkitPlayer().teleport(bukkitLocation));
    }
    public String getResourceKey(String key) {
        return new StringJoiner(".").add(this.gameType.getIdentifier()).add(key).toString();
    }
}
