package datrat.takethis.handler;

import org.apache.commons.text.StringSubstitutor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static datrat.takethis.config.SealConfig.config;

public class Handler {

    public enum MessageType {
        THEIR_INVENTORY_IS_FULL(
                () -> config.chatMessages.theirInventoryIsFull,
                () -> config.chatMessages.theirInventoryIsFullColor
        ),
        THEY_OPTED_OUT(
                () -> config.chatMessages.theyOptedOut,
                () -> config.chatMessages.theyOptedOutColor
        ),
        YOUR_INVENTORY_IS_FULL(
                () -> config.chatMessages.yourInventoryIsFull,
                () -> config.chatMessages.yourInventoryIsFullColor
        ),
        SENDER_GIVING_THE_ITEM(
                () -> config.chatMessages.senderGivingTheItem,
                () -> config.chatMessages.senderGivingTheItemColor
        ),
        RECEIVER_TAKING_THE_ITEM(
                () -> config.chatMessages.receiverTakingTheItem,
                () -> config.chatMessages.receiverTakingTheItemColor
        ),
        COOLDOWN_MESSAGE(
                () -> config.chatMessages.cooldownMessage,
                () -> config.chatMessages.cooldownMessageColor
        ),
        CONSOLE_LOG(
                () -> config.chatMessages.consoleLog,
                () -> null
        );

        private final Supplier<String> templateSupplier;
        private final Supplier<String> colorSupplier;

        MessageType(Supplier<String> templateSupplier, Supplier<String> colorSupplier) {
            this.templateSupplier = templateSupplier;
            this.colorSupplier = colorSupplier;
        }

        public String template() {
            return templateSupplier.get();
        }

        public ChatColor color() {
            String colorName = colorSupplier.get();
            if (colorName == null) {
                return null;
            }
            try {
                return ChatColor.valueOf(colorName.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ChatColor.WHITE;
            }
        }
    }

    public String format(Player player, Player receiver, ItemStack item, MessageType type, long cooldownLeft) {
        Objects.requireNonNull(type, "Message type cannot be null");
        String message = applyPlaceholders(player, receiver, item, type.template(), cooldownLeft);
        
        ChatColor color = type.color();
        if (color != null) {
            return color + message;
        }
        return message;
    }

    private String applyPlaceholders(Player player, Player receiver, ItemStack item, String template, long cooldownLeft) {
        if (template == null) {
            return "";
        }

        Objects.requireNonNull(player, "Player cannot be null");
        Objects.requireNonNull(receiver, "Receiver cannot be null");
        Objects.requireNonNull(item, "Item cannot be null");

        String itemName = config.useFormattedItemNames 
                ? formatItemName(item.getType().name()) 
                : item.getType().name();

        Map<String, String> data = new HashMap<>();
        data.put("sender", player.getName());
        data.put("receiver", receiver.getName());
        data.put("quantity", String.valueOf(item.getAmount()));
        data.put("item_type", itemName);
        data.put("cooldown", String.valueOf(cooldownLeft));

        return StringSubstitutor.replace(template, data);
    }

    private String formatItemName(String materialName) {
        if (materialName == null || materialName.isEmpty()) {
            return materialName;
        }

        String[] words = materialName.toLowerCase().split("_");
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                formatted.append(" ");
            }
            String word = words[i];
            if (!word.isEmpty()) {
                formatted.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    formatted.append(word.substring(1));
                }
            }
        }

        return formatted.toString();
    }
}
