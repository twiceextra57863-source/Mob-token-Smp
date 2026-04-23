package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class PhantomTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Give levitation effect visually
        for (int i = 0; i < 40; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 2 - 1,
                Math.random() * 3,
                Math.random() * 2 - 1
            );
            world.spawnParticle(Particle.SCULK_SOUL, particleLoc, 1, 0.2, 0.3, 0.2, 0.05);
        }
        
        // Create phantom wings effect
        for (int i = 0; i < 360; i += 15) {
            double rad = Math.toRadians(i);
            double x = loc.getX() + Math.cos(rad) * 1.5;
            double z = loc.getZ() + Math.sin(rad) * 1.5;
            Location wingLoc = new Location(world, x, loc.getY() + 1, z);
            world.spawnParticle(Particle.CLOUD, wingLoc, 2, 0.1, 0.1, 0.1, 0.02);
        }
        
        // Lift player slightly
        org.bukkit.util.Vector velocity = player.getVelocity();
        velocity.setY(0.8);
        player.setVelocity(velocity);
        
        world.playSound(loc, Sound.ENTITY_PHANTOM_FLAP, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_PHANTOM_AMBIENT, 1.5f, 0.8f);
    }
    
    @Override
    public String getTokenName() {
        return "Phantom";
    }
    
    @Override
    public String getDescription() {
        return "§6Phantom Token §7- Right Click to gain temporary flight with ghostly effects!";
    }
}
