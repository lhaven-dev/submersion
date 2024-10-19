package fr.lhaven.submersion.map;

import fr.lhaven.submersion.border.BorderManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import javax.swing.border.Border;

public class SeaLevelManager {

    private int sealevel;
    private BorderManager borderManager;

    private static SeaLevelManager instance;

    // Attribut pour la taille de la bordure
    private int borderSize;

    // Constructeur privé pour empêcher l'instanciation extérieure
    private SeaLevelManager() {
        this.sealevel = 0; // ou une autre valeur par défaut
        borderManager = BorderManager.getInstance();

    }

    // Méthode statique pour obtenir l'instance unique
    public static SeaLevelManager getInstance() {
        if (instance == null) {
            instance = new SeaLevelManager();
        }
        return instance;
    }


    public void setSealevel(int sealevel) {
        this.sealevel = sealevel;
    }

    public void updateSeaLevel(int level) {
        new BukkitRunnable() {

           int borderSize = borderManager.getBorderSize();

            @Override
            public void run() {
                // Récupère la taille de la bordure
                int minX = (int) (-borderSize / 2); // Centre à (0, 0)
                int maxX = (int) (borderSize / 2);
                int minZ = (int) (-borderSize / 2);
                int maxZ = (int) (borderSize / 2);

                boolean finished = true; // Flag pour suivre si tout est terminé

                // Parcours les blocs dans les limites de la bordure à partir de la couche 64
                for (int x = minX; x <= maxX; x++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        for (int y = level; y <= level + 1; y++) { // Remplir la couche d'eau
                            World world = Bukkit.getServer().getWorld("world");
                            Block block = world.getBlockAt(x, y, z);
                            if (block.getType() == Material.AIR) {
                                block.setType(Material.WATER); // Remplace les blocs d'air par de l'eau
                                finished = false; // S'il y a eu un changement, on n'est pas fini
                            }
                        }
                    }
                }

                if (finished) {
                    Bukkit.getLogger().info("Niveau de la mer élevé au niveau " + level);
                    cancel(); // Annule la tâche
                   }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("submersion"), 0L, 20L); // Répète la tâche toutes les secondes
    }
}
