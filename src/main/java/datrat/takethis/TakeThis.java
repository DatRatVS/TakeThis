package datrat.takethis;

import datrat.takethis.command.ReloadCommand;
import datrat.takethis.command.ReloadCommandTabCompleter;
import datrat.takethis.event.TakeThisItem;
import datrat.takethis.util.VersionSupport;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static datrat.takethis.config.SealConfig.loadConfig;

public final class TakeThis extends JavaPlugin {

    private static TakeThis instance;

    private final Map<UUID, Boolean> optOutStates = new ConcurrentHashMap<>();
    private final Map<UUID, Long> cooldowns = new ConcurrentHashMap<>();

    public static TakeThis getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        VersionSupport.logCompatibility(this);

        loadConfig();
        getLogger().info("Take This has loaded Seal Config!");

        registerEventListeners();
        registerCommands();

        this.saveDefaultConfig();
        restoreOptOutStates();

        getLogger().warning("Take This has fully initialized!");
    }

    @Override
    public void onDisable() {
        if (!optOutStates.isEmpty()) {
            saveOptOutStates();
        }
    }

    private void registerEventListeners() {
        getServer().getPluginManager().registerEvents(new TakeThisItem(this), this);
        getLogger().info("Take This has registered events!");
    }

    private void registerCommands() {
        PluginCommand takethis = getCommand("takethis");
        if (takethis == null) {
            getLogger().severe("Unable to register 'takethis' command. Check plugin.yml configuration.");
            return;
        }

        takethis.setExecutor(new ReloadCommand(this));
        takethis.setTabCompleter(new ReloadCommandTabCompleter());
        getLogger().info("Take This has registered commands!");
    }

    public void saveOptOutStates() {
        getConfig().set("data", null);

        if (optOutStates.isEmpty()) {
            saveConfig();
            return;
        }

        optOutStates.forEach((uuid, optedOut) -> getConfig().set("data." + uuid, optedOut));
        saveConfig();
    }

    private void restoreOptOutStates() {
        ConfigurationSection dataSection = getConfig().getConfigurationSection("data");
        if (dataSection == null) {
            getLogger().info("Take This has no opted-in and out data to restore.");
            return;
        }

        optOutStates.clear();
        for (String key : dataSection.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(key);
                boolean optedOut = dataSection.getBoolean(key);
                optOutStates.put(uuid, optedOut);
            } catch (IllegalArgumentException ex) {
                getLogger().warning("Failed to parse UUID '" + key + "' while restoring opt-out data.");
            }
        }

        getConfig().set("data", null);
        saveConfig();
        getLogger().info("Take This has restored opted-in and outs!");
    }

    public boolean hasOptOutRecord(UUID uuid) {
        return optOutStates.containsKey(uuid);
    }

    public boolean isOptedOut(UUID uuid) {
        return optOutStates.getOrDefault(uuid, false);
    }

    public void setOptOut(UUID uuid, boolean optedOut) {
        optOutStates.put(uuid, optedOut);
    }

    public long getCooldownExpiry(UUID uuid) {
        return cooldowns.getOrDefault(uuid, 0L);
    }

    public void setCooldownExpiry(UUID uuid, long expiryTimestamp) {
        cooldowns.put(uuid, expiryTimestamp);
    }

    public void clearCooldown(UUID uuid) {
        cooldowns.remove(uuid);
    }
}
