package p.squidgames.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import p.squidgames.SquidGames;
import p.squidgames.games.GameType;

@CommandAlias("squidgames")
@CommandPermission("squid-games.command.spinnin34.admin")
@RequiredArgsConstructor
public class GameCommand extends BaseCommand {

    @Subcommand("phase start")
    public void startPhase(Player player, GameType gameType) {
        SquidGames.getInstance().getArena().startGame(gameType);
    }
}
