package com.mobtokens.abilities;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.util.Vector;

public class GuardianTokenAbility implements TokenAbility {

    @Override
    public void activate(Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();

        // Cinematic Sounds
        world.playSound(loc, Sound.ENTITY_GUARDIAN_ATTACK, 2.0f, 1.0f);
        world.playSound(loc, Sound.ENTITY_GUARDIAN_AMBIENT, 1.5f, 0.8f);
        world.playSound(loc, Sound.BLOCK_BEACON_ACTIVATE, 0.5f, 2.0f);

        Vector direction = player.getEyeLocation().getDirection();
        
        // Create visual beam using AreaEffectCloud
        AreaEffectCloud beam = world.spawn(loc.clone().add(0, 1.5, 0), AreaEffectCloud.class);
        beam.setRadius(0.8F);
        beam.setColor(Color.fromRGB(0, 255, 255)); // Cyan like Guardian
        beam.setParticle(Particle.END_ROD);
        beam.setDuration(40); // 2 seconds
        
        // Spawn projectile to simulate laser
        Arrow laserProjectile = world.spawn(loc.clone().add(0, 1.5, 0), Arrow.class);
        laserProjectile.setVelocity(direction.multiply(3));
        laserProjectile.setGravity(false);
        laserProjectile.setSilent(true);
        laserProjectile.setInvulnerable(true);
        laserProjectile.setCritical(false);

        // Particle trail task
        new org.bukkit.scheduler.BukkitRunnable() {
            int ticks = 0;
            Location currentLoc = loc.clone().add(0, 1.5, 0);
            
            @Override
            public void run() {
                if (!laserProjectile.isValid() || ticks > 60) {
                    this.cancel();
                    if (beam != null && !beam.isDead()) beam.remove();
                    if (laserProjectile != null && !laserProjectile.isDead()) laserProjectile.remove();
                    return;
                }

                currentLoc.add(direction.clone().multiply(0.5));
                
                // Draw particles along the path
                for (int i = 0; i < 5; i++) {
                    world.spawnParticle(Particle.END_ROD, currentLoc.clone(), 1, 0.1, 0.1, 0.1, 0);
                    world.spawnParticle(Particle.DRIPPING_WATER, currentLoc.clone(), 1, 0.2, 0.2, 0.2, 0.05);
                }

                // Check collision with ground
                if (currentLoc.getBlock().getType().isSolid()) {
                    // Impact Effect
                    world.playSound(currentLoc, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.5f);
                    world.spawnParticle(Particle.CLOUD, currentLoc, 50, 1, 1, 1, 0.5);
                    
                    // Damage entities in radius
                    for (org.bukkit.entity.Entity e : world.getNearbyEntities(currentLoc, 3, 3, 3)) {
                        if (e instanceof org.bukkit.entity.LivingEntity && e != player) {
                            ((org.bukkit.entity.LivingEntity) e).damage(8.0, player);
                        }
                    }
                    
                    this.cancel();
                    if (beam != null && !beam.isDead()) beam.remove();
                    if (laserProjectile != null && !laserProjectile.isDead()) laserProjectile.remove();
                }
                
                ticks++;
            }
        }.runTaskTimer(com.mobtokens.MobTokensPlugin.getPlugin(), 0L, 1L);
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
