package p.squidgames.Configuracion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import p.squidgames.SquidGames;

public class ConfigurationManager {

    private final Map<String, Configuration> configurationMap;

    public ConfigurationManager() {
        this.configurationMap = new HashMap<>();
    }

    public Configuration getConfig(String name) {
        Configuration configuration = configurationMap.get(name);

        if (configuration != null) {
            return configuration;
        }

        File configurationFile = new File(SquidGames.getInstance().getDataFolder(), name);
        if (!configurationFile.exists()) {
            configurationFile.getParentFile().mkdirs();
            SquidGames.getInstance().saveResource(name, false);
        }

        configuration = new Configuration(configurationFile);
        try {
            configuration.load();
            this.configurationMap.put(name, configuration);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }

        return configuration;
    }
    public List<Configuration> configurationList() {
        return new ArrayList<>(configurationMap.values());
    }
}
