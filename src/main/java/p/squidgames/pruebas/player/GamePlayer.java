package p.squidgames.pruebas.player;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import p.squidgames.region.Arena.Arena;
import p.squidgames.region.Vector3;

public class GamePlayer {
    @Getter private final Player bukkitPlayer;

    @Getter @Setter private Arena gameArena;

    public GamePlayer(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
    }
    public Vector3 getLocation() {
        return new Vector3(this.bukkitPlayer.getLocation());
    }
    public void sendTitle(String key) {

    }
    public void kill() {
        this.bukkitPlayer.setHealth(0);
    }
}