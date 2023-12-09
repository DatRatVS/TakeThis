package datrat.takethis.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static datrat.takethis.config.SealConfig.reloadConfig;
import static datrat.takethis.TakeThis.isOptedOut;

public class ReloadCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("takethis")) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender instanceof Player) if (!(sender.hasPermission("takethis.reload"))) return true;

                sender.sendMessage("[Take This] - Reloading Take This' Variables.");

                reloadConfig();

                sender.sendMessage("[Take This] - Reload done.");
                return true;

            } else if (args[0].equalsIgnoreCase("opt")) {

                if (!(sender instanceof Player)) {
                    sender.sendMessage("Consoles aren't able to use this command.");
                    return true;
                }

                Player player = (Player) sender;

                if (isOptedOut.containsKey(player.getUniqueId().toString()) && isOptedOut.get(player.getUniqueId().toString())) {
                    isOptedOut.replace(player.getUniqueId().toString(), false);
                    player.sendMessage("[Take This] - You have now opted-in.");
                    return true;
                } else if (isOptedOut.containsKey(player.getUniqueId().toString()) && !isOptedOut.get(player.getUniqueId().toString())) {
                    isOptedOut.replace(player.getUniqueId().toString(), true);
                    player.sendMessage("[Take This] - You have now opted-out.");
                    return true;
                }

                isOptedOut.put(player.getUniqueId().toString(), true);
                player.sendMessage("[Take This] - You have now opted-out of the item exchanging system.");
                return true;

            } else {
                sender.sendMessage("[Take This] - Usage: /takethis (subcommand)");
            }
            return true;
        }
        return true;
    }

}
