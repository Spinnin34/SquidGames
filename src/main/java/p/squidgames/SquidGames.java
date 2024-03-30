package p.squidgames;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import p.squidgames.Configuracion.ConfigurationManager;
import p.squidgames.Listener.PlayerJoinListener;
import p.squidgames.command.GameCommand;
import p.squidgames.command.NumeroCommad;
import p.squidgames.command.SetupCommand;
import p.squidgames.command.TareaCommand;
import p.squidgames.manager.GamePlayerManager;
import p.squidgames.manager.PlaholderAPI;
import p.squidgames.pruebas.luzverde.LuzManagerCommand;
import p.squidgames.pruebas.luzverde.LuzVerdeLuzRojaListener;
import p.squidgames.region.Arena.Arena;

public final class SquidGames extends JavaPlugin {

    @Getter private static SquidGames instance;

    @Getter private Arena arena;

    @Getter private PaperCommandManager paperCommandManager;
    @Getter private ConfigurationManager configurationManager;
    @Getter private GamePlayerManager gamePlayerManager;

    @Getter private Configuration locationsConfiguration;

    @Override
    public void onEnable() {
        instance = this;

        this.arena = new Arena();
        getLogger().info("Plugin habilitado Developer ©Spinnin34");

        getCommand("tarea").setExecutor(new TareaCommand(this));
        getCommand("numero").setExecutor(new NumeroCommad());
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        LuzVerdeLuzRojaListener listener = new LuzVerdeLuzRojaListener(this);
        getServer().getPluginManager().registerEvents(listener, this);
        getCommand("luzmanager").setExecutor(new LuzManagerCommand(this, listener));


        this.paperCommandManager = new PaperCommandManager(this);
        this.configurationManager = new ConfigurationManager();
        this.gamePlayerManager = new GamePlayerManager();

        this.paperCommandManager.registerCommand(new GameCommand());
        this.paperCommandManager.registerCommand(new SetupCommand());

        this.locationsConfiguration = this.configurationManager.getConfig("locations.yml");



        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaholderAPI(this, new PlayerJoinListener(this)).register();
        } else {
            getLogger().warning("PlaceholderAPI no encontrado. La expansión de PlaceholderAPI no funcionará.");
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin deshabilitado Developer ©Spinnin34");
    }


}
