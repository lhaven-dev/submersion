package fr.lhaven.submersion.larguage;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;

import java.util.HashMap;

public class LarguageManager {
    private static LarguageManager instance;
    private PlayerManager playerManager;

    public static LarguageManager getInstance() {
        if (instance == null) {
            instance = new LarguageManager();
        }
        return instance;
    }

  public void Start() {
    System.out.println("Larguage started");

    playerManager = PlayerManager.getInstance();
     // on tp les joueurs aléatoirement dans la zone de la border de la map avec une chute lente ( tp + slowfall )
    playerManager.getPlayerList().forEach((uuid, playerData) -> {
      System.out.println("Larguage player: " + playerData.getPlayer().getName());
    });

  }
}
