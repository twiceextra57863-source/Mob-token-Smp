package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class SlimeTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Launch player upward with slime bounce effect
        org.bukkit.util.Vector velocity = player.getVelocity();
        velocity.setY(2);
        player.setVelocity(velocity);
        
        // Slime particles everywhere
        for (int i = 0; i < 50; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 2 - 1,
                Math.random() * 3,
                Math.random() * 2 - 1
            );
            world.spawnParticle(Particle.ITEM_SLIME, particleLoc, 1, 0.3, 0.3, 0.3, 0.05);
        }
        
        // Create bouncing slime balls on ground
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45);
            double x = loc.getX() + Math.cos(angle) * 2;
            double z = loc.getZ() + Math.sin(angle) * 2;
            Location slimeLoc = new Location(world, x, loc.getY(), z);
            world.spawnParticle(Particle.ITEM_SLIME, slimeLoc, 3, 0.2, 0.2, 0.2, 0.02);
        }
        
        world.playSound(loc, Sound.ENTITY_SLIME_JUMP_LARGE, 2.0f, 1.2f);
        world.playSound(loc, Sound.BLOCK_SLIME_BLOCK_PLACE, 1.5f, 1.0f);
    }
    
    @Override
    public String getTokenName() {
        return "Slime";
    }
    
    @Override
    public String getDescription() {
        return "§6Slime Token §7- Right Click to bounce high with slime effects!";
    }
}
