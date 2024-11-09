package fr.lhaven.submersion.game;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.game.gamemode.BattleRoyale;
import fr.lhaven.submersion.game.gamemode.GameMode;
import fr.lhaven.submersion.map.MapManager;
import fr.lhaven.submersion.map.Terrain.Island;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.utils.DisconnectedManager;
import fr.lhaven.submersion.utils.SeaLevelManager;
import fr.lhaven.submersion.utils.SubmersionScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class GameManager {

    private GameMode gameMode;
    private boolean gameStarted = false;
    private boolean gameCreated = false;
    private boolean gameEnded = false;

    private  SubmersionScoreboard scoreboard;


    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isGameCreated() {
        return gameCreated;
    }

    private static GameManager instance;

    private GameManager() {

        this.scoreboard = SubmersionScoreboard.getInstance();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }


    public void deleteGame() {
        if (gameCreated) {
            gameCreated = false;
            gameStarted = false;
            gameEnded = false;
            System.out.println("Game deleted");
            PlayerManager.getInstance().deletePlayers();
            MapManager.getInstance().deleteMap();
            SeaLevelManager.getInstance().deleteSeaLevel();
            scoreboard.deleteScoreboard();
            gameMode = null;

        } else {
            System.out.println("Game not created yet");
        }
    }

    public void createGame() {
        if (gameCreated) {
            System.out.println("Game already created");
            return;
        }
        gameCreated = true;
        System.out.println("Game created");
        setGameMode("Battle Royale");
        MapManager.getInstance().setTerrain(new Island());
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (PlayerManager.getInstance().getPlayerData(player.getUniqueId()) == null) {
                PlayerManager.getInstance().addPlayer(player.getUniqueId());
            }
        });
    }

    public void setGameMode(String mode) {
        if (gameCreated) {
            this.gameMode = new BattleRoyale();
            System.out.println("Game mode set to " + mode);
        } else {
            System.out.println("Game not created yet");
        }
    }

    public void startGame() {
        if (gameStarted) {
            System.out.println("Game already started");
            return;
        }
        DisconnectedManager.getInstance();
        System.out.println("AvantStartGame created");
        gameMode.startGame();
        gameStarted = true;
        PlayerManager.getInstance().setSpectatorCount();
        PlayerManager.getInstance().setAliveCount();
        System.out.println("Game started");
        for (Player player : Bukkit.getOnlinePlayers()) {
            SubmersionScoreboard.getInstance().initializeScoreboard(player);
        }
        PlayerManager.getInstance().randomTeleportPlayers();
    }

    public long GetTime() {
        return gameMode.getTimeElapsed();
    }

    public void endGame() {
        gameEnded = true;
        System.out.println("La partie est terminée. Merci d'avoir joué !");
        resumeGame();
    }

    public void resumeGame() {
        if(!gameEnded)
        {
            System.out.println("Game resumed");
        }
    }

    public boolean isMapSelected() {
        return MapManager.getInstance().isMapSelected();
    }
}
