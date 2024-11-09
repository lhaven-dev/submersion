package fr.lhaven.submersion.commands;

import org.bukkit.Location;
import fr.lhaven.submersion.utils.Structure.Structure;
import fr.lhaven.submersion.utils.Structure.StructureManager;
import fr.lhaven.submersion.utils.Structure.StructureSaver;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class StructGenCommand implements CommandExecutor {
    private final StructureManager structureManager;

    public StructGenCommand() {
        this.structureManager = StructureManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Cette commande doit être utilisée par un joueur.");
            return false;
        }

        // Vérifier si les coins ont été définis
        if (!structureManager.hasCorners()) {
            player.sendMessage("Veuillez définir les deux coins avec la pelle en or.");
            return false;
        }

        // Récupérer les coins depuis le StructureManager
        Location corner1 = structureManager.getCorner1();
        Location corner2 = structureManager.getCorner2();

        // Créer la structure avec les coins définis
        Structure structure = new Structure(corner1, corner2);
        List<Structure.StructureBlockData> structureData = structure.getStructureData();
        // Sauvegarder la structure en JSON avec StructureSaver
        StructureSaver.saveStructureToJson(args[0], structureData, player.getLocation(), corner1, corner2);
        player.sendMessage("Structure sauvegardée sous le nom : " + args[0]);
        return true;
    }
}
