package run.tere.plugin.mineboss.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import run.tere.plugin.mineboss.MineBoss;

public class InventoryUtil {
    public static void closeInventory(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.closeInventory();
            }
        }.runTask(MineBoss.getPlugin());
    }

    public static void addItem(Player player, ItemStack itemStack) {
        ItemStack clonedItemStack = itemStack.clone();
        Inventory inventory = player.getInventory();
        if (inventory.firstEmpty() == -1) {
            ChatUtil.sendMessage(player, "§aインベントリの空きがない為地面にドロップします");
            player.getWorld().dropItem(player.getLocation(), clonedItemStack);
        } else {
            inventory.addItem(clonedItemStack);
        }
    }
}
