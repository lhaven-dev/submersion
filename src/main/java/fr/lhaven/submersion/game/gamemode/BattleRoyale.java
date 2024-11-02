package fr.lhaven.submersion.game.gamemode;
import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.map.MapManager;
import fr.lhaven.submersion.map.Terrain.Terrain;
import fr.lhaven.submersion.players.PlayerManager;

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
    public void startGame() {
        System.out.println("Démarrage du mode Battle Royale !");
        this.isStarted = true;
        this.isRunning = true;
        this.isFinished = false;
        this.timeElapsed = 0;

        PlayerManager.getInstance().randomTeleportPlayers();
    }
    public Terrain getCurrentMap() {
        return MapManager.getInstance().getTerrain();
    }
}

