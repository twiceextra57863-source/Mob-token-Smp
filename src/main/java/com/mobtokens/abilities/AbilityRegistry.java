package com.mobtokens.abilities;

import java.util.HashMap;
import java.util.Map;

public class AbilityRegistry {
    
    private static final Map<String, TokenAbility> abilities = new HashMap<>();
    
    public static void init() {
        // Register all 17 mob token abilities
        register(new CreeperTokenAbility());
        register(new EndermanTokenAbility());
        register(new BlazeTokenAbility());
        register(new GhastTokenAbility());
        register(new SpiderTokenAbility());
        register(new SkeletonTokenAbility());
        register(new ZombieTokenAbility());
        register(new WitchTokenAbility());
        register(new SlimeTokenAbility());
        register(new PhantomTokenAbility());
        register(new GuardianTokenAbility());
        register(new ShulkerTokenAbility());
        register(new VexTokenAbility());
        register(new EvokerTokenAbility());
        register(new WitherSkeletonTokenAbility());
        register(new MagmaCubeTokenAbility());
        register(new SilverfishTokenAbility());
        
        System.out.println("[MobTokens] Registered " + abilities.size() + " abilities");
    }
    
    public static void register(TokenAbility ability) {
        abilities.put(ability.getTokenName().toLowerCase(), ability);
    }
    
    public static TokenAbility getAbility(String name) {
        return abilities.get(name.toLowerCase());
    }
    
    public static Map<String, TokenAbility> getAllAbilities() {
        return new HashMap<>(abilities);
    }
}
