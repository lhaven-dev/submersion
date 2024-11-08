package fr.lhaven.submersion.scenario.scenarios;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.utils.BorderManager;
import fr.lhaven.submersion.utils.SeaLevelManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Blizzard extends Scenario {
    private static boolean isActive = false; // Statut du scénario
    private static boolean hasAlreadyActivated  = false; // Statut de l'activation

    @Override
    public void startScenario() {

        if(hasAlreadyActivated||isActive){
            return;
        }
        Random rand = new Random();
        if(rand.nextInt(100) < 50){
            isActive = true;
            hasAlreadyActivated = true;
            RunScenario(); // Lancer le scénario

        }

    }
    @Override
    public void RunScenario() {

                new BukkitRunnable() {
                    World world = Bukkit.getWorlds().get(0); // Ajuste si nécessaire pour cibler un monde spécifique
                    WorldBorder worldBorder = world.getWorldBorder();

                    // Calcul des limites de la WorldBorder
                    int borderSize = BorderManager.getInstance().getBorderSize() / 2;
                    int minX = -borderSize;
                    int maxX = borderSize;
                    int minZ = -borderSize;
                    int maxZ = borderSize;
                    @Override
                    public void run() {
                        if (!isActive) {
                            this.cancel(); // Arrêter la tâche si le scénario est désactivé
                            return;
                        }
                        int y = SeaLevelManager.getInstance().getSealevel();

                        for (int x =  minX; x <= maxX; x++) {
                            for (int z =  minZ; z <= maxZ; z++) {
                                    Block block = world.getBlockAt(x, y, z);

                                    // Si le bloc est de l'eau, on le transforme en glace
                                    if (block.getType() == Material.WATER) {
                                        block.setType(Material.ICE);
                                    }
                                }
                        }
                    }
                }.runTaskTimer(Submersion.getPlugin(Submersion.class), 0L, 20L); // Remplace `pluginInstance` par l'instance de ton plugin
            }
        }



