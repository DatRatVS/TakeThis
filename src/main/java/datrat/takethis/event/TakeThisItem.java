package datrat.takethis.event;

import datrat.takethis.TakeThis;
import datrat.takethis.handler.Handler;
import datrat.takethis.handler.Handler.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

import static datrat.takethis.config.SealConfig.config;

public class TakeThisItem implements Listener {

    private final TakeThis plugin;
    private final Handler messageHandler = new Handler();

    public TakeThisItem(TakeThis plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void passingItem(PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof Player)) return;
        if (event.getHand() == EquipmentSlot.OFF_HAND) return;

        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (plugin.isOptedOut(playerId)) return;
        if (!player.isSneaking()) return;

        Player rcplayer = (Player) event.getRightClicked();
        UUID receiverId = rcplayer.getUniqueId();

        if (config.receiverSneaking && !rcplayer.isSneaking()) return;

        Bukkit.getScheduler().runTask(plugin, () -> {

            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType() == Material.AIR) return;

            if (config.cooldown.isCooldownActive) {

                long cooldownExpiry = plugin.getCooldownExpiry(playerId);
                long now = System.currentTimeMillis();
                if (cooldownExpiry > now) {
                    long timeLeft = (cooldownExpiry - now) / 1000;
                    player.sendMessage(messageHandler.format(player, rcplayer, item, MessageType.COOLDOWN_MESSAGE, timeLeft));
                    return;
                }

                plugin.setCooldownExpiry(playerId, now + (config.cooldown.cooldownValue * 1000L));

            }

            if (plugin.isOptedOut(receiverId)) {
                player.sendMessage(messageHandler.format(player, rcplayer, item, MessageType.THEY_OPTED_OUT, 0));
                return;
            }

            if (rcplayer.getInventory().firstEmpty() == -1) {
                player.sendMessage(messageHandler.format(player, rcplayer, item, MessageType.THEIR_INVENTORY_IS_FULL, 0));
                rcplayer.sendMessage(messageHandler.format(player, rcplayer, item, MessageType.YOUR_INVENTORY_IS_FULL, 0));
                return;
            }

            player.getInventory().setItemInMainHand(null);
            rcplayer.getInventory().addItem(item);

            player.sendMessage(messageHandler.format(player, rcplayer, item, MessageType.SENDER_GIVING_THE_ITEM, 0));
            rcplayer.sendMessage(messageHandler.format(player, rcplayer, item, MessageType.RECEIVER_TAKING_THE_ITEM, 0));

            if (config.chatMessages.shouldConsoleLog) {
                plugin.getLogger().info("[TakeThis] " + messageHandler.format(player, rcplayer, item, MessageType.CONSOLE_LOG, 0));
            }

        });
    }

}