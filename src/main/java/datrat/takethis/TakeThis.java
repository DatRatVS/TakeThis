package datrat.takethis;

import datrat.takethis.command.ReloadCommand;
import datrat.takethis.command.ReloadCommandTabCompleter;
import datrat.takethis.event.TakeThisItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static datrat.takethis.config.SealConfig.loadConfig;

public final class TakeThis extends JavaPlugin {

    public static TakeThis instance;

    public static HashMap<String, Boolean> isOptedOut = new HashMap<>();

    @Override
    public void onEnable() {

        instance = this;

        loadConfig();
        getLogger().info("Take This has loaded Seal Config!");

        Bukkit.getServer().getPluginManager().registerEvents(new TakeThisItem(), this);
        getLogger().info("Take This has registered events!");

        Objects.requireNonNull(this.getCommand("takethis")).setExecutor(new ReloadCommand());
        Objects.requireNonNull(this.getCommand("takethis")).setTabCompleter(new ReloadCommandTabCompleter());
        getLogger().info("Take This has registered commands!");

        this.saveDefaultConfig();
        if (this.getConfig().contains("data")) {
            this.restoreHashMap();
            this.getConfig().set("data", null);
            this.saveConfig();
        }
        getLogger().info("Take This has gotten opted-in and outs!");

        getLogger().warning("Take This has fully initialized!");
    }

    @Override
    public void onDisable() {
        if (!isOptedOut.isEmpty()) this.saveHashMap();
    }

    public void saveHashMap() {
        for (Map.Entry<String, Boolean> entry : isOptedOut.entrySet()) {
            this.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    public void restoreHashMap() {
        Objects.requireNonNull(this.getConfig().getConfigurationSection("data")).getKeys(false).forEach(key -> {
            isOptedOut.put(key, (Boolean) this.getConfig().get("data." + key));
        });
    }
}

