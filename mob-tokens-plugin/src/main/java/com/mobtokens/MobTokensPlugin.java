package com.mobtokens;

import com.mobtokens.abilities.*;
import com.mobtokens.commands.MobTokenCommand;
import com.mobtokens.listeners.TokenUseListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MobTokensPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands
        getCommand("mobtoken").setExecutor(new MobTokenCommand(this));
        
        // Register listeners
        getServer().getPluginManager().registerEvents(new TokenUseListener(this), this);
        
        // Initialize all abilities
        AbilityRegistry.init();
        
        getLogger().info("MobTokens plugin enabled successfully!");
        getLogger().info("17 unique mob token abilities are ready to use!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MobTokens plugin disabled!");
    }
}
