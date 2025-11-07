package datrat.takethis.config;

import com.focamacho.sealconfig.annotation.ConfigCategory;
import com.focamacho.sealconfig.relocated.blue.endless.jankson.api.annotation.Comment;

public class SealConfigObject {

    @Comment("Toggle if the player that is receiving\n" +
             "the item needs to be sneaking as well.\n" +
             "[ Boolean ( true | false ) ]\n"+
             "[ Default: false ]")
    public Boolean receiverSneaking = false;

    @Comment("Toggle if item names should be formatted nicely.\n" +
             "When true: 'GRASS_BLOCK' becomes 'Grass Block'\n" +
             "When false: Shows raw material name like 'GRASS_BLOCK'\n" +
             "[ Boolean ( true | false ) ]\n"+
             "[ Default: true ]")
    public Boolean useFormattedItemNames = true;

    @Comment("Category to tweak the cooldown system.")
    public CooldownCategory cooldown = new CooldownCategory();

    @ConfigCategory
    public static class CooldownCategory {

        @Comment("Toggle if the cooldown system should\n" +
                 "be active or not.\n" +
                 "[ Boolean ( true | false ) ]\n"+
                 "[ Default: true ]")
        public Boolean isCooldownActive = true;

        @Comment("Change the value (in seconds)\n" +
                 "of the cooldown.\n" +
                 "[ int ( any integral number ) ]\n"+
                 "[ Default: 3 ]")
        public int cooldownValue = 3;

    }

    @Comment("Category to tweak or translate the command messages.")
    public CommandMessagesCategory commandMessages = new CommandMessagesCategory();

    @ConfigCategory
    public static class CommandMessagesCategory {

        @Comment("Message shown when command usage is incorrect.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Default: Usage: /takethis (reload|opt) ]")
        public String usage = "Usage: /takethis (reload|opt)";

        @Comment("Color for the 'usage' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: YELLOW ]")
        public String usageColor = "YELLOW";

        @Comment("Message shown when reload starts.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Default: Reloading Take This' Variables... ]")
        public String reloadStart = "Reloading Take This' Variables...";

        @Comment("Color for the 'reloadStart' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: GREEN ]")
        public String reloadStartColor = "GREEN";

        @Comment("Message shown when reload completes successfully.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Default: Reload complete! ]")
        public String reloadComplete = "Reload complete!";

        @Comment("Color for the 'reloadComplete' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: GREEN ]")
        public String reloadCompleteColor = "GREEN";

        @Comment("Message shown when console tries to use player-only command.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Default: This command can only be used by players. ]")
        public String consoleNotAllowed = "This command can only be used by players.";

        @Comment("Color for the 'consoleNotAllowed' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: RED ]")
        public String consoleNotAllowedColor = "RED";

        @Comment("Message shown when player lacks permission.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Default: You don't have permission to use this command. ]")
        public String noPermission = "You don't have permission to use this command.";

        @Comment("Color for the 'noPermission' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: RED ]")
        public String noPermissionColor = "RED";

        @Comment("Message shown when player opts in.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Default: You have now opted-in to the item exchange system. ]")
        public String optedIn = "You have now opted-in to the item exchange system.";

        @Comment("Color for the 'optedIn' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: GREEN ]")
        public String optedInColor = "GREEN";

        @Comment("Message shown when player opts out.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Default: You have now opted-out of the item exchange system. ]")
        public String optedOut = "You have now opted-out of the item exchange system.";

        @Comment("Color for the 'optedOut' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: RED ]")
        public String optedOutColor = "RED";

        @Comment("Prefix for all command messages.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Default: [Take This] ]")
        public String prefix = "[Take This]";

        @Comment("Color for the message prefix.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: WHITE ]")
        public String prefixColor = "WHITE";

    }

    @Comment("Category to tweak or translate the chat messages.")
    public ChatMessagesCategory chatMessages = new ChatMessagesCategory();

    @ConfigCategory
    public static class ChatMessagesCategory {

        @Comment("This is the message that will appear for\n" +
                 "sender if the receiver's inventory is full.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ ${sender} ${receiver} ${quantity} ${item_type} ]\n" +
                 "[ Default: Their inventory is full! ]")
        public String theirInventoryIsFull = "Their inventory is full!";

        @Comment("Color for the 'theirInventoryIsFull' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: RED ]")
        public String theirInventoryIsFullColor = "RED";

        @Comment("This is the message that will appear if\n" +
                "sender has opted-out and does not want to\n" +
                "be bothered by Take This.\n" +
                "[ String ( any phrase ) ]\n" +
                "[ Supports placeholders: ]\n" +
                "[ ${sender} ${receiver} ]\n" +
                "[ Default: They are not accepting items! ]")
        public String theyOptedOut = "They are not accepting items!";

        @Comment("Color for the 'theyOptedOut' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: RED ]")
        public String theyOptedOutColor = "RED";

        @Comment("This is the message that will appear for\n" +
                 "receiver if their inventory is full.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ ${sender} ${receiver} ${quantity} ${item_type} ]\n" +
                 "[ Default: ${sender} just tried to give you an item, but your inventory is full! ]")
        public String yourInventoryIsFull = "${sender} just tried to give you an item, but your inventory is full!";

        @Comment("Color for the 'yourInventoryIsFull' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: RED ]")
        public String yourInventoryIsFullColor = "RED";

        @Comment("This is the message that will appear for\n" +
                 "sender when receiver successfully takes their\n" +
                 "item\n"+
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ ${sender} ${receiver} ${quantity} ${item_type} ]\n" +
                 "[ Default: You have given ${quantity} ${item_type} to ${receiver}! ]")
        public String senderGivingTheItem = "You have given ${quantity} ${item_type} to ${receiver}!";

        @Comment("Color for the 'senderGivingTheItem' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: LIGHT_PURPLE ]")
        public String senderGivingTheItemColor = "LIGHT_PURPLE";

        @Comment("This is the message that will appear for\n" +
                 "receiver when sender successfully gives them\n" +
                 "an item\n"+
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ ${sender} ${receiver} ${quantity} ${item_type} ]\n" +
                 "[ Default: ${sender} just gave you ${quantity} ${item_type}! ]")
        public String receiverTakingTheItem = "${sender} just gave you ${quantity} ${item_type}!";

        @Comment("Color for the 'receiverTakingTheItem' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: LIGHT_PURPLE ]")
        public String receiverTakingTheItemColor = "LIGHT_PURPLE";

        @Comment("This is the message that will appear if\n" +
                 "sender is in cooldown.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ {cooldown} ]\n" +
                 "[ Default: Wait ${cooldown}s before you can give another item! ]")
        public String cooldownMessage = "Wait ${cooldown}s before you can give another item!";

        @Comment("Color for the 'cooldownMessage' message.\n" +
                 "[ String ( Minecraft color code ) ]\n" +
                 "[ Options: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE ]\n" +
                 "[ Default: RED ]")
        public String cooldownMessageColor = "RED";

        @Comment("Toggle if the console logging\n" +
                 "system should be active or not.\n" +
                 "[ Boolean ( true | false ) ]\n"+
                 "[ Default: true ]")
        public Boolean shouldConsoleLog = true;

        @Comment("This is the message that the console will log.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ ${sender} ${receiver} ${quantity} ${item_type} ]\n" +
                 "[ Default: ${sender} gave ${quantity} ${item_type} for ${receiver}. ]")
        public String consoleLog = "${sender} gave ${quantity} ${item_type} for ${receiver}.";

    }
}
