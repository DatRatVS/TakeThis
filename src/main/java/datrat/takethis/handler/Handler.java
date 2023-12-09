package datrat.takethis.handler;

import org.apache.commons.text.StringSubstitutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static datrat.takethis.config.SealConfig.config;

public class Handler {

    public String treatedMessage;

    public String placeholderHandler(Player player, Player rcplayer, ItemStack item, String placeholderMessage) {

        Map<String, String> data = new HashMap<String, String>();
        data.put("sender", player.getName());
        data.put("receiver", rcplayer.getName());
        data.put("quantity", String.valueOf(item.getAmount()));
        data.put("item_type", String.valueOf(item.getType()));

        data.put("cooldown", "WORK IN PROGRESS.");

        treatedMessage = StringSubstitutor.replace(placeholderMessage, data);

        return treatedMessage;

    }

    public String handler(Player player, Player rcplayer, ItemStack item, String message) {

        switch (message) {
            case "theirInventoryIsFull": return placeholderHandler(player, rcplayer, item, config.chatMessages.theirInventoryIsFull);
            case "theyOptedOut": return placeholderHandler(player, rcplayer, item, config.chatMessages.theyOptedOut);
            case "yourInventoryIsFull": return placeholderHandler(player, rcplayer, item, config.chatMessages.yourInventoryIsFull);
            case "senderGivingTheItem": return placeholderHandler(player, rcplayer, item, config.chatMessages.senderGivingTheItem);
            case "receiverTakingTheItem": return placeholderHandler(player, rcplayer, item, config.chatMessages.receiverTakingTheItem);
            case "cooldownMessage": return placeholderHandler(player, rcplayer, item, config.chatMessages.cooldownMessage);
            case "consoleLog": return placeholderHandler(player, rcplayer, item, config.chatMessages.consoleLog);
        }

        return null;

    }

}
