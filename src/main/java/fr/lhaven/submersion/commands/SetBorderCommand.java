package fr.lhaven.submersion.commands;

import fr.lhaven.submersion.border.BorderManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderCommand implements CommandExecutor {
    BorderManager borderManager;
    public SetBorderCommand() {
        borderManager = BorderManager.getInstance();    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Cette commande peut uniquement être exécutée par un joueur.");
            return true;
        }

        if (args.length != 1) {
            commandSender.sendMessage("Usage: /setbordersize <taille>");
            return true;
        }
        Player player = (Player) commandSender;

        player.sendMessage("La bordure a été ajustée!");
        borderManager.setBorderSize(Integer.parseInt(args[0]));

        return true;

}
}
