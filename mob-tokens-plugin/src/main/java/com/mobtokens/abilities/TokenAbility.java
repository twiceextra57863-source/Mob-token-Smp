package com.mobtokens.abilities;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public interface TokenAbility {
    void activate(Player player);
    
    String getTokenName();
    
    String getDescription();
}
