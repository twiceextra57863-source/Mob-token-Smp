package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class WitchTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Throw splash potions in all directions
        for (int i = 0; i < 5; i++) {
            ThrownPotion potion = world.spawn(player.getEyeLocation(), ThrownPotion.class);
            
            org.bukkit.util.Vector direction = player.getEyeLocation().getDirection().clone();
            direction.rotateAroundAxis(new org.bukkit.util.Vector(0, 1, 0), Math.toRadians(i * 72));
            direction.setY(0.3);
            direction.multiply(1.2);
            potion.setVelocity(direction);
            
            // Add particle trail
            Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("MobTokens"), task -> {
                if (!potion.isValid()) {
                    task.cancel();
                    return;
                }
                potion.getWorld().spawnParticle(Particle.SPELL_WITCH, potion.getLocation(), 2, 0.1, 0.1, 0.1, 0.05);
            }, 0, 2);
        }
        
        // Witch particles
        for (int i = 0; i < 30; i++) {
            Location particleLoc = loc.clone().add(
                Math.random() * 2 - 1,
                Math.random() * 2,
                Math.random() * 2 - 1
            );
            world.spawnParticle(Particle.SPELL_WITCH, particleLoc, 1, 0.2, 0.2, 0.2, 0.1);
        }
        
        world.playSound(loc, Sound.ENTITY_WITCH_AMBIENT, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_WITCH_THROW, 1.5f, 1.2f);
    }
    
    @Override
    public String getTokenName() {
        return "Witch";
    }
    
    @Override
    public String getDescription() {
        return "§6Witch Token §7- Right Click to throw magical splash potions!";
    }
}
