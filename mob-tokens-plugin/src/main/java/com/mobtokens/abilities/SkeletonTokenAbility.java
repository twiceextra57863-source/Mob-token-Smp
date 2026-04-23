package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.SpectralArrow;

public class SkeletonTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Shoot spectral arrows in a spread pattern
        for (int i = -2; i <= 2; i++) {
            Arrow arrow = world.spawn(player.getEyeLocation(), SpectralArrow.class);
            
            org.bukkit.util.Vector direction = player.getEyeLocation().getDirection().clone();
            if (i != 0) {
                direction.rotateAroundAxis(new org.bukkit.util.Vector(0, 1, 0), Math.toRadians(i * 10));
            }
            direction.multiply(2);
            arrow.setVelocity(direction);
        }
        
        // Bone particles
        for (int i = 0; i < 30; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 2 - 1,
                Math.random() * 2,
                Math.random() * 2 - 1
            );
            world.spawnParticle(Particle.SCULK_SOUL, particleLoc, 1, 0.2, 0.2, 0.2, 0.05);
        }
        
        world.playSound(loc, Sound.ENTITY_SKELETON_SHOOT, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_ARROW_SHOOT, 1.5f, 1.2f);
    }
    
    @Override
    public String getTokenName() {
        return "Skeleton";
    }
    
    @Override
    public String getDescription() {
        return "§6Skeleton Token §7- Right Click to shoot 5 spectral arrows!";
    }
}
