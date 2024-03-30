package p.squidgames.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import p.squidgames.SquidGames;
import p.squidgames.games.LocationType;

@CommandAlias("setup")
@CommandPermission("squid-games.command.admin")
@RequiredArgsConstructor
public class SetupCommand extends BaseCommand {

    @Subcommand("setup spawn")
    public void setupSpawn(Player player, LocationType locationType) {
        SquidGames.getInstance().getArena().getBaseGame().setLocation(locationType.getIdentifier(), player.getLocation());
    }
}
