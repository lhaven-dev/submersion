package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.players.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        // Récupérer le joueur qui est mort
        Player player = event.getEntity();

        // Récupérer les données du joueur dans PlayerManager
        PlayerData playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId());

        // Vérifier si les données du joueur existent
        if (playerData != null) {
            // Mettre à jour l'état du joueur à "mort"
            PlayerManager.getInstance().setDead(player.getUniqueId());
           // playerData.setKilledBy(event.getEntity().getKiller().getName()); // ajouter le tué par a la personne morte
        }
        if(player.getKiller() instanceof Player){
            event.setDeathMessage(player.getName() + " a été tué"); // Supprimer le message de mort par défaut

            Player killer = player.getKiller();
            PlayerManager.getInstance().getPlayerData(player.getUniqueId()).setKilledBy(killer.getName());
            PlayerManager.getInstance().getPlayerData(killer.getUniqueId()).addKill();
        }
        else{
            event.setDeathMessage(player.getName() + " est mort");
        }

        // Vous pouvez également ajouter des messages ou d'autres logiques ici, si nécessaire
    }
}
