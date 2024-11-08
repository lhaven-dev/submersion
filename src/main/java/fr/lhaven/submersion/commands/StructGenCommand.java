package fr.lhaven.submersion.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.lhaven.submersion.utils.CreateStructure;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StructGenCommand implements CommandExecutor {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public StructGenCommand() {
        // Enregistrement de la commande dans le plugin
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Cette commande doit être utilisée par un joueur.");
            return false;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /structGen <NomDeStructure>");
            return false;
        }

        String structureName = args[0];

        // Récupérer les coins depuis le singleton
Location corner1 = CreateStructure.getInstance().getCorner1();
Location corner2 = CreateStructure.getInstance().getCorner2();
        if (corner1 == null || corner2 == null) {
            player.sendMessage("Veuillez définir les deux coins avec la pelle en or.");
            return true;
        }

        // Récupération des données de la structure
        List<StructureBlockData> structureData = getStructureData(corner1, corner2);

        // Sauvegarde de la structure en JSON
        saveStructureToJson(structureName, structureData);
        player.sendMessage("Structure sauvegardée sous le nom : " + structureName);
        return true;
    }

    // Méthode pour récupérer les données de la structure
    private List<StructureBlockData> getStructureData(Location loc1, Location loc2) {
        List<StructureBlockData> blocks = new ArrayList<>();
        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = loc1.getWorld().getBlockAt(x, y, z);
                    if (block.getType() != Material.AIR) {
                        Location relativeLocation = new Location(loc1.getWorld(), x - minX, y - minY, z - minZ);
                        blocks.add(new StructureBlockData(relativeLocation, block.getType()));
                    }
                }
            }
        }
        return blocks;
    }

    // Sauvegarde des données de la structure dans un fichier JSON
    private void saveStructureToJson(String structureName, List<StructureBlockData> structureData) {
        File structuresDir = new File("plugins/Submersion/structures");
        // Vérifie si le répertoire existe, sinon le crée
        if (!structuresDir.exists()) {
            if (!structuresDir.mkdirs()) {
                System.err.println("Erreur lors de la création du répertoire structures.");
                return;
            }
        }
        try (FileWriter writer = new FileWriter("plugins/Submersion/structures/" + structureName + ".json")) {
            gson.toJson(structureData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe interne pour les données de chaque bloc
    private static class StructureBlockData {
        private final int x, y, z;
        private final String material;

        public StructureBlockData(Location location, Material material) {
            this.x = location.getBlockX();
            this.y = location.getBlockY();
            this.z = location.getBlockZ();
            this.material = material.name();
        }
    }
}
