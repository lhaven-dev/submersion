package fr.lhaven.submersion.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class StructGenReloadCommand implements CommandExecutor {

    private final Gson gson = new Gson();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Cette commande doit être utilisée par un joueur.");
            return false;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /structGenReload <NomDeStructure>");
            return false;
        }

        String structureName = args[0];
        List<StructureBlockData> structureData = loadStructureFromJson(structureName);

        if (structureData == null) {
            player.sendMessage("La structure " + structureName + " n'existe pas.");
            return false;
        }

        // Remplacer la structure à l'endroit du joueur
        replaceStructure(player.getLocation(), structureData);
        player.sendMessage("Structure " + structureName + " rechargée.");
        return true;
    }

    // Méthode pour charger la structure depuis le fichier JSON
    private List<StructureBlockData> loadStructureFromJson(String structureName) {
        try (FileReader reader = new FileReader("plugins/Submersion/structures/" + structureName + ".json")) {
            Type listType = new TypeToken<List<StructureBlockData>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour replacer la structure dans le monde
    private void replaceStructure(Location location, List<StructureBlockData> structureData) {
        for (StructureBlockData blockData : structureData) {
            Location newLocation = location.clone().add(blockData.getX(), blockData.getY(), blockData.getZ());
            Block block = location.getWorld().getBlockAt(newLocation);
            block.setType(Material.getMaterial(blockData.getMaterial()));
        }
    }

    // Classe interne pour les données des blocs
    private static class StructureBlockData {
        private final int x, y, z;
        private final String material;

        public StructureBlockData(int x, int y, int z, String material) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.material = material;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public String getMaterial() {
            return material;
        }
    }
}
