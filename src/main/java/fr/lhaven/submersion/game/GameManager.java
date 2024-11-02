package fr.lhaven.submersion.game;

import fr.lhaven.submersion.game.gamemode.BattleRoyale;
import fr.lhaven.submersion.game.gamemode.GameMode;
import fr.lhaven.submersion.utils.SeaLevelManager;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.utils.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager {

    private GameMode gameMode;
    private String gamemodeName;
    private final Scoreboards Scoreboards = new Scoreboards();
    private boolean gameStarted = false;
    private boolean gameCreated = false;
    private boolean gameEnded = false;
    private boolean gameResumed = false;





    public boolean isGameStarted() {
        return gameStarted;
    }
    public boolean isGameCreated() {
        return gameCreated;
    }
    private static GameManager instance;
    private GameManager() {
    }
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }



    public void createGame() {
        gameCreated = true;
        System.out.println("Game created");
        setGameMode("Battle Royale");
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (PlayerManager.getInstance().getPlayerData(player.getUniqueId()) == null) {
                PlayerManager.getInstance().addPlayer(player.getUniqueId());
            }
        });
    }

    public void setGameMode(String mode) {
        if(gameCreated)
        {
            if(gamemodeName != mode)
            {
                if (mode == "Battle Royale") {
                    this.gamemodeName = mode;
                    this.gameMode = new BattleRoyale();
                    System.out.println("Gamemode Choisi" + mode);
                }
                else
                {
                    System.out.println("Game mode already set to "+ mode);
                }
            }
            else
            {
                System.out.println("Game mode " + mode +" not found");
            }
        }
        else
        {
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
            Scoreboards.setupScoreboard(player);
        }

        // Mettre à jour le scoreboard global avec des valeurs initiales
        Scoreboards.updateGlobalScore("Niveau d'eau", SeaLevelManager.getInstance().getSealevel());;
        Scoreboards.updateGlobalScore("Joueurs Restants", PlayerManager.getInstance().getAlivePlayersCount());
       // Scoreboards.updateGlobalScore("temps", timeElapsed);
    }



    private void checkEndOfGame() {
        int alivePlayers = PlayerManager.getInstance().getAlivePlayersCount();
        if (alivePlayers <= 1) {
            System.out.println("Un seul joueur en vie. Fin de la partie !");
            endGame();
        }
    }

    public void endGame() {
        gameEnded = true;
        gameMode.endGame();
        System.out.println("La partie est terminée. Merci d'avoir joué !");
    }


    public void resumeGame() {
        System.out.println("Game resumed");
    }

}
