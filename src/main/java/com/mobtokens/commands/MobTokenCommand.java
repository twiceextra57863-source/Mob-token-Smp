package com.mobtokens.commands;

import com.mobtokens.abilities.AbilityRegistry;
import com.mobtokens.abilities.TokenAbility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MobTokenCommand implements CommandExecutor, TabCompleter {
    
    public MobTokenCommand(Object plugin) {
        // Constructor
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mobtokens.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }
        
        if (args.length < 3) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /mobtoken give <player> <token_type> [amount]");
            sender.sendMessage(ChatColor.YELLOW + "Available tokens: " + String.join(", ", AbilityRegistry.getAllAbilities().keySet()));
            return true;
        }
        
        if (args[0].equalsIgnoreCase("give")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found!");
                return true;
            }
            
            String tokenType = args[2];
            TokenAbility ability = AbilityRegistry.getAbility(tokenType);
            
            if (ability == null) {
                sender.sendMessage(ChatColor.RED + "Unknown token type: " + tokenType);
                sender.sendMessage(ChatColor.YELLOW + "Available tokens: " + String.join(", ", AbilityRegistry.getAllAbilities().keySet()));
                return true;
            }
            
            int amount = 1;
            if (args.length >= 4) {
                try {
                    amount = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid amount!");
                    return true;
                }
            }
            
            // Create token item
            Material tokenMaterial = Material.PAPER;
            ItemStack token = new ItemStack(tokenMaterial, amount);
            ItemMeta meta = token.getItemMeta();
            
            meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + ability.getTokenName() + " Token");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + ability.getDescription());
            lore.add("");
            lore.add(ChatColor.YELLOW + "Right Click to activate!");
            if (ability.getTokenName().equalsIgnoreCase("Creeper")) {
                lore.add(ChatColor.YELLOW + "Crouch + Right Click for special ability!");
            }
            lore.add("");
            lore.add(ChatColor.DARK_GRAY + "Token ID: " + ability.getTokenName().toLowerCase());
            meta.setLore(lore);
            token.setItemMeta(meta);
            
            // Give item to player
            target.getInventory().addItem(token);
            
            sender.sendMessage(ChatColor.GREEN + "Gave " + amount + " " + ability.getTokenName() + " Token(s) to " + target.getName());
            target.sendMessage(ChatColor.GREEN + "You received " + amount + " " + ability.getTokenName() + " Token!");
            target.sendMessage(ability.getDescription());
        }
        
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("give").stream()
                    .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (args.length == 2) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(s -> s.toLowerCase().startsWith(args[1].toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (args.length == 3) {
            return AbilityRegistry.getAllAbilities().keySet().stream()
                    .filter(s -> s.toLowerCase().startsWith(args[2].toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        return new ArrayList<>();
    }
}
