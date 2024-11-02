package fr.lhaven.submersion.game;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.game.gamemode.BattleRoyale;
import fr.lhaven.submersion.game.gamemode.GameMode;
import fr.lhaven.submersion.map.MapManager;
import fr.lhaven.submersion.map.Terrain.Island;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.utils.SeaLevelManager;
import fr.lhaven.submersion.utils.SubmersionScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class GameManager {

    private GameMode gameMode;
    private String gamemodeName;
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
            if (gamemodeName != mode) {
                if (mode == "Battle Royale") {
                    this.gamemodeName = mode;
                    this.gameMode = new BattleRoyale();
                    System.out.println("Gamemode Choisi" + mode);
                } else {
                    System.out.println("Game mode already set to " + mode);
                }
            } else {
                System.out.println("Game mode " + mode + " not found");
            }
        } else {
            System.out.println("Game not created yet");
        }
    }

    public void startGame() {
        System.out.println("Game started");
        gameMode.startGame();
        gameStarted = true;
        PlayerManager.getInstance().setSpectatorCount();
        PlayerManager.getInstance().setAliveCount();
        for (Player player : Bukkit.getOnlinePlayers()) {
            SubmersionScoreboard.getInstance().initializeScoreboard(player);
        }

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

}
