package fr.lhaven.submersion.commands;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.players.team.TeamManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;


public class CreateTeamCommand  implements CommandExecutor {

    PlayerManager playerManager;
    public CreateTeamCommand() {
        playerManager = PlayerManager.getInstance(); }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Cette commande peut uniquement être exécutée par un joueur.");
            return true;
        }

        if (args.length != 1) {
            commandSender.sendMessage("Usage: /CreateTeam <Name>");
            return true;
        }
        getLogger().info("Commande addPlayerToTeam");

        TeamManager.getInstance().createTeam(args[0]);
        return true;
    }
}

