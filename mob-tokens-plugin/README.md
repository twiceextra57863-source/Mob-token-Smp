# MobTokens Plugin

A Minecraft Paper plugin for version 1.21.4 that adds 17 unique mob token abilities with cinematic effects!

## Features

- **17 Unique Mob Tokens** - Each with their own special ability
- **Cinematic Particle Effects** - Beautiful visual effects for every ability
- **Custom Sounds** - Immersive audio experience
- **No Potion Effects** - Pure cinematic abilities without status effects
- **Admin Command** - `/mobtoken give <player> <token_type> [amount]`

## Available Tokens

1. **Creeper** - Crouch + Right Click to summon a GIANT explosive TNT with fire particles!
2. **Enderman** - Right Click to teleport forward dramatically with purple portal effects
3. **Blaze** - Right Click to shoot 3 incendiary fireballs
4. **Ghast** - Right Click to launch a massive explosive fireball
5. **Spider** - Right Click to create a massive cobweb trap
6. **Skeleton** - Right Click to shoot 5 spectral arrows
7. **Zombie** - Right Click to summon zombie power
8. **Witch** - Right Click to throw magical splash potions
9. **Slime** - Right Click to bounce high with slime effects
10. **Phantom** - Right Click to gain temporary flight with ghostly effects
11. **Guardian** - Right Click to shoot a powerful laser beam
12. **Shulker** - Right Click to shoot 3 homing bullets
13. **Vex** - Right Click to dash forward with ghostly charge
14. **Evoker** - Right Click to summon magical fangs attack
15. **Wither Skeleton** - Right Click to shoot 3 wither skulls
16. **Magma Cube** - Right Click to create a fiery explosion and jump high
17. **Silverfish** - Right Click to create infestation and dash forward

## Requirements

- Minecraft 1.21.4
- Paper Server
- Java 21

## Installation

1. Download the latest `.jar` file from the releases page
2. Place it in your server's `plugins` folder
3. Restart your server

## Usage

### Admin Command
```
/mobtoken give <player> <token_type> [amount]
```

### Examples
```
/mobtoken give Steve Creeper 5
/mobtoken give Alex Enderman
/mobtoken give Notch Blaze 10
```

### Using Tokens
- Hold the token in your hand
- Right Click to activate the ability
- For Creeper Token: Crouch + Right Click for special ability

## Building from Source

```bash
git clone https://github.com/yourusername/MobTokens.git
cd MobTokens
mvn clean package
```

The compiled `.jar` file will be in the `target` folder.

## Permissions

- `mobtokens.admin` - Allows use of the `/mobtoken` command (default: OP)

## Development

This plugin uses:
- Java 21
- Maven for dependency management
- Paper API 1.21.4
- GitHub Actions for CI/CD

## License

All rights reserved.

## Support

For issues and feature requests, please open an issue on GitHub.
