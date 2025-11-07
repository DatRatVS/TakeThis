package datrat.takethis.command;

import datrat.takethis.TakeThis;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static datrat.takethis.config.SealConfig.config;
import static datrat.takethis.config.SealConfig.reloadConfig;

public class ReloadCommand implements CommandExecutor {

    private final TakeThis plugin;

    public ReloadCommand(TakeThis plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("takethis")) {
            return false;
        }

        if (args.length == 0) {
            sendMessage(sender, config.commandMessages.usage, config.commandMessages.usageColor);
            return true;
        }

        if ("reload".equalsIgnoreCase(args[0])) {
            return handleReload(sender);
        }

        if ("opt".equalsIgnoreCase(args[0])) {
            return handleOpt(sender);
        }

        sendMessage(sender, config.commandMessages.usage, config.commandMessages.usageColor);
        return true;
    }

    private boolean handleReload(CommandSender sender) {
        if (sender instanceof Player && !sender.hasPermission("takethis.reload")) {
            sendMessage(sender, config.commandMessages.noPermission, config.commandMessages.noPermissionColor);
            return true;
        }

        sendMessage(sender, config.commandMessages.reloadStart, config.commandMessages.reloadStartColor);
        reloadConfig();
        sendMessage(sender, config.commandMessages.reloadComplete, config.commandMessages.reloadCompleteColor);
        return true;
    }

    private boolean handleOpt(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sendMessage(sender, config.commandMessages.consoleNotAllowed, config.commandMessages.consoleNotAllowedColor);
            return true;
        }

        Player player = (Player) sender;
        UUID playerId = player.getUniqueId();

        if (plugin.hasOptOutRecord(playerId) && plugin.isOptedOut(playerId)) {
            plugin.setOptOut(playerId, false);
            sendMessage(player, config.commandMessages.optedIn, config.commandMessages.optedInColor);
        } else {
            plugin.setOptOut(playerId, true);
            sendMessage(player, config.commandMessages.optedOut, config.commandMessages.optedOutColor);
        }
        return true;
    }

    private void sendMessage(CommandSender sender, String message, String colorName) {
        ChatColor prefixColor = parseColor(config.commandMessages.prefixColor);
        ChatColor messageColor = parseColor(colorName);
        
        sender.sendMessage(prefixColor + config.commandMessages.prefix + " " + messageColor + message);
    }

    private ChatColor parseColor(String colorName) {
        if (colorName == null) {
            return ChatColor.WHITE;
        }
        try {
            return ChatColor.valueOf(colorName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ChatColor.WHITE;
        }
    }
}
