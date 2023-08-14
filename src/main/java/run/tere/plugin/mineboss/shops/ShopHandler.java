package run.tere.plugin.mineboss.shops;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import run.tere.plugin.mineboss.MineBoss;
import run.tere.plugin.mineboss.shops.consts.ShopItem;
import run.tere.plugin.mineboss.utils.ChatUtil;
import run.tere.plugin.mineboss.utils.InventoryUtil;
import run.tere.plugin.mineboss.utils.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

public class ShopHandler {
    private ShopInventory shopInventory;
    private static List<ShopItem> shopItems;

    static {
        shopItems = new ArrayList<>();
        shopItems.add(new ShopItem("RevivalPowder", ItemStackUtil.createRevivalPowder(), 5000));
    }

    public ShopHandler() {
        this.shopInventory = new ShopInventory();
    }

    public ShopInventory getShopInventory() {
        return shopInventory;
    }

    public void buyItem(Player player, String shopItemName) {
        ShopItem shopItem = getShopItemFromName(shopItemName);
        Economy economy = MineBoss.getPlugin().getEconomy();
        if (!economy.hasAccount(player)) {
            ChatUtil.sendMessage(player, "§c経済アカウントを持っていないため購入出来ません");
            InventoryUtil.closeInventory(player);
            return;
        }
        if (shopItem == null) {
            ChatUtil.sendMessage(player, "§c商品がエラーで取得できません");
            InventoryUtil.closeInventory(player);
            return;
        }
        ItemStack itemStack = shopItem.getItemStack();
        double price = shopItem.getPrice();
        if (!economy.has(player, price)) {
            ChatUtil.sendMessage(player, "§c料金が足りません");
            InventoryUtil.closeInventory(player);
            return;
        }
        InventoryUtil.addItem(player, itemStack);
        economy.withdrawPlayer(player, price);
        ChatUtil.sendMessage(player, "§a購入しました!");
        InventoryUtil.closeInventory(player);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1.5F);
    }

    public static List<ShopItem> getShopItems() {
        return shopItems;
    }

    public ShopItem getShopItemFromName(String name) {
        for (ShopItem shopItem : shopItems) {
            if (shopItem.getName().equalsIgnoreCase(name)) {
                return shopItem;
            }
        }
        return null;
    }
}
