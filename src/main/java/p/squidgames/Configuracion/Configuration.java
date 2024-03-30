package p.squidgames.Configuracion;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import p.squidgames.region.Cuboid;

public class Configuration extends YamlConfiguration {

    private File file;

    public Configuration(File file) {
        this.file = file;
    }

    public void load() throws IOException, InvalidConfigurationException {
        this.load(this.file);
    }

    public void save() throws IOException {
        this.save(this.file);
    }

    public void safeSave() {
        try {
            this.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIfNotExist(String path, Object value) {
        if (!this.contains(path) && value != null) {
            this.set(path, value);
            this.safeSave();
        }
    }

    @Override
    public int getInt(String path, int defaultValue) {
        this.setIfNotExist(path, defaultValue);
        return super.getInt(path, defaultValue);
    }

    @Override
    public String getString(String path, String defaultValue) {
        this.setIfNotExist(path, defaultValue);
        return super.getString(path, defaultValue);
    }

    @Override
    public boolean getBoolean(String path, boolean defaultValue) {
        this.setIfNotExist(path, defaultValue);
        return super.getBoolean(path, defaultValue);
    }

    public List<String> getStringList(String path, List<String> defaultValue) {
        this.setIfNotExist(path, defaultValue);
        return super.getStringList(path);
    }

    private Sound getSound(String key) {
        String name = this.getString(key);

        for ( Sound sound : Sound.values()) {
            if (name.equalsIgnoreCase(sound.name())) {
                return sound;
            }
        }

        Bukkit.getLogger().warning("Couldn't load sound '" + name + "' from configuration file! (Invalid name?)");
        return null;
    }

    public Sound getSound(String key, String defaultValue) {
        this.setIfNotExist(key, defaultValue);
        return this.getSound(key);
    }

    public Sound getSound(String key, Sound defaultValue) {
        return this.getSound(key, defaultValue.toString());
    }

    public Location getLocation(String key, boolean getWorld) {
        World world = getWorld ? Bukkit.getWorld(this.getString(key + ".world")) : null;
        double x = this.getDouble(key + ".x");
        double y = this.getDouble(key + ".y");
        double z = this.getDouble(key + ".z");
        float pitch = (float) this.getDouble(key + ".pitch");
        float yaw = (float) this.getDouble(key + ".yaw");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public Cuboid getCuboid(String key) {
        return new Cuboid(getLocation(new StringJoiner(".").add(key).add("first_point").toString()),
                getLocation(new StringJoiner(".").add(key).add("second_point").toString()));
    }

    public Location getLocation(String key) {
        return this.getLocation(key, true);
    }

    public void setLocation(String key, Location location, boolean includeWorld) {
        if (includeWorld) {
            this.set(key + ".world", location.getWorld().getName());
        }

        this.set(key + ".x", location.getX());
        this.set(key + ".y", location.getY());
        this.set(key + ".z", location.getZ());
        this.set(key + ".pitch", location.getPitch());
        this.set(key + ".yaw", location.getYaw());
    }

    public void setLocation(String key, Location location) {
        this.setLocation(key, location, true);
    }



}
