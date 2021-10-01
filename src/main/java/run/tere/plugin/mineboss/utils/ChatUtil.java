package run.tere.plugin.mineboss.utils;

import org.bukkit.command.CommandSender;

public class ChatUtil {
    public static String getPrefix() {
        return "§f[§7MineBoss§f] ";
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(getPrefix() + message);
    }
}
