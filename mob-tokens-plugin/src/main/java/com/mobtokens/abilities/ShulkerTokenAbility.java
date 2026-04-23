package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;

public class ShulkerTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Shoot homing shulker bullets
        for (int i = 0; i < 3; i++) {
            ShulkerBullet bullet = world.spawn(player.getEyeLocation(), ShulkerBullet.class);
            
            org.bukkit.util.Vector direction = player.getEyeLocation().getDirection().clone();
            if (i == 1) direction.rotateAroundAxis(new org.bukkit.util.Vector(0, 1, 0), Math.toRadians(15));
            if (i == 2) direction.rotateAroundAxis(new org.bukkit.util.Vector(0, 1, 0), Math.toRadians(-15));
            direction.multiply(1.5);
            bullet.setVelocity(direction);
        }
        
        // Purple shield particles
        for (int i = 0; i < 360; i += 20) {
            double rad = Math.toRadians(i);
            double x = loc.getX() + Math.cos(rad) * 1.5;
            double z = loc.getZ() + Math.sin(rad) * 1.5;
            Location shieldLoc = new Location(world, x, loc.getY() + 1, z);
            world.spawnParticle(Particle.END_ROD, shieldLoc, 2, 0.1, 0.1, 0.1, 0.05);
        }
        
        world.playSound(loc, Sound.ENTITY_SHULKER_SHOOT, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_SHULKER_AMBIENT, 1.5f, 0.8f);
    }
    
    @Override
    public String getTokenName() {
        return "Shulker";
    }
    
    @Override
    public String getDescription() {
        return "§6Shulker Token §7- Right Click to shoot 3 homing bullets!";
    }
}
