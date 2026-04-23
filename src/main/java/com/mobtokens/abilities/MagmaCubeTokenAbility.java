package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class MagmaCubeTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Create magma explosion around player
        for (int i = 0; i < 50; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 3 - 1.5,
                Math.random() * 2,
                Math.random() * 3 - 1.5
            );
            world.spawnParticle(Particle.FLAME, particleLoc, 1, 0.3, 0.3, 0.3, 0.1);
            world.spawnParticle(Particle.LAVA, particleLoc, 1, 0.3, 0.3, 0.3, 0.05);
        }
        
        // Fire ring on ground
        for (int i = 0; i < 360; i += 15) {
            double rad = Math.toRadians(i);
            double x = loc.getX() + Math.cos(rad) * 2.5;
            double z = loc.getZ() + Math.sin(rad) * 2.5;
            Location fireLoc = new Location(world, x, loc.getY(), z);
            world.spawnParticle(Particle.FALLING_LAVA, fireLoc, 2, 0.1, 0.1, 0.1, 0.02);
        }
        
        // Launch player upward
        org.bukkit.util.Vector velocity = player.getVelocity();
        velocity.setY(1.5);
        player.setVelocity(velocity);
        
        world.playSound(loc, Sound.BLOCK_FIRE_AMBIENT, 2.0f, 1.0f);
        world.playSound(loc, Sound.BLOCK_LAVA_POP, 1.5f, 0.8f);
    }
    
    @Override
    public String getTokenName() {
        return "MagmaCube";
    }
    
    @Override
    public String getDescription() {
        return "§6Magma Cube Token §7- Right Click to create a fiery explosion and jump high!";
    }
}
