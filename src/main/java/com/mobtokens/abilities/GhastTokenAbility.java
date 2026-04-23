package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Fireball;

public class GhastTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Shoot a large explosive fireball upward then forward
        Fireball fireball = world.spawn(player.getEyeLocation().add(0, 2, 0), Fireball.class);
        
        org.bukkit.util.Vector direction = player.getEyeLocation().getDirection().clone();
        direction.setY(0.5);
        direction.multiply(2);
        fireball.setVelocity(direction);
        fireball.setIsIncendiary(true);
        
        // Ghast crying particles
        for (int i = 0; i < 50; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 3 - 1.5,
                Math.random() * 3,
                Math.random() * 3 - 1.5
            );
            world.spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, particleLoc, 1, 0.2, 0.2, 0.2, 0.1);
        }
        
        world.playSound(loc, Sound.ENTITY_GHAST_SHOOT, 3.0f, 0.8f);
        world.playSound(loc, Sound.ENTITY_GHAST_AMBIENT, 2.0f, 0.7f);
    }
    
    @Override
    public String getTokenName() {
        return "Ghast";
    }
    
    @Override
    public String getDescription() {
        return "§6Ghast Token §7- Right Click to launch a massive explosive fireball!";
    }
}
