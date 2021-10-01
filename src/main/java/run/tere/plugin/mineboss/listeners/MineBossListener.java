package run.tere.plugin.mineboss.listeners;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;
import run.tere.plugin.mineboss.MineBoss;
import run.tere.plugin.mineboss.schedulers.MineBossSpawnScheduler;
import run.tere.plugin.mineboss.utils.ChatUtil;

public class MineBossListener implements Listener {
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent e) {
        Block block = e.getBlock();
        if (block.getType().equals(Material.DRAGON_EGG)) {
            if (MineBoss.getPlugin().getMineBossHandler().containsEggLocation(block.getLocation())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        Block block = e.getBlock();
        if (block.getType().equals(Material.DRAGON_EGG)) {
            if (MineBoss.getPlugin().getMineBossHandler().containsEggLocation(block.getLocation())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack handItem = e.getItemInHand();
        Block block = e.getBlockPlaced();
        Location blockLocation = block.getLocation();
        World blockWorld = block.getWorld();
        if (handItem.getType().equals(Material.DRAGON_EGG) && NBTEditor.contains(handItem, "MineBossItem") && NBTEditor.getString(handItem, "MineBossItem").equalsIgnoreCase("SpawnDragonEgg")) {
            if (!blockWorld.getEnvironment().equals(World.Environment.THE_END)) {
                ChatUtil.sendMessage(player, "§c§oこのたまごはエンドでのみ置けるようだ。");
                e.setCancelled(true);
                return;
            }
            if (MineBoss.getPlugin().getConfigHandler().getWorldList().contains(blockWorld.getName())) {
                ChatUtil.sendMessage(player, "§c§oこのたまごはここでは置けないようだ。");
                e.setCancelled(true);
                return;
            }
            MineBoss.getPlugin().getMineBossHandler().addEggLocation(blockLocation);
            MineBossSpawnScheduler mineBossSpawnScheduler = new MineBossSpawnScheduler(blockLocation);
            mineBossSpawnScheduler.runTaskTimer(MineBoss.getPlugin(), 0L, 5L);
        }
    }
}
