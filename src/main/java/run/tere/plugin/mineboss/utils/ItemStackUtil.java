package run.tere.plugin.mineboss.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import run.tere.plugin.mineboss.MineBoss;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStackUtil {

    public static final NamespacedKey MINE_BOSS_ITEM_KEY = new NamespacedKey(MineBoss.getPlugin(), "MineBossItem");

    public static ItemStack createItemStack(Material material, int amount, String displayName, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static String getMineBossItemKey(ItemStack itemStack) {
        if (itemStack == null) return null;
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        if (!dataContainer.has(MINE_BOSS_ITEM_KEY, PersistentDataType.STRING)) return null;
        return dataContainer.get(MINE_BOSS_ITEM_KEY, PersistentDataType.STRING);
    }

    public static ItemStack createKeyedItemStack(Material material, int amount, String displayName, List<String> lore, String key) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(MINE_BOSS_ITEM_KEY, PersistentDataType.STRING, key);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack createCustomItemStack(Material material, int amount, String displayName, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack createRevivalPowder() {
        return ItemStackUtil.createCustomItemStack(Material.GUNPOWDER, 1, "§eふっかつのもと", Arrays.asList(" §a§oクラフト素材", " §dエンダードラゴン§fなどの復活の材料"));
    }

    public static ItemStack createSpawnDragonEgg() {
        return ItemStackUtil.createKeyedItemStack(Material.DRAGON_EGG, 1, "§e孵化しそうなドラゴンのたまご", Arrays.asList("§a今すぐにでも§f孵化しそうなたまご", "§bエンド§fでのみ孵化可能"), "SpawnDragonEgg");
    }

    public static ItemStack createItemStackDescribedPrice(ItemStack itemStack, double price) {
        ItemStack changedItemStack = itemStack.clone();
        ItemMeta itemMeta = changedItemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        lore.add(ChatColor.of(Color.decode("#B1E693")) + " " + price + "FP");
        itemMeta.setLore(lore);
        changedItemStack.setItemMeta(itemMeta);
        return changedItemStack;
    }
}
