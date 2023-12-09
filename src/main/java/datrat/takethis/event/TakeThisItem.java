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
import static datrat.takethis.TakeThis.isOptedOut;
import static datrat.takethis.TakeThis.cooldown ;
import static org.bukkit.Bukkit.getLogger;
import static datrat.takethis.config.SealConfig.config;

public class TakeThisItem implements Listener {

    Handler messageHandler = new Handler();

    @EventHandler
    public void passingItem(PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof Player)) return;
        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) return;

        Player player = event.getPlayer();

        if (isOptedOut.containsKey(player.getUniqueId().toString()) && isOptedOut.get(player.getUniqueId().toString())) return;
        if (!(player.isSneaking())) return;

        Player rcplayer = (Player) event.getRightClicked();

        if (config.receiverSneaking) if (!(rcplayer.isSneaking())) return;

        Bukkit.getScheduler().callSyncMethod(instance, () -> {

            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType() == Material.AIR) return true;

            if (config.cooldown.isCooldownActive) {

                if (cooldown.containsKey(player.getUniqueId().toString()) && cooldown.get(player.getUniqueId().toString()) > System.currentTimeMillis()) {
                    long timeLeft = (cooldown.get(player.getUniqueId().toString()) - System.currentTimeMillis()) / 1000;
                    player.sendMessage(ChatColor.RED + messageHandler.handler(player, rcplayer, item, "cooldownMessage", timeLeft));
                    return true;
                }

                cooldown.put(player.getUniqueId().toString(), System.currentTimeMillis() + (config.cooldown.cooldownValue * 1000L));

            }

            if (isOptedOut.containsKey(rcplayer.getUniqueId().toString()) && isOptedOut.get(rcplayer.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + messageHandler.handler(player, rcplayer, item, "theyOptedOut", 0));
                return true;
            }

            if (rcplayer.getInventory().firstEmpty() == -1) {
                player.sendMessage(ChatColor.RED + messageHandler.handler(player, rcplayer, item, "theirInventoryIsFull", 0));
                rcplayer.sendMessage(ChatColor.RED + messageHandler.handler(player, rcplayer, item, "yourInventoryIsFull", 0));
                return true;
            }

            player.getInventory().setItemInMainHand(null);
            rcplayer.getInventory().addItem(item);

            player.sendMessage(ChatColor.LIGHT_PURPLE + messageHandler.handler(player, rcplayer, item, "senderGivingTheItem", 0));
            rcplayer.sendMessage(ChatColor.LIGHT_PURPLE + messageHandler.handler(player, rcplayer, item, "receiverTakingTheItem", 0));

            if (config.chatMessages.shouldConsoleLog) {
                getLogger().info("[TakeThis] " + messageHandler.handler(player, rcplayer, item, "consoleLog", 0));
            }

            return true;
        });
    }

}