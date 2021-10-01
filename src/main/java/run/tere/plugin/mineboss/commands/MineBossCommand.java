package run.tere.plugin.mineboss.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import run.tere.plugin.mineboss.MineBoss;
import run.tere.plugin.mineboss.utils.ChatUtil;

public class MineBossCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("mineboss")) return true;
        if (!(sender instanceof Player)) {
            ChatUtil.sendMessage(sender, "§4このコマンドはプレイヤーからのみ実行可能です");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            Inventory inventory = MineBoss.getPlugin().getShopHandler().getShopInventory().createInventory();
            player.openInventory(inventory);
        }
        return true;
    }
}
