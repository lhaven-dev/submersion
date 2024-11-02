package fr.lhaven.submersion.utils;

import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Random;

public class LarguageManager {

    Random random ;

    private static LarguageManager instance;

    private PlayerManager playerManager;
    public static LarguageManager getInstance() {
        if (instance == null) {
            instance = new LarguageManager();
        }
        return instance;
    }
    private LarguageManager() {
    }

    private Location getlocation(int Border) {
        random = new Random();
        if (Border <= 0) {
            throw new IllegalArgumentException("La valeur de Border doit être positive.");
        }

        int halfZone = Border / 2;
        Location location;

        do {
            int x = random.nextInt(Border) - halfZone; // Cela donne des valeurs de -halfZone à halfZone
            int z = random.nextInt(Border) - halfZone; // Cela donne des valeurs de -halfZone à halfZone
            location = new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockYAt(x, z) + 1, z);
        } while (location.getBlock().isLiquid()); // continue jusqu'à ce qu'on trouve un bloc non liquide

        return location;
    }


     public void Start() {
    System.out.println("Larguage started");
        // int Border = BorderManager.getInstance().getBorderSize();
        // PlayerManager.getInstance().getPlayerList().forEach((uuid, playerData) -> {
        //  System.out.println("Larguage player: " + playerData.getPlayer().getName());
        //  if (playerData.getPlayer().isOnline()) {
        //    if (!playerData.isSpectator()) {
        //        Location location = getlocation(Border);
        //        Bukkit.getPlayer(uuid).teleport(location);
        //        CustomBroadcast.StartGameBroadcast(playerData.getPlayer());
        //        System.out.println("Larguage player: " + playerData.getPlayer().getName());
        //    }
       // }
  //  });
  }


}
