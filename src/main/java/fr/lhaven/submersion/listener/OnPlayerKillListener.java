package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.utils.SubmersionScoreboard;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerKillListener implements Listener {

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent event) {
        Entity killer = event.getEntity().getKiller();

        System.out.println("Entité tuée : " + event.getEntity().getName());
        // Vérifie si l'entité tuée a bien été tuée par un joueur
        Player player = event.getEntity().getPlayer();
        PlayerManager.getInstance().getPlayerData(player.getUniqueId()).addKill();
        int updatedKills = PlayerManager.getInstance().getPlayerData(player.getUniqueId()).getKills();
        //SubmersionScoreboard.getInstance().updateKills(event.getEntity().getPlayer(), updatedKills);

        if (killer instanceof Player) {
           // Player player = (Player) killer;
            System.out.println("Le joueur " + player.getName() + " a tué une entité  !");
            // Met à jour le nombre de kills dans PlayerManager
            PlayerManager.getInstance().getPlayerData(player.getUniqueId()).addKill();
            // Récupère le nouveau nombre de kills pour mettre à jour le scoreboard
         //   int updatedKills = PlayerManager.getInstance().getPlayerData(player.getUniqueId()).getKills();
            // Met à jour le scoreboard pour ce joueur
            System.out.println("Nombre de kills : " + updatedKills);
        //    SubmersionScoreboard.getInstance().updateKills(player, updatedKills);
        }
    }
}
