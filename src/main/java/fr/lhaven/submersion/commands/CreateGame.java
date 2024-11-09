package fr.lhaven.submersion.commands;

import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.gui.ChoiceMap;
import fr.lhaven.submersion.gui.MenuPrincipal;
import fr.lhaven.submersion.utils.Structure.Structure;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Cette commande doit être utilisée par un joueur.");
            return false;
        }
        GameManager.getInstance().createGame();
        MenuPrincipal.MenuPrincipal(player);
        return true;
    }
}
