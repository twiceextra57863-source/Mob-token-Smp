package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Create zombie summoning particles
        for (int i = 0; i < 40; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 3 - 1.5,
                Math.random() * 2,
                Math.random() * 3 - 1.5
            );
            world.spawnParticle(Particle.SCULK_CHARGE_POP, particleLoc, 1, 0.3, 0.3, 0.3, 0.05);
        }
        
        // Play zombie sounds
        world.playSound(loc, Sound.ENTITY_ZOMBIE_AMBIENT, 2.0f, 0.8f);
        world.playSound(loc, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1.5f, 0.7f);
        
        // Give player strength effect visually
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("MobTokens"), () -> {
            for (int i = 0; i < 20; i++) {
                Location particleLoc = player.getLocation().clone().add(
                    Math.random() * 1 - 0.5,
                    Math.random() * 2,
                    Math.random() * 1 - 0.5
                );
                world.spawnParticle(Particle.ENCHANT, particleLoc, 1, 0.2, 0.5, 0.2, 0.1);
            }
        }, 10);
    }
    
    @Override
    public String getTokenName() {
        return "Zombie";
    }
    
    @Override
    public String getDescription() {
        return "§6Zombie Token §7- Right Click to summon zombie power!";
    }
}
