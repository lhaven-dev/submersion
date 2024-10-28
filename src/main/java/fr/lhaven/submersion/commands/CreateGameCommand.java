package fr.lhaven.submersion.commands;

import fr.lhaven.submersion.gui.ChoiceGamemode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Vous devez être un joueur pour executer cette commande");
            return true;
        }
        ChoiceGamemode.ChoiceGamemode(player);
        return true;

    }


}
