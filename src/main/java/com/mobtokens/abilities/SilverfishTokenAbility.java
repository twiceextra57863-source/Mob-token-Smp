package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class SilverfishTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Create silverfish infestation particles from ground
        for (int i = 0; i < 30; i++) {
            double angle = Math.toRadians(i * 12);
            double x = loc.getX() + Math.cos(angle) * 3;
            double z = loc.getZ() + Math.sin(angle) * 3;
            Location particleLoc = new Location(world, x, loc.getY(), z);
            world.spawnParticle(Particle.SCULK_CHARGE, particleLoc, 2, 0.2, 0.2, 0.2, 0.1);
        }
        
        // Ground cracks effect
        for (int i = 0; i < 20; i++) {
            Location crackLoc = loc.clone().add(
                Math.random() * 4 - 2,
                loc.getY(),
                Math.random() * 4 - 2
            );
            world.spawnParticle(Particle.VIBRATION, crackLoc, 1, 0, 0, 0, 0.5);
        }
        
        // Quick dash forward
        org.bukkit.util.Vector direction = player.getEyeLocation().getDirection().clone();
        direction.setY(0.2);
        direction.multiply(2);
        player.setVelocity(direction);
        
        world.playSound(loc, Sound.ENTITY_SILVERFISH_STEP, 2.0f, 1.2f);
        world.playSound(loc, Sound.ENTITY_SILVERFISH_HURT, 1.5f, 1.0f);
        world.playSound(loc, Sound.BLOCK_SCULK_SPREAD, 1.0f, 0.8f);
    }
    
    @Override
    public String getTokenName() {
        return "Silverfish";
    }
    
    @Override
    public String getDescription() {
        return "§6Silverfish Token §7- Right Click to create infestation and dash forward!";
    }
}
