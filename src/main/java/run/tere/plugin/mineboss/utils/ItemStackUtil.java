package run.tere.plugin.mineboss.utils;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStackUtil {
    public static ItemStack getItemStack(Material material, int amount, String displayName, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getKeyedItemStack(Material material, int amount, String displayName, List<String> lore, String key) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        itemStack = NBTEditor.set(itemStack, key, "MineBossItem");
        return itemStack;
    }

    public static ItemStack getCustomItemStack(Material material, int amount, String displayName, List<String> lore, String key) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        itemStack = NBTEditor.set(itemStack, key, "CustomItemClickEvent");
        return itemStack;
    }

    public static ItemStack getRevivalPowder() {
        return ItemStackUtil.getCustomItemStack(Material.GUNPOWDER, 1, "§eふっかつのもと", Arrays.asList(" §a§oクラフト素材", " §dエンダードラゴン§fなどの復活の材料"), "RevivalPowder");
    }

    public static ItemStack getSpawnDragonEgg() {
        return ItemStackUtil.getKeyedItemStack(Material.DRAGON_EGG, 1, "§e孵化しそうなドラゴンのたまご", Arrays.asList("§a今すぐにでも§f孵化しそうなたまご", "§bエンド§fでのみ孵化可能"), "SpawnDragonEgg");
    }

    public static ItemStack getItemStackDescribedPrice(ItemStack itemStack, double price) {
        ItemStack changedItemStack = itemStack.clone();
        ItemMeta itemMeta = changedItemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        lore.add(ChatColor.of(Color.decode("#B1E693")) + " " + price + "FP");
        itemMeta.setLore(lore);
        changedItemStack.setItemMeta(itemMeta);
        return changedItemStack;
    }
}
