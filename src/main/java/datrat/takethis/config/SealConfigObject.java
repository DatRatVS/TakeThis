package datrat.takethis.config;

import com.focamacho.sealconfig.annotation.ConfigCategory;
import com.focamacho.sealconfig.relocated.blue.endless.jankson.api.annotation.Comment;

public class SealConfigObject {

    @Comment("Toggle if the player that is receiving\n" +
             "the item needs to be sneaking as well.\n" +
             "[ Boolean ( true | false ) ]\n"+
             "[ Default: false ]")
    public Boolean receiverSneaking = false;

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

        @Comment("This is the message that will appear if\n" +
                "sender has opted-out and does not want to\n" +
                "be bothered by Take This.\n" +
                "[ String ( any phrase ) ]\n" +
                "[ Supports placeholders: ]\n" +
                "[ ${sender} ${receiver} ]\n" +
                "[ Default: They are not accepting items! ]")
        public String theyOptedOut = "They are not accepting items!";

        @Comment("This is the message that will appear for\n" +
                 "receiver if their inventory is full.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ ${sender} ${receiver} ${quantity} ${item_type} ]\n" +
                 "[ Default: ${sender} just tried to give you an item, but your inventory is full! ]")
        public String yourInventoryIsFull = "${sender} just tried to give you an item, but your inventory is full!";

        @Comment("This is the message that will appear for\n" +
                 "sender when receiver successfully takes their\n" +
                 "item\n"+
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ ${sender} ${receiver} ${quantity} ${item_type} ]\n" +
                 "[ Default: You have given ${quantity} ${item_type} to ${receiver}! ]")
        public String senderGivingTheItem = "You have given ${quantity} ${item_type} to ${receiver}!";

        @Comment("This is the message that will appear for\n" +
                 "receiver when sender successfully gives them\n" +
                 "an item\n"+
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ ${sender} ${receiver} ${quantity} ${item_type} ]\n" +
                 "[ Default: ${sender} just gave you ${quantity} ${item_type}! ]")
        public String receiverTakingTheItem = "${sender} just gave you ${quantity} ${item_type}!";

        @Comment("This is the message that will appear if\n" +
                 "sender is in cooldown.\n" +
                 "[ String ( any phrase ) ]\n" +
                 "[ Supports placeholders: ]\n" +
                 "[ {cooldown} ]\n" +
                 "[ Default: Wait ${cooldown}s before you can give another item! ]")
        public String cooldownMessage = "Wait ${cooldown}s before you can give another item!";


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
