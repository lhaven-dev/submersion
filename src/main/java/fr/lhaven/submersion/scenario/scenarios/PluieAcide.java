package fr.lhaven.submersion.scenario.scenarios;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class PluieAcide extends Scenario {
    private static boolean isActive = false; // Statut du scénario
    private static boolean hasAlreadyActivated  = false; // Statut de l'activation

    @Override
    public void startScenario() {

        if(hasAlreadyActivated||isActive){
            return;
        }
        Random rand = new Random();
        if(rand.nextInt(100) < 5){
            isActive = true;
            hasAlreadyActivated = true;
            RunScenario(); // Lancer le scénario
        }
        }


    @Override
    public void RunScenario() {
        final int duration = 5 * 60;
        // Programmer le début de la pluie
        Bukkit.getWorld("world").setStorm(true);
        new BukkitRunnable() {
            int elapsed = 0;

            @Override
            public void run() {
                // Vérifie si le temps est écoulé
                if (elapsed >= duration) {
                    // Fin de la pluie et arrêt de la tâche
                    Bukkit.getWorld("world").setStorm(false);
                    getOnlinePlayers().forEach(player -> player.sendMessage("La pluie acide s'arrête."));
                    this.cancel();
                    return;
                }

                // Applique les effets aux joueurs chaque seconde
                getOnlinePlayers().forEach(player -> {
                    player.sendMessage("La pluie acide commence à tomber !");
                    // Vérifie les conditions pour appliquer les dégâts
                    if (PlayerManager.getInstance().getPlayerData(player.getUniqueId()).isAlive() &&
                            player.getLocation().getBlockY() >= Bukkit.getWorld("world").getHighestBlockYAt(
                                    player.getLocation().getBlockX(), player.getLocation().getBlockZ())) {
                        player.damage(1);  // Inflige un dégât au joueur
                        player.sendMessage("Vous prenez des dégâts à cause de la pluie acide !");
                    }
                });

                // Incrémenter le temps écoulé
                elapsed++;
            }
        }.runTaskTimer(Submersion.getPlugin(Submersion.class), 0, 20); // Exécute toutes les secondes (20 ticks)


    };
}
