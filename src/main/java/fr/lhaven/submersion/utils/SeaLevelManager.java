package fr.lhaven.submersion.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class SeaLevelManager {

    private int sealevel;
    private BorderManager borderManager;

    private static SeaLevelManager instance;

    // Attribut pour la taille de la bordure
    private int borderSize;

    // Liste des blocs waterloggables
    private Set<Block> nextblocks;

    private SeaLevelManager() {
        this.sealevel = 64; // Niveau de la mer par défaut
        this.borderManager = BorderManager.getInstance();
        this.nextblocks = new HashSet<>();
    }

    // Méthode statique pour obtenir l'instance unique
    public static SeaLevelManager getInstance() {
        if (instance == null) {
            instance = new SeaLevelManager();
        }
        return instance;
    }

    public int getSealevel() {
        return sealevel;
    }

    public void setSealevel(int sealevel) {
        this.sealevel = sealevel;
    }

    // Ajouter un bloc à la liste des blocs waterloggables
    public void addWaterloggableBlock(Block block) {
        if (block.getBlockData() instanceof Waterlogged waterlogged && !waterlogged.isWaterlogged()) {
            waterlogged.setWaterlogged(true);  // Active le waterlogging
            block.setBlockData(waterlogged);   // Applique la modification au bloc
            nextblocks.add(block);    // Ajoute le bloc à la liste
        }
    }

    // Supprimer un bloc de la liste des blocs waterloggables

    // Méthode pour remplir les bords avec de l'eau
    public void fillBorderWithWater(int level) {
        new BukkitRunnable() {
            int borderSize = borderManager.getBorderSize();

            @Override
            public void run() {
                int minX = -borderSize / 2;
                int maxX = borderSize / 2;
                int minZ = -borderSize / 2;
                int maxZ = borderSize / 2;

                boolean finished = true; // Flag pour savoir si la tâche est terminée

                // Remplir les bords de la zone avec de l'eau
                for (int x = minX; x <= maxX; x++) {
                    // Parcourir le côté inférieur (minZ à maxZ, x = minX)
                    for (int z = minZ; z <= maxZ; z++) {
                        for (int y = level; y <= level; y++) {
                            World world = Bukkit.getServer().getWorld("world");
                            Block block = world.getBlockAt(minX, y, z);  // Utiliser minX pour le côté gauche


                            // Si c'est un bloc d'air, on le transforme en eau
                            if (block.getType() == Material.AIR) {
                                block.setType(Material.WATER);
                                nextblocks.add(block); // Ajouter à la HashSet des blocs d'eau
                                finished = false; // Il y a eu un changement, donc la tâche n'est pas terminée
                            }
                        }
                    }
                }

                for (int x = minX; x <= maxX; x++) {
                    // Parcourir le côté supérieur (minZ à maxZ, x = maxX)
                    for (int z = minZ; z <= maxZ; z++) {
                        for (int y = level; y <= level; y++) {
                            World world = Bukkit.getServer().getWorld("world");
                            Block block = world.getBlockAt(maxX, y, z);  // Utiliser maxX pour le côté droit

                            // Si c'est un bloc d'air, on le transforme en eau
                            if (block.getType() == Material.AIR) {
                                block.setType(Material.WATER);
                                nextblocks.add(block); // Ajouter à la HashSet des blocs d'eau
                                finished = false; // Il y a eu un changement, donc la tâche n'est pas terminée
                            }
                        }
                    }
                }

                for (int z = minZ; z <= maxZ; z++) {
                    // Parcourir le côté gauche (minX à maxX, z = minZ)
                    for (int x = minX; x <= maxX; x++) {
                        for (int y = level; y <= level; y++) {
                            World world = Bukkit.getServer().getWorld("world");
                            Block block = world.getBlockAt(x, y, minZ);  // Utiliser minZ pour le côté bas

                            // Si c'est un bloc d'air, on le transforme en eau
                            if (block.getType() == Material.AIR) {
                                block.setType(Material.WATER);
                                nextblocks.add(block); // Ajouter à la HashSet des blocs d'eau
                                finished = false; // Il y a eu un changement, donc la tâche n'est pas terminée
                            }
                        }
                    }
                }

                for (int z = minZ; z <= maxZ; z++) {
                    // Parcourir le côté droit (minX à maxX, z = maxZ)
                    for (int x = minX; x <= maxX; x++) {
                        for (int y = level; y <= level; y++) {
                            World world = Bukkit.getServer().getWorld("world");
                            Block block = world.getBlockAt(x, y, maxZ);  // Utiliser maxZ pour le côté haut

                            // Si c'est un bloc d'air, on le transforme en eau
                            if (block.getType() == Material.AIR) {
                                block.setType(Material.WATER);
                                nextblocks.add(block); // Ajouter à la HashSet des blocs d'eau
                                finished = false; // Il y a eu un changement, donc la tâche n'est pas terminée
                            }
                        }
                    }
                }

                if (finished) {
                    // Si la tâche est terminée, on met à jour le niveau de la mer
                    Bukkit.getLogger().info("Niveau de la mer élevé au niveau " + level);
                    setSealevel(level); // Met à jour le niveau de la mer
                    SubmersionScoreboard.getInstance().updateWaterLevel(level);
                    startFillCheck(); // Commence à vérifier les blocs waterloggables
                    cancel(); // Annule la tâche
                }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("submersion"), 0L, 20L); // Répète la tâche toutes les secondes
    }

    // Méthode pour gérer la vérification des blocs waterloggables
    private void startFillCheck() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (nextblocks.isEmpty()) {
                    // Si la liste est vide, on arrête la tâche
                    this.cancel();
                    return;
                }

                // Crée une copie de la liste pour éviter les modifications concurrentes pendant l'itération
                HashSet<Block> blocksToProcess = new HashSet<>(nextblocks);

                // Vider la liste d'origine après avoir fait la copie
                nextblocks.clear();

                // Parcours des blocs d'eau de la copie de la liste

                for (Block block : blocksToProcess) {
                    
                    if(isInBorder(block)) {

                    for (BlockFace face : BlockFace.values()) {
                        if (face != BlockFace.UP) { // Exclure la direction 'UP'
                            Block adjacentBlock = block.getRelative(face);

                            if (adjacentBlock.getType() == Material.AIR) {
                                nextblocks.add(adjacentBlock); // Ajouter à la liste des blocs waterloggables
                            }

                            // Si le bloc adjacent peut être waterloggé, on l'ajoute à la liste
                            if (adjacentBlock.getBlockData() instanceof Waterlogged adjacentWaterlogged) {
                                if (!adjacentWaterlogged.isWaterlogged()) {
                                    nextblocks.add(adjacentBlock);
                                }
                            }
                        }
                    }
                    if(block.getBlockData() instanceof Waterlogged)
                    {
                        checkAndAddWaterloggable(block);
                    }
                    else {
                        replaceAirWithWater(block);
                    }
                }
                }
                // Si la liste est vide après traitement, on arrête la tâche
                if (nextblocks.isEmpty()) {
                    this.cancel(); // Arrêter la tâche si tous les blocs ont été traités
                }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("submersion"), 0L, 20L); // Répète la tâche toutes les secondes
    }

    // Méthode pour supprimer le niveau de la mer
    public void deleteSeaLevel() {
        this.sealevel = 0;
    }

    public boolean isInBorder(Block block) {
        int size = BorderManager.getInstance().getBorderSize() / 2;

        // Vérifie si le bloc est dans la zone délimitée (inclus dans la bordure)
        if (block.getX() >= -size && block.getX() <= size && block.getZ() >= -size && block.getZ() <= size) {
            return true; // Le bloc est dans la bordure
        }
        return false; // Le bloc est en dehors de la bordure
    }


    private void checkAndAddWaterloggable(Block block) {
        // Si le bloc est un bloc waterloggable, on l'ajoute à la liste

            Waterlogged waterlogged = (Waterlogged) block.getBlockData();
            // Si ce bloc peut être waterloggé et ne l'est pas déjà, on l'ajoute à la liste
            if (!waterlogged.isWaterlogged()) {
                waterlogged.setWaterlogged(true);
                block.setBlockData(waterlogged);
            }

    }

    private void replaceAirWithWater(Block block) {
        if (block.getType() == Material.AIR) {
            block.setType(Material.WATER);
        }
    }
}
