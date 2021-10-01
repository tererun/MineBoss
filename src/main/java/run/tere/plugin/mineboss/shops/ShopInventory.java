package run.tere.plugin.mineboss.shops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import run.tere.api.custominventory.consts.CustomInventory;
import run.tere.api.custominventory.consts.CustomInventoryHolder;
import run.tere.api.custominventory.consts.clicks.CustomItemClickEvent;
import run.tere.api.custominventory.consts.clicks.CustomItemClickTag;
import run.tere.api.custominventory.consts.patterns.CustomItemContents;
import run.tere.api.custominventory.consts.patterns.CustomItemPattern;
import run.tere.api.custominventory.listeners.CustomInventoryListener;
import run.tere.plugin.mineboss.MineBoss;
import run.tere.plugin.mineboss.utils.ItemStackUtil;

import java.util.Collections;

public class ShopInventory {

    public ShopInventory() {
        Bukkit.getPluginManager().registerEvents(new CustomInventoryListener(), MineBoss.getPlugin());
    }

    public Inventory createInventory() {
        CustomInventoryHolder customInventoryHolder = new CustomInventoryHolder(
                new CustomInventory("§7MineBoss Shop", 27),
                new CustomItemClickEvent(
                    new CustomItemClickTag("RevivalPowder", true, inventoryClickEvent -> {
                        Player player = (Player) inventoryClickEvent.getWhoClicked();
                        MineBoss.getPlugin().getShopHandler().buyItem(player, "RevivalPowder");
                    })
                ),
                new CustomItemContents(
                        new CustomItemPattern('S', ItemStackUtil.getItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, " ", Collections.singletonList(" "))),
                        new CustomItemPattern('E', ItemStackUtil.getItemStackDescribedPrice(ItemStackUtil.getRevivalPowder(), 5000)),
                        new CustomItemPattern('A', new ItemStack(Material.AIR))
                ),
                "SSSSSSSSS",
                "AEAAAAAAA",
                "SSSSSSSSS"
        );
        return customInventoryHolder.getInventory();
    }
}
