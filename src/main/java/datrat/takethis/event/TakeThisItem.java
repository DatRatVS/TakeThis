package datrat.takethis.event;

import datrat.takethis.handler.Handler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import static datrat.takethis.TakeThis.instance;
import static org.bukkit.Bukkit.getLogger;
import static datrat.takethis.config.SealConfig.config;

public class TakeThisItem implements Listener {

    Handler messageHandler = new Handler();

    @EventHandler
    public void passingItem(PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof Player)) return;
        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) return;

        Player player = event.getPlayer();

        if (!(player.isSneaking())) return;

        Player rcplayer = (Player) event.getRightClicked();

        if (config.receiverSneaking) if (!(rcplayer.isSneaking())) return;

        Bukkit.getScheduler().callSyncMethod(instance, () -> {

            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType() == Material.AIR) { return true; }

            if (rcplayer.getInventory().firstEmpty() == -1) {
                player.sendMessage(ChatColor.RED + messageHandler.handler(player, rcplayer, item, "theirInventoryIsFull"));
                rcplayer.sendMessage(ChatColor.RED + messageHandler.handler(player, rcplayer, item, "yourInventoryIsFull"));
                return true;
            }

            player.getInventory().setItemInMainHand(null);
            rcplayer.getInventory().addItem(item);

            player.sendMessage(ChatColor.LIGHT_PURPLE + messageHandler.handler(player, rcplayer, item, "senderGivingTheItem"));
            rcplayer.sendMessage(ChatColor.LIGHT_PURPLE + messageHandler.handler(player, rcplayer, item, "receiverTakingTheItem"));

            if (config.chatMessages.shouldConsoleLog) {
                getLogger().info("[TakeThis] " + messageHandler.handler(player, rcplayer, item, "consoleLog"));
            }

            return true;
        });
    }

}