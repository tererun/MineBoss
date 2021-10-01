package run.tere.plugin.mineboss.shops.consts;

import org.bukkit.inventory.ItemStack;

public class ShopItem {
    private String name;
    private ItemStack itemStack;
    private double price;

    public ShopItem(String name, ItemStack itemStack, double price) {
        this.name = name;
        this.itemStack = itemStack;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public double getPrice() {
        return price;
    }
}
