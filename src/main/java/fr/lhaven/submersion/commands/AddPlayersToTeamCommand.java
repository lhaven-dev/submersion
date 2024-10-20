package fr.lhaven.submersion.commands;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.players.team.Team;
import fr.lhaven.submersion.players.team.TeamManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class AddPlayersToTeamCommand implements CommandExecutor {

    PlayerManager playerManager;

    public AddPlayersToTeamCommand() {
        playerManager = PlayerManager.getInstance(); }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Cette commande peut uniquement être exécutée par un joueur.");
            return true;
        }

        if (args.length != 2) {
            commandSender.sendMessage("Usage: /addPlayerToTeam <Player> <Team>");
            return true;
        }
        getLogger().info("Commande addPlayerToTeam");
        Player player = ((Player) commandSender).getPlayer();
        TeamManager.getInstance().addPlayerToTeam(player, args[1]);
        return true;

    }
}
