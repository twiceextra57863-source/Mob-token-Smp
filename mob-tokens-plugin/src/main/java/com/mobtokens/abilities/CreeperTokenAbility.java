package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;

public class CreeperTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        if (!player.isSneaking()) {
            player.sendMessage("§cYou need to crouch + right click to use Creeper Token!");
            return;
        }
        
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Spawn a giant TNT
        TNTPrimed tnt = world.spawn(loc.add(0, 1, 0), TNTPrimed.class);
        tnt.setFuseTicks(60); // 3 seconds
        tnt.setIsIncendiary(true);
        
        // Make it visually larger using metadata or just particles
        spawnGiantTNTParticles(tnt.getLocation(), world);
        
        // Play creeper hiss sound
        world.playSound(loc, Sound.ENTITY_CREEPER_PRIMED, 2.0f, 1.0f);
        
        // Create fire chain particles
        for (int i = 0; i < 20; i++) {
            final int delay = i * 5;
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("MobTokens"), () -> {
                Location particleLoc = tnt.getLocation().clone().add(
                    Math.random() * 3 - 1.5,
                    Math.random() * 3,
                    Math.random() * 3 - 1.5
                );
                world.spawnParticle(Particle.FLAME, particleLoc, 5, 0.5, 0.5, 0.5, 0.1);
                world.spawnParticle(Particle.SMOKE_NORMAL, particleLoc, 3, 0.5, 0.5, 0.5, 0.05);
            }, delay);
        }
        
        // Explosion effect
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("MobTokens"), () -> {
            world.createExplosion(tnt.getLocation(), 8.0f, true, true);
            world.playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 3.0f, 0.5f);
            
            // Massive particle explosion
            for (int i = 0; i < 100; i++) {
                Location expLoc = tnt.getLocation().clone().add(
                    Math.random() * 10 - 5,
                    Math.random() * 10 - 5,
                    Math.random() * 10 - 5
                );
                world.spawnParticle(Particle.EXPLOSION_LARGE, expLoc, 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, expLoc, 2, 0.3, 0.3, 0.3, 0.1);
            }
        }, 60);
    }
    
    private void spawnGiantTNTParticles(Location loc, World world) {
        // Create ring of fire particles around the TNT
        for (int i = 0; i < 360; i += 10) {
            double rad = Math.toRadians(i);
            double x = loc.getX() + Math.cos(rad) * 2;
            double z = loc.getZ() + Math.sin(rad) * 2;
            Location particleLoc = new Location(world, x, loc.getY(), z);
            world.spawnParticle(Particle.FLAME, particleLoc, 1, 0, 0, 0, 0);
        }
    }
    
    @Override
    public String getTokenName() {
        return "Creeper";
    }
    
    @Override
    public String getDescription() {
        return "§6Creeper Token §7- Crouch + Right Click to summon a GIANT explosive TNT!";
    }
}
