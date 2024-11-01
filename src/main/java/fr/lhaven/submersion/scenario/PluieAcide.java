package fr.lhaven.submersion.scenario;
import fr.lhaven.submersion.Submersion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.World;

public class PluieAcide
{
public void PluieAcide() {
    World world = Bukkit.getWorld("world");

    if (world == null) {
        return; // Vérifie si le monde existe
    }

    // Démarre la pluie
    world.setStorm(true);
    Bukkit.getScheduler().runTaskLater(Submersion.getPlugin(Submersion.class), () -> {
        world.setStorm(false);
    }, 6000L); // 6000 ticks = 5 minutes

    new BukkitRunnable() {
        int elapsedTime = 0;

        @Override
        public void run() {
            if (elapsedTime >= 6000) { // Arrête la tâche après 5 minutes
                cancel();
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
}