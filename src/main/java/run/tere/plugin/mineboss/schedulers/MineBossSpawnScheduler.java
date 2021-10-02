package run.tere.plugin.mineboss.schedulers;

import org.bukkit.*;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import run.tere.plugin.mineboss.MineBoss;
import run.tere.plugin.mineboss.utils.LocationUtil;

public class MineBossSpawnScheduler extends BukkitRunnable {
    private World world;
    private Location fromLocation;
    private Location animEndRodLocation;
    private int count;

    public MineBossSpawnScheduler(Location fromLocation) {
        this.fromLocation = fromLocation.clone().add(0.5, 0, 0.5);
        this.world = fromLocation.getWorld();
        this.animEndRodLocation = this.fromLocation.clone();
        this.count = 0;
    }

    @Override
    public void run() {
        if (this.count >= 15) {
            Location spawnLocation = this.animEndRodLocation.clone().add(0, 5, 0);
            //this.world.spawn(spawnLocation, EnderDragon.class, e -> {
            //    e.setPhase(EnderDragon.Phase.CIRCLING);
            //});
            Location portalLocation = this.world.getEnderDragonBattle().getEndPortalLocation().clone();
            portalLocation.clone().add(0, 4, 0).getBlock().setType(Material.DRAGON_EGG);
            this.world.spawnEntity(LocationUtil.getCenterLocation(portalLocation.clone().add(3, 1, 0)), EntityType.ENDER_CRYSTAL);
            this.world.spawnEntity(LocationUtil.getCenterLocation(portalLocation.clone().add(0, 1, 3)), EntityType.ENDER_CRYSTAL);
            this.world.spawnEntity(LocationUtil.getCenterLocation(portalLocation.clone().add(-3, 1, 0)), EntityType.ENDER_CRYSTAL);
            this.world.spawnEntity(LocationUtil.getCenterLocation(portalLocation.clone().add(0, 1, -3)), EntityType.ENDER_CRYSTAL);
            DragonBattle dragonBattle = this.world.getEnderDragonBattle();
            dragonBattle.initiateRespawn();
            this.world.playSound(this.fromLocation, Sound.ENTITY_GENERIC_EXPLODE, 1F, 1F);
            this.world.playSound(this.fromLocation, Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1F, 1F);
            this.world.spawnParticle(Particle.EXPLOSION_HUGE, this.animEndRodLocation, 30, 5, 5, 5, 1);
            this.world.spawnParticle(Particle.PORTAL, this.animEndRodLocation, 80, 5, 5, 5, 1);
            this.world.spawnParticle(Particle.VILLAGER_HAPPY, this.animEndRodLocation, 80, 1, 1, 1, 1);
            this.fromLocation.getBlock().setType(Material.AIR);
            MineBoss.getPlugin().getMineBossHandler().removeEggLocation(this.fromLocation);
            this.cancel();
            return;
        }
        float pitch = (float) this.count * 0.1F + 0.5F;
        this.world.spawnParticle(Particle.PORTAL, this.fromLocation, 12 * this.count, 0.5, 0.5, 0.5, 0.1);
        this.world.spawnParticle(Particle.END_ROD, this.animEndRodLocation, 15, 0.5, 0.5, 0.5, 0.01);
        this.animEndRodLocation.add(0, 1, 0);
        this.world.playSound(this.fromLocation, Sound.BLOCK_BREWING_STAND_BREW, 1F, pitch);
        this.count++;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public Location getAnimEndRodLocation() {
        return animEndRodLocation;
    }

    public int getCount() {
        return count;
    }
}
