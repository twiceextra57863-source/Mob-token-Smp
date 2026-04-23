package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;

public class WitherSkeletonTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Shoot wither skulls
        for (int i = -1; i <= 1; i++) {
            WitherSkull skull = world.spawn(player.getEyeLocation().add(0, 0.5, 0), WitherSkull.class);
            
            org.bukkit.util.Vector direction = player.getEyeLocation().getDirection().clone();
            if (i != 0) {
                direction.rotateAroundAxis(new org.bukkit.util.Vector(0, 1, 0), Math.toRadians(i * 20));
            }
            direction.setY(direction.getY() + 0.1);
            direction.multiply(1.8);
            skull.setVelocity(direction);
        }
        
        // Wither particles
        for (int i = 0; i < 40; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 2 - 1,
                Math.random() * 2,
                Math.random() * 2 - 1
            );
            world.spawnParticle(Particle.WITHER_ROSE, particleLoc, 1, 0.2, 0.2, 0.2, 0.05);
            world.spawnParticle(Particle.SMOKE_NORMAL, particleLoc, 1, 0.2, 0.2, 0.2, 0.05);
        }
        
        world.playSound(loc, Sound.ENTITY_WITHER_SHOOT, 2.0f, 0.8f);
        world.playSound(loc, Sound.ENTITY_WITHER_AMBIENT, 1.5f, 0.7f);
    }
    
    @Override
    public String getTokenName() {
        return "WitherSkeleton";
    }
    
    @Override
    public String getDescription() {
        return "§6Wither Skeleton Token §7- Right Click to shoot 3 wither skulls!";
    }
}
