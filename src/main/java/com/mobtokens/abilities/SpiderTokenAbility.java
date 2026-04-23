package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.CaveSpider;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpiderTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Spawn web particles in all directions
        for (int i = 0; i < 60; i++) {
            double angle = Math.toRadians(i * 6);
            double x = loc.getX() + Math.cos(angle) * 5;
            double z = loc.getZ() + Math.sin(angle) * 5;
            Location particleLoc = new Location(world, x, loc.getY() + 1, z);
            world.spawnParticle(Particle.COBWEB, particleLoc, 2, 0.5, 0.5, 0.5, 0.05);
        }
        
        // Play spider sounds
        world.playSound(loc, Sound.ENTITY_SPIDER_STEP, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_CAVE_SPIDER_AMBIENT, 1.5f, 1.2f);
        
        // Create cobweb effect around player
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("MobTokens"), () -> {
            for (int i = 0; i < 360; i += 30) {
                double rad = Math.toRadians(i);
                double x = loc.getX() + Math.cos(rad) * 3;
                double z = loc.getZ() + Math.sin(rad) * 3;
                Location webLoc = new Location(world, x, loc.getY(), z);
                world.spawnParticle(Particle.COBWEB, webLoc, 5, 0.3, 0.3, 0.3, 0.02);
            }
        }, 5);
    }
    
    @Override
    public String getTokenName() {
        return "Spider";
    }
    
    @Override
    public String getDescription() {
        return "§6Spider Token §7- Right Click to create a massive cobweb trap!";
    }
}
