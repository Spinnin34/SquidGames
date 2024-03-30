package p.squidgames.manager;

import org.bukkit.entity.Player;
import p.squidgames.pruebas.player.GamePlayer;

import java.util.*;

public class GamePlayerManager {
    private final HashMap<UUID, GamePlayer> gamePlayerMap;

    public GamePlayerManager() {
        this.gamePlayerMap = new HashMap<>();
    }

    public GamePlayer get(Player player) {
        return gamePlayerMap.computeIfAbsent(player.getUniqueId(), uuid -> new GamePlayer(player));
    }
    public GamePlayer destroy(Player player) {
        return gamePlayerMap.remove(player.getUniqueId());
    }
    public Collection<GamePlayer> getList() {
        return gamePlayerMap.values();
    }
}
