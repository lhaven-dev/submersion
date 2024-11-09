package fr.lhaven.submersion.commands;

import fr.lhaven.submersion.utils.Structure.Structure;
import fr.lhaven.submersion.utils.Structure.StructureLoader;
import fr.lhaven.submersion.utils.Structure.StructureManager;
import fr.lhaven.submersion.utils.Structure.StructureSaver;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StructGenReloadCommand implements CommandExecutor {
    private final StructureManager structureManager;

    public StructGenReloadCommand() {
        this.structureManager = StructureManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Vérifier si l'expéditeur est un joueur
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Cette commande doit être utilisée par un joueur.");
            return false;
        }

        // Vérifier que les arguments sont bien fournis
        if (args.length != 2) {
            player.sendMessage("Usage: /structGenReload <type> <NomDeStructure>");
            return false;
        }

        String type = args[0].toLowerCase();  // Le type (struct ou map)
        String structureName = args[1];  // Le nom de la structure ou de la map

        // Si le type est "struct", on charge une structure
        if (type.equals("struct")) {
            Structure.StructureData structureData = StructureSaver.loadStructureFromJson(structureName);
            if (structureData == null) {
                player.sendMessage("La structure " + structureName + " n'existe pas.");
                return false;
            }

            // Recharger la structure à sa position d'origine
            StructureLoader.loadAndPlaceStructure(player.getLocation(), structureData);
            player.sendMessage("Structure " + structureName + " rechargée.");
        }
        // Si le type est "map", on charge une map
        else if (type.equals("map")) {
            // Remplacez ceci par le code pour charger une map si nécessaire
            player.sendMessage("Chargement de la map " + structureName + " ...");
            // Exemple de logique à ajouter pour charger une map
            Structure.StructureData structureData = StructureSaver.loadStructureFromJson(structureName);
            if (structureData == null) {
                player.sendMessage("La map " + structureName + " n'existe pas.");
                return false;
            }
            StructureLoader.loadAndPlaceMap(player.getLocation(), structureData);
        }
        else {
            player.sendMessage("Type inconnu. Utilisez 'struct' pour une structure ou 'map' pour une map.");
            return false;
        }

        return true;
    }
}
