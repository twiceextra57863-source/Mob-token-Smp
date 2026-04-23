package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Laser;

public class GuardianTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Shoot a laser beam forward
        for (int i = 0; i < 30; i++) {
            Location beamLoc = loc.clone().add(
                player.getEyeLocation().getDirection().getX() * i,
                player.getEyeLocation().getDirection().getY() * i + 1,
                player.getEyeLocation().getDirection().getZ() * i
            );
            world.spawnParticle(Particle.END_ROD, beamLoc, 1, 0.05, 0.05, 0.05, 0);
        }
        
        // Water drip particles
        for (int i = 0; i < 40; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 2 - 1,
                Math.random() * 2,
                Math.random() * 2 - 1
            );
            world.spawnParticle(Particle.DRIPPING_WATER, particleLoc, 1, 0.2, 0.2, 0.2, 0.05);
        }
        
        world.playSound(loc, Sound.ENTITY_GUARDIAN_ATTACK, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_GUARDIAN_AMBIENT, 1.5f, 0.8f);
    }
    
    @Override
    public String getTokenName() {
        return "Guardian";
    }
    
    @Override
    public String getDescription() {
        return "§6Guardian Token §7- Right Click to shoot a powerful laser beam!";
    }
}
