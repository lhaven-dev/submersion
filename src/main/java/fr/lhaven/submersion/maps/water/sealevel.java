package fr.lhaven.submersion.maps.water;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class sealevel {
    public int sealevel;

    public sealevel() {
    }

    public void setSealevel(int sealevel) {
        this.sealevel = sealevel;
    }

    public void increaseSealevel(int sealevel) {
        this.sealevel += sealevel;
        startRaisingSeaLevelEveryMinute();
    }

    public void startRaisingSeaLevelEveryMinute() {
       //
            new BukkitRunnable() {

                @Override
                public void run() {
                    int level = sealevel;
                    raiseSeaLevel(level);
                    increaseSealevel(1);
                }
            }.runTaskTimer(Bukkit.getPluginManager().getPlugin("submersion"), 0L, 1200L); // 1200 ticks = 1 minute
        }

    public void raiseSeaLevel(int level) {
            new BukkitRunnable() {
                private int sealevel = level;
                int borderSize = 100;

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
                        for (int y = sealevel; y <= sealevel + 1; y++) { // Remplir la couche d'eau
                            World world = Bukkit.getServer().getWorld("world"); // Remplace "world" par le nom de ton monde
                            Block block = world.getBlockAt(x, y, z);
                            if (block.getType() == Material.AIR) {
                                block.setType(Material.WATER); // Remplace les blocs d'air par de l'eau
                                finished = false; // S'il y a eu un changement, on n'est pas fini
                            }
                        }
                    }
                }

                if (finished) {
                    Bukkit.getLogger().info("border "+ borderSize + "  sealevel " + sealevel);
                    Bukkit.getLogger().info("Tous les blocs d'air ont été remplacés par de l'eau. La tâche est arrêtée.");
                    cancel(); // Annule la tâche

                    // Optionnel : Affiche un message dans la console quand c'est terminé
                    Bukkit.getLogger().info("Niveau de la mer élevé au niveau " + sealevel);
                }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("submersion"), 0L, 20L); // Répète la tâche toutes les secondes
    }
}


