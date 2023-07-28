package datrat.takethis;

import datrat.takethis.event.TakeThisItem;
import datrat.takethis.handler.Handler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static datrat.takethis.config.SealConfig.loadConfig;

public final class TakeThis extends JavaPlugin {

    public static TakeThis instance;

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {

        instance = this;

        loadConfig();
        getLogger().info("Take This has loaded Seal Config!");

        Bukkit.getServer().getPluginManager().registerEvents(new TakeThisItem(), this);
        getLogger().info("Take This has registered events!");
        getLogger().warning("Take This has fully initialized!");

    }

    @Override
    public void onDisable() {}

}
