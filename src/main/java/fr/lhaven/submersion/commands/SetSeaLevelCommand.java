package fr.lhaven.submersion.commands;

import fr.lhaven.submersion.map.SeaLevelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSeaLevelCommand implements CommandExecutor {
SeaLevelManager sealevelManager;
    public SetSeaLevelCommand() {
        this.sealevelManager =  SeaLevelManager.getInstance();
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Cette commande peut uniquement être exécutée par un joueur.");
            return true;
    }

        if (args.length != 1) {
            commandSender.sendMessage("Usage: /raiseSeaLevel <niveau>");
            return true;
        }
        Player player = (Player) commandSender;

        // Démarrer la montée du niveau de la mer
        sealevelManager.updateSeaLevel(Integer.parseInt(args[0]));
        player.sendMessage("La montée du niveau de la mer a été lancée !");

        return true;
}
}