package fr.lhaven.submersion.scenario;

import fr.lhaven.submersion.Submersion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.World;

public class PluieAcide {

    private static boolean isActive = false; // Statut du scénario

    public static void startPluieAcide() {
        if (isActive) {
            return; // Si déjà actif, ne rien faire
        }

        isActive = true; // Marque comme actif
        World world = Bukkit.getWorld("world");

        if (world == null) {
            isActive = false; // Réinitialiser si le monde n'existe pas
            return; // Vérifie si le monde existe
        }

        // Démarre la pluie
        world.setStorm(true);
        Bukkit.getScheduler().runTaskLater(Submersion.getPlugin(Submersion.class), () -> {
            world.setStorm(false);
            isActive = false; // Marque comme inactif lorsque la pluie s'arrête
        }, 6000L); // 6000 ticks = 5 minutes

        new BukkitRunnable() {
            int elapsedTime = 0;

            @Override
            public void run() {
                if (elapsedTime >= 6000) { // Arrête la tâche après 5 minutes
                    cancel();
                    isActive = false; // Réinitialiser le statut
                    return;
                }

                for (Player player : world.getPlayers()) {
                    if (player.getLocation().getBlock().getLightFromSky() > 0) {
                        player.damage(0.5); // 0.5 HP de dégâts
                    }
                }

                elapsedTime += 300;
            }
        }.runTaskTimer(Submersion.getPlugin(Submersion.class), 0L, 300L); // Toutes les 15 secondes
    }

    // Méthode pour activer ou désactiver le scénario depuis le ScenarioManager
    public static void toggleScenario(boolean activate) {
        if (activate) {
            startPluieAcide(); // Démarre le scénario
        } else {
            // Logique pour désactiver si nécessaire
            isActive = false; // Réinitialiser le statut
        }
    }
}
