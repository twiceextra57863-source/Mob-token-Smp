package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class VexTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Create vex charge effect - dash forward rapidly
        org.bukkit.util.Vector direction = player.getEyeLocation().getDirection().clone();
        direction.multiply(3);
        player.setVelocity(direction);
        
        // Ghostly particles trail
        for (int i = 0; i < 20; i++) {
            final int delay = i * 2;
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("MobTokens"), () -> {
                Location trailLoc = player.getLocation().clone().subtract(
                    direction.getX() * i * 0.1,
                    Math.random(),
                    direction.getZ() * i * 0.1
                );
                world.spawnParticle(Particle.SCULK_CHARGE_POP, trailLoc, 2, 0.1, 0.1, 0.1, 0.05);
            }, delay);
        }
        
        // Vex summoning circle
        for (int i = 0; i < 360; i += 30) {
            double rad = Math.toRadians(i);
            double x = loc.getX() + Math.cos(rad) * 2;
            double z = loc.getZ() + Math.sin(rad) * 2;
            Location circleLoc = new Location(world, x, loc.getY(), z);
            world.spawnParticle(Particle.SOUL, circleLoc, 3, 0.1, 0.1, 0.1, 0.02);
        }
        
        world.playSound(loc, Sound.ENTITY_VEX_AMBIENT, 2.0f, 1.2f);
        world.playSound(loc, Sound.ENTITY_VEX_CHARGE, 1.5f, 1.0f);
    }
    
    @Override
    public String getTokenName() {
        return "Vex";
    }
    
    @Override
    public String getDescription() {
        return "§6Vex Token §7- Right Click to dash forward with ghostly charge!";
    }
}
