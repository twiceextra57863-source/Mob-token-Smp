package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class EndermanTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Teleport forward dramatically
        Vector direction = player.getEyeLocation().getDirection();
        Location teleportLoc = loc.add(direction.multiply(15));
        
        // Purple portal particles before teleport
        for (int i = 0; i < 30; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 2 - 1,
                Math.random() * 2,
                Math.random() * 2 - 1
            );
            world.spawnParticle(Particle.PORTAL, particleLoc, 1, 0.1, 0.1, 0.1, 0.5);
        }
        
        world.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 2.0f, 1.0f);
        
        // Teleport after delay
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("MobTokens"), () -> {
            player.teleport(teleportLoc);
            
            // Explosion of purple particles at destination
            for (int i = 0; i < 50; i++) {
                Location particleLoc = teleportLoc.clone().add(
                    Math.random() * 3 - 1.5,
                    Math.random() * 3,
                    Math.random() * 3 - 1.5
                );
                world.spawnParticle(Particle.PORTAL, particleLoc, 1, 0.2, 0.2, 0.2, 0.5);
                world.spawnParticle(Particle.END_ROD, particleLoc, 1, 0.2, 0.2, 0.2, 0.1);
            }
            
            world.playSound(teleportLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 2.0f, 1.5f);
        }, 10);
    }
    
    @Override
    public String getTokenName() {
        return "Enderman";
    }
    
    @Override
    public String getDescription() {
        return "§6Enderman Token §7- Right Click to teleport forward dramatically!";
    }
}
