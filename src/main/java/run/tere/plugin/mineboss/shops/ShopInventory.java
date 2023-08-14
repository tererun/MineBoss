package run.tere.plugin.mineboss.shops;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import run.tere.framework.InventoryBuilder;
import run.tere.framework.models.ClickInventoryItem;
import run.tere.framework.models.InventoryItem;
import run.tere.framework.models.ItemRow;
import run.tere.plugin.mineboss.MineBoss;
import run.tere.plugin.mineboss.utils.ItemStackUtil;

import java.util.Collections;

public class ShopInventory {

    public Inventory createInventory() {
        return new InventoryBuilder("§7MineBoss Shop", 27)
                .addRow(new ItemRow("SSSSSSSSS"))
                .addRow(new ItemRow("AEAAAAAAA"))
                .addRow(new ItemRow("SSSSSSSSS"))
                .setItem('S', new InventoryItem(ItemStackUtil.createItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, " ", null)))
                .setItem('A', new InventoryItem(new ItemStack(Material.AIR)))
                .setItem('E', new ClickInventoryItem(
                        ItemStackUtil.createItemStackDescribedPrice(ItemStackUtil.createRevivalPowder(), 5000),
                        e -> {
                            e.setCancelled(true);
                            Player player = (Player) e.getWhoClicked();
                            MineBoss.getPlugin().getShopHandler().buyItem(player, "RevivalPowder");
                        }
                ))
                .build(MineBoss.getPlugin());
    }
}
