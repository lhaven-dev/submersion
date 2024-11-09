package fr.lhaven.submersion.utils.Structure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Structure {

    private Location corner1;
    private Location corner2;

    public Structure(Location corner1, Location corner2) {
        this.corner1 = corner1;
        this.corner2 = corner2;
    }

    public List<StructureBlockData> getStructureData() {
        List<StructureBlockData> blocks = new ArrayList<>();

        // Détermination des limites de la structure
        int minX = Math.min(corner1.getBlockX(), corner2.getBlockX());
        int maxX = Math.max(corner1.getBlockX(), corner2.getBlockX());
        int minY = Math.min(corner1.getBlockY(), corner2.getBlockY());
        int maxY = Math.max(corner1.getBlockY(), corner2.getBlockY());
        int minZ = Math.min(corner1.getBlockZ(), corner2.getBlockZ());
        int maxZ = Math.max(corner1.getBlockZ(), corner2.getBlockZ());

        // Récupération de tous les blocs dans la zone délimitée par corner1 et corner2
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = corner1.getWorld().getBlockAt(x, y, z);

                    if(block.getType() == Material.AIR) {
                        continue;
                    }
                    // Calcul de la position relative par rapport à corner1
                    Location relativeLocation = new Location(corner1.getWorld(), x - minX, y - minY, z - minZ);

                    // Ajouter les données du bloc à la liste
                    blocks.add(new StructureBlockData(relativeLocation, block.getType()));
                }
            }
        }
        return blocks;
    }

    public static void replaceStructure(Location location, List<StructureBlockData> structureData) {
        // Parcours des données des blocs pour remplacer les blocs à leur position relative
        for (StructureBlockData blockData : structureData) {
            // Calcul de la nouvelle position absolue du bloc à remplacer
            Location newLocation = location.clone().add(blockData.getX(), blockData.getY(), blockData.getZ());
            Block block = location.getWorld().getBlockAt(newLocation);

            // Remplacer le bloc
            block.setType(Material.getMaterial(blockData.getMaterial()));
        }
    }

    public Location getCorner1() {
        return corner1;
    }

    public Location getCorner2() {
        return corner2;
    }


public static class StructureBlockData {
        private final int x, y, z;
        private final String material;

        public StructureBlockData(Location location, Material material) {
            this.x = location.getBlockX();
            this.y = location.getBlockY();
            this.z = location.getBlockZ();
            this.material = material.name();
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

    public record StructureData(List<StructureBlockData> blocks, Location corner1, Location corner2) {    }
}
