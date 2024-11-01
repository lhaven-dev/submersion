package fr.lhaven.submersion.game;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.gamemode.BattleRoyale;
import fr.lhaven.submersion.gamemode.GameMode;
import fr.lhaven.submersion.map.SeaLevelManager;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.scenario.PluieAcide;
import fr.lhaven.submersion.utils.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.logging.Logger;

public class GameManager {

    private GameMode gameMode;
    private String gamemodeName;
    private BukkitRunnable gameTimer;
    private final Scoreboards Scoreboards = new Scoreboards();
    private boolean gameStarted = false;
    private boolean gameCreated = false;
    private boolean gameEnded = false;
    private boolean gameResumed = false;

    int timeElapsed = 0;

    private boolean ScenarioPluieAcide = false;
    private boolean PluieAcideActivated = false;



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

    public void  AddToGame(Player player)
    {
        gameMode.addplayerToGame(player.getUniqueId());
        System.out.println(player.getName() + " added to game");
    }
    public void  setSpectator(Player player)
    {
        gameMode.addspectatorToGame(player.getUniqueId());
        System.out.println(player.getName() + " added to game");
    }
    public void removeSpecator(Player player)
    {
        PlayerManager.getInstance().removeSpectator(player.getUniqueId());
        System.out.println(player.getName() + " removed from game");
    }

    public void setMap(String map) {
        System.out.println("Map set to " + map);
        if(gameMode != null)
        gameMode.ChooseMap(map);
    }

    public void createGame() {
        gameCreated = true;
        System.out.println("Game created");
        setGameMode("Battle Royale");
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(!PlayerManager.getInstance().getPlayerList().containsKey(player.getUniqueId()))
            {
                AddToGame(player);
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
        startGameTimer();
        PlayerManager.getInstance().setSpectatorCount();
        PlayerManager.getInstance().setAliveCount();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboards.setupScoreboard(player);
        }

        // Mettre à jour le scoreboard global avec des valeurs initiales
        Scoreboards.updateGlobalScore("Niveau d'eau", SeaLevelManager.getInstance().getSealevel());;
        Scoreboards.updateGlobalScore("Joueurs Restants", PlayerManager.getInstance().getAlivePlayersCount());
        Scoreboards.updateGlobalScore("temps", timeElapsed);
    }


    private void startGameTimer() {
        gameTimer = new BukkitRunnable() {
            @Override
            public void run() {
            //    Logger.getLogger("Submersion").info("Game timer running");
            //    Logger.getLogger("Submersion").info("Time elapsed: " + timeElapsed);
            //    Logger.getLogger("Submersion").info("Sea level: " + SeaLevelManager.getInstance().getSealevel());
            //    Logger.getLogger("Submersion").info("Alive players: " + PlayerManager.getInstance().getAlivePlayersCount());
            //    Logger.getLogger("Submersion").info("Var " + gameMode.isFinished() + gameMode.isRunning());
                if (gameMode.isRunning() && !gameMode.isFinished()) {
                    timeElapsed++;
                    checkWaterLevelIncrease();
                    triggerRandomEvents();
                    Scoreboards.updateGlobalScore("Joueurs Restants", PlayerManager.getInstance().getAlivePlayersCount());
                    Scoreboards.updateGlobalScore("temps", timeElapsed);
                    //checkEndOfGame();
                } else {
                    this.cancel(); // Arrête le timer quand le jeu est fini
                }
            }
        };
        gameTimer.runTaskTimer(Submersion.getPlugin(Submersion.class), 0, 20); // Exécution toutes les secondes (20 ticks)
    }

    private void checkWaterLevelIncrease() {
        if (timeElapsed % 300 == 0) { // toutes les 5 minutes (300 secondes)
            SeaLevelManager.getInstance().updateSeaLevel(SeaLevelManager.getInstance().getSealevel() + 1);
            System.out.println("Le niveau d'eau a monté !");

        }
    }

    private void triggerRandomEvents() {
        if (timeElapsed % 300 == 0) { // toutes les 5 minutes (300 secondes)
            double random = Math.random();
            if (random < 0.05) {
                System.out.println("Un événement aléatoire a eu lieu !");
                if(ScenarioPluieAcide&&!PluieAcideActivated) {
                    PluieAcide.PluieAcide();
                }
            }
        }

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
        gameTimer.cancel(); // Arrête le timer
        System.out.println("La partie est terminée. Merci d'avoir joué !");
    }


    public void pauseGame() {
        System.out.println("Game paused");
    }

    public void resumeGame() {
        System.out.println("Game resumed");
    }

    public void setScenarioPluieAcide(boolean b) {
        ScenarioPluieAcide = b;
    }

    public boolean isPluieAcide() {
        return this.ScenarioPluieAcide;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }
}
