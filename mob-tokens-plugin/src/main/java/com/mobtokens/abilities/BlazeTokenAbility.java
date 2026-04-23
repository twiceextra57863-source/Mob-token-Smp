package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Fireball;

public class BlazeTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Shoot 3 fireballs in different directions
        for (int i = -1; i <= 1; i++) {
            Fireball fireball = world.spawn(player.getEyeLocation(), Fireball.class);
            
            org.bukkit.util.Vector direction = player.getEyeLocation().getDirection().clone();
            if (i != 0) {
                direction.rotateAroundAxis(new org.bukkit.util.Vector(0, 1, 0), Math.toRadians(i * 15));
            }
            direction.multiply(1.5);
            fireball.setVelocity(direction);
            fireball.setIsIncendiary(true);
        }
        
        // Blaze particles
        for (int i = 0; i < 40; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 2 - 1,
                Math.random() * 2,
                Math.random() * 2 - 1
            );
            world.spawnParticle(Particle.FLAME, particleLoc, 1, 0.3, 0.3, 0.3, 0.1);
            world.spawnParticle(Particle.SMOKE_NORMAL, particleLoc, 1, 0.3, 0.3, 0.3, 0.05);
        }
        
        world.playSound(loc, Sound.ENTITY_BLAZE_SHOOT, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_BLAZE_AMBIENT, 1.5f, 1.2f);
    }
    
    @Override
    public String getTokenName() {
        return "Blaze";
    }
    
    @Override
    public String getDescription() {
        return "§6Blaze Token §7- Right Click to shoot 3 incendiary fireballs!";
    }
}
