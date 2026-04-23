package com.mobtokens.listeners;

import com.mobtokens.abilities.AbilityRegistry;
import com.mobtokens.abilities.TokenAbility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TokenUseListener implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        
        if (item == null || item.getType() != Material.PAPER) {
            return;
        }
        
        // Check if it's a mob token
        if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return;
        }
        
        String displayName = item.getItemMeta().getDisplayName();
        if (!displayName.contains("Token")) {
            return;
        }
        
        // Only trigger on right click
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        
        // Extract token name from display name
        String tokenName = ChatColor.stripColor(displayName)
                .replace(" Token", "")
                .trim();
        
        TokenAbility ability = AbilityRegistry.getAbility(tokenName);
        if (ability == null) {
            return;
        }
        
        // Cancel event to prevent block placement etc.
        event.setCancelled(true);
        
        // Activate the ability
        ability.activate(player);
        
        // Consume one token if not in creative mode
        if (!player.getGameMode().toString().equals("CREATIVE")) {
            item.setAmount(item.getAmount() - 1);
        }
    }
}
