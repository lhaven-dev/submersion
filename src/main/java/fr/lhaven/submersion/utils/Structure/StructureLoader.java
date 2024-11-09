package fr.lhaven.submersion.utils.Structure;

import fr.lhaven.submersion.Submersion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureLoader {

    // Méthode qui lance le processus de placement des blocs pour une structure
    public static void loadAndPlaceStructure(Location location, Structure.StructureData structureData) {
        Bukkit.getScheduler().runTaskLater(Submersion.getPlugin(Submersion.class), new Runnable() {
            private int currentIndex = 0;  // Suivi de l'index actuel dans la liste des blocs
            private final List<Structure.StructureBlockData> blocks = structureData.blocks();
            private final Location corner1 = structureData.corner1();  // Récupérer corner1
            private final Location corner2 = structureData.corner2();  // Récupérer corner2

            @Override
            public void run() {
                if (currentIndex < blocks.size()) {
                    Structure.StructureBlockData blockData = blocks.get(currentIndex);
                    // Calculer la position relative du bloc à partir des coins
                    Location newLocation = corner1.clone().add(blockData.getX(), blockData.getY(), blockData.getZ());
                    Block block = location.getWorld().getBlockAt(newLocation);
                    Material material = Material.getMaterial(blockData.getMaterial());

                    // Ne placer le bloc que s'il n'est pas de l'air
                    if (material != Material.AIR) {
                        block.setType(material);
                    }
                    currentIndex++;
                    // Réexécuter cette tâche après un délai (par exemple 5 ticks)
                    Bukkit.getScheduler().runTaskLater(Submersion.getPlugin(Submersion.class), this, 5L);
                } else {
                    // Quand tous les blocs sont placés, envoyer un message de fin (si nécessaire)
                    System.out.println("Structure placement complete.");
                }
            }
        }, 0L);  // Commence immédiatement
    }



    // Méthode qui lance le processus de placement des blocs pour une map (inclut l'air)
    public static void loadAndPlaceMap(Location location, Structure.StructureData structureData) {
        Bukkit.getScheduler().runTaskLater(Submersion.getPlugin(Submersion.class), new Runnable() {
            private int currentY = 0;  // La hauteur actuelle à traiter
            private final List<Structure.StructureBlockData> blocks = structureData.blocks();
            private final Location corner1 = structureData.corner1();  // Récupérer corner1
            private final Location corner2 = structureData.corner2();  // Récupérer corner2

            // Structure pour regrouper les blocs par Y
            private final Map<Integer, List<Structure.StructureBlockData>> blocksByY = new HashMap<>();

            {
                // Organiser les blocs par Y
                for (Structure.StructureBlockData blockData : blocks) {
                    int y = blockData.getY();
                    blocksByY.computeIfAbsent(y, k -> new ArrayList<>()).add(blockData);
                }
            }

            @Override
            public void run() {
                    List<Structure.StructureBlockData> blocksAtCurrentY = blocksByY.get(currentY);
                    if (blocksAtCurrentY != null) {
                        for (Structure.StructureBlockData blockData : blocksAtCurrentY) {
                            // Calculer la position absolue à partir de corner1
                            Location newLocation = corner1.clone().add(blockData.getX(), blockData.getY(), blockData.getZ());
                            Block block = location.getWorld().getBlockAt(newLocation);
                            Material material = Material.getMaterial(blockData.getMaterial());

                            if (material != null) {
                                block.setType(material);
                            }
                        }


                    // Passer à la couche suivante
                    currentY++;
                    // Réexécuter cette tâche après un délai (par exemple, 20 ticks)
                    Bukkit.getScheduler().runTaskLater(Submersion.getPlugin(Submersion.class), this, 20L);
                } else {
                    System.out.println("Structure placement complete.");
                }
            }
        }, 0L);  // Commence immédiatement
    }
}