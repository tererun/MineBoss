package run.tere.plugin.mineboss;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import run.tere.plugin.mineboss.commands.MineBossCommand;
import run.tere.plugin.mineboss.configs.ConfigHandler;
import run.tere.plugin.mineboss.handlers.MineBossHandler;
import run.tere.plugin.mineboss.listeners.MineBossListener;
import run.tere.plugin.mineboss.shops.ShopHandler;
import run.tere.plugin.mineboss.utils.ItemStackUtil;

public final class MineBoss extends JavaPlugin {

    private static MineBoss plugin;
    private ConfigHandler configHandler;

    private Economy economy;
    private ShopHandler shopHandler;
    private MineBossHandler mineBossHandler;

    @Override
    public void onEnable() {
        plugin = this;
        this.configHandler = new ConfigHandler();
        if (!this.setupEconomy()) {
            getLogger().severe("Vaultが入っていないため、MineBossを有効化できませんでした");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        this.shopHandler = new ShopHandler();
        this.mineBossHandler = new MineBossHandler();
        this.registerRecipes();
        getServer().getPluginManager().registerEvents(new MineBossListener(), this);
        getCommand("mineboss").setExecutor(new MineBossCommand());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return true;
    }

    private void registerRecipes() {
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(plugin, "mb_spawn_dragon_egg"), ItemStackUtil.getSpawnDragonEgg());
        shapedRecipe.shape(
                "GCG",
                "CGC",
                "GCG"
        );
        shapedRecipe.setIngredient('G', new RecipeChoice.ExactChoice(ItemStackUtil.getRevivalPowder()));
        shapedRecipe.setIngredient('C', Material.END_CRYSTAL);
        Bukkit.addRecipe(shapedRecipe);
    }

    public static MineBoss getPlugin() {
        return plugin;
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }

    public Economy getEconomy() {
        return economy;
    }

    public ShopHandler getShopHandler() {
        return shopHandler;
    }

    public MineBossHandler getMineBossHandler() {
        return mineBossHandler;
    }
}
