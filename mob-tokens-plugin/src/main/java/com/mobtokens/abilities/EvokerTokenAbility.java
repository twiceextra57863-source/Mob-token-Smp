package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class EvokerTokenAbility implements TokenAbility {
    
    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        
        // Create evoker fangs in a line forward
        for (int i = 1; i <= 5; i++) {
            final int delay = i * 5;
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("MobTokens"), () -> {
                Location fangLoc = loc.clone().add(
                    player.getEyeLocation().getDirection().getX() * i * 1.5,
                    0,
                    player.getEyeLocation().getDirection().getZ() * i * 1.5
                );
                
                // Fang particles
                for (int j = 0; j < 10; j++) {
                    Location particleLoc = fangLoc.clone().add(
                        Math.random() * 0.5 - 0.25,
                        Math.random() * 1,
                        Math.random() * 0.5 - 0.25
                    );
                    world.spawnParticle(Particle.TOTEM_OF_UNDYING, particleLoc, 1, 0.1, 0.2, 0.1, 0.05);
                }
                
                world.playSound(fangLoc, Sound.ENTITY_EVOKER_FANGS_ATTACK, 1.5f, 1.0f);
            }, delay);
        }
        
        // Evoker spell particles around player
        for (int i = 0; i < 360; i += 20) {
            double rad = Math.toRadians(i);
            double x = loc.getX() + Math.cos(rad) * 2;
            double z = loc.getZ() + Math.sin(rad) * 2;
            Location spellLoc = new Location(world, x, loc.getY(), z);
            world.spawnParticle(Particle.SPELL_MOB, spellLoc, 3, 0.1, 0.1, 0.1, 0.5);
        }
        
        world.playSound(loc, Sound.ENTITY_EVOKER_CAST_SPELL, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.5f, 0.8f);
    }
    
    @Override
    public String getTokenName() {
        return "Evoker";
    }
    
    @Override
    public String getDescription() {
        return "§6Evoker Token §7- Right Click to summon magical fangs attack!";
    }
}
