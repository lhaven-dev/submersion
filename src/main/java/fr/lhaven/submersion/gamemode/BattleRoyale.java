package fr.lhaven.submersion.gamemode;


import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.map.SeaLevelManager;
import fr.lhaven.submersion.map.Terrain.Island;
import fr.lhaven.submersion.map.Terrain.Terrain;
import fr.lhaven.submersion.map.Terrain.Volcano;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.utils.CustomBroadcast;
import fr.lhaven.submersion.utils.LarguageManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class BattleRoyale extends GameMode {

    private GameManager gameManager;
    public BattleRoyale() {
        gameManager = GameManager.getInstance();
        this.isStarted = false;
        this.isFinished = false;
        this.isRunning = false;
        this.timeElapsed = 0;
    }
    @Override
    public void ChooseMap(String mapName) {

        if(gameManager.isGameCreated())
        {
            System.out.println("Parti crée , choix la map possible ");
            if (mapName == "Volcano") {
                Terrain terrain = new Volcano();
            }
            else if (mapName == "Island") {
                Terrain terrain = new Island();
            }
            else {
                System.out.println("La partie n'a pas été créée");
            }
        }
        else {
            System.out.println("La partie n'a pas été créée");
        }
    }

    @Override
    public void addplayerToGame(UUID uuid) {
            PlayerManager.getInstance().addPlayer(uuid);
            System.out.println("Le joueur a été ajouté au jeu.");
    }

    @Override
    public void addspectatorToGame(UUID uuid) {
        PlayerManager.getInstance().addSpectator(uuid);
        System.out.println("Le joueur a été ajouté en tant que spectateur.");
    }
    @Override
    public void startGame() {
        System.out.println("Démarrage du mode Battle Royale !");
        this.isStarted = true;
        this.isRunning = true;
        this.isFinished = false;
        this.timeElapsed = 0;

        PlayerManager.getInstance().randomTeleportPlayers();
    }
}