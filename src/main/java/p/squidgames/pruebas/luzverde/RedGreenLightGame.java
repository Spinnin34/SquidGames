package p.squidgames.pruebas.luzverde;

import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import p.squidgames.SquidGames;
import p.squidgames.games.BaseGame;
import p.squidgames.games.GameZoneType;
import p.squidgames.pruebas.player.GamePlayer;
import p.squidgames.region.Arena.Arena;
import p.squidgames.utils.task.BasicRepeatableTask;
import p.squidgames.utils.task.DelayedTask;

import java.util.concurrent.ThreadLocalRandom;

public class RedGreenLightGame extends BaseGame {
    @Getter private boolean canMove;

    public RedGreenLightGame(Arena arena) {
        super(arena);

        this.canMove = true;
    }

    @Override
    public void onSpawn() {
        arena.teleport(this.spawnLocation);
    }

    @Override
    public void onStart() {
        new BasicRepeatableTask(() -> {
            if (canMove) {
                this.arena.doGlobally(target -> target.sendTitle(this.arena.getResourceKey("red_light.title")));
                this.arena.doGlobally(target -> target.getBukkitPlayer().getInventory().clear());
            } else {
                this.arena.doGlobally(target -> target.sendTitle(this.arena.getResourceKey("green_light.title")));
                this.arena.doGlobally(target -> target.getBukkitPlayer().getInventory().clear());
            }

            new DelayedTask(() -> {
                canMove = !canMove;
            }, 20);
        }, 0, () -> ThreadLocalRandom.current().nextInt(3, 5));
    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onTimeUp() {

    }

    @Override
    public void onSkip() {

    }

    @Override
    public void onPlayerDeath() {

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        GamePlayer gamePlayer = SquidGames.getInstance().getGamePlayerManager().get(event.getPlayer());

        if (this.getZone(GameZoneType.RED_GREEN_LIGHT_KILL_ZONE).isBetween(gamePlayer.getLocation()) && !this.isCanMove()) {
            gamePlayer.kill();
        }
    }
}
