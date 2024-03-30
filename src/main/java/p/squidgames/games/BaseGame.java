package p.squidgames.games;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import p.squidgames.SquidGames;
import p.squidgames.manager.GameManager.EventManager;
import p.squidgames.manager.GamePlayerManager;
import p.squidgames.region.Arena.Arena;
import p.squidgames.region.Cuboid;

import java.util.StringJoiner;

public abstract class BaseGame implements Listener, GameActions {
    @Getter protected final Arena arena;
    @Getter protected final String identifier;
    @Getter protected final ConfigurationSection configurationSection;

    protected final Location spawnLocation;

    public BaseGame(Arena arena) {
        this.arena = arena;

        this.spawnLocation = getLocation("spawn");
        this.identifier = new StringJoiner(".").add("games").add(this.arena.getGameType().getIdentifier()).toString();
        this.configurationSection = SquidGames.getInstance().getConfig().getConfigurationSection(identifier);
    }
    public Cuboid getZone(GameZoneType gameZoneType) {
        return SquidGames.getInstance().getLocationsConfiguration().getCuboid(this.arena.getResourceKey(gameZoneType.getIdentifier()));
    }
    public Location getLocation(String identifier) {
        return SquidGames.getInstance().getLocationsConfiguration().getLocation(this.configurationSection.get(identifier).toString());
    }
    public void setLocation(String identifier, Location location) {
        SquidGames.getInstance().getLocationsConfiguration().setLocation(new StringJoiner(".").add(this.identifier).add(identifier).toString(), location);
    }

    public void onEnable() {
        EventManager.registerListener(this);
        onStart();
    }

    public void onDisable() {
        EventManager.unregisterListener(this);
        onEnd();
    }

    public abstract void onSpawn();

    public void onStart() {
    }

    public void onEnd() {
    }

    public abstract void onTimeUp();

    public abstract void onSkip();
}
