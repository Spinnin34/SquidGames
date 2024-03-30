package p.squidgames.pruebas.luzverde;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class LuzVerdeLuzRojaListener implements Listener {
    private Cuboid gameRegion;
    private Cuboid metaRegion;
    private boolean gameRunning;

    private boolean isEnabled;

    public void enable() {
        isEnabled = true;
    }

    public void disable() {
        isEnabled = false;
    }

    public LuzVerdeLuzRojaListener(Plugin plugin) {
        World world = plugin.getServer().getWorld("nombre_del_mundo");
        if (world == null) {
            plugin.getLogger().severe("No se pudo cargar el mundo. El plugin no funcionará correctamente.");
            return;
        }

        Location start1 = new Location(world, 151, -8, -220);
        Location end1 = new Location(world, 79, -48, -314);
        gameRegion = new Cuboid(start1, end1);


        Location start2 = new Location(world, 79, -49, -315);
        Location end2 = new Location(world, 151, 1, -329);
        metaRegion = new Cuboid(start2, end2);

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!isEnabled) return;
        Player player = event.getPlayer();
        Location to = event.getTo();

        // Verificar si el jugador está dentro de la región del juego
        if (gameRegion.isInside(to)) {
            if (!gameRunning) {
                player.sendTitle("¡Luz Verde!", "", 10, 70, 20);
                player.sendMessage("¡Puedes moverte! ¡Espera a que comience el juego!");
            } else {
                // Verificar si el jugador está en la región de la meta
                if (metaRegion.isInside(to)) {
                    player.sendMessage("¡Has llegado a la meta! ¡Eres el ganador!");
                } else {
                    player.sendTitle("¡Luz Roja!", "", 10, 70, 20);
                    player.sendMessage("¡No puedes moverte! ¡Si lo haces, morirás!");
                    player.teleport(player.getWorld().getSpawnLocation());
                    player.sendMessage("¡Has sido eliminado del juego!");
                }
            }
        }
    }
}


