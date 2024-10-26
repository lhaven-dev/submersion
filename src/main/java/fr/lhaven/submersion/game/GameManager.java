package fr.lhaven.submersion.game;

import fr.lhaven.submersion.gamemode.BattleRoyale;
import fr.lhaven.submersion.gamemode.GameMode;

public class GameManager {

    private GameMode gameMode;
    private String gamemodeName;
    private boolean gameStarted = false;
    private boolean gameCreated = false;

    private boolean gameEnded = false;

    private boolean gamePaused = false;

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

    public void setMap(String map) {
        System.out.println("Map set to " + map);
        if(gameMode != null)
        gameMode.ChooseMap(map);
    }

    public void createGame() {
        gameCreated = true;
        System.out.println("Game created");
    }

    public void setGameMode(String mode) {
        if(gameCreated)
        {
            if(gamemodeName != mode)
            {
                if (mode == "Battle Royale") {
                    this.gamemodeName = mode;
                    this.gameMode = new BattleRoyale();
                    System.out.println("Game mode set to " + mode);
                    System.out.println("Gamemode Choisi" + mode);
                }
                else if (mode == "Team vs All") {
                    //this.gamemodeName = mode;
                    //this.gameMode = new TeamVsAll();
                    //   System.out.println("Game mode set to " + mode);
                    //System.out.println("Gamemode Choisi" + mode);
                }
                else if (mode == "Team vs Team") {
                    //this.gamemodeName = mode;
                    //this.gameMode = new TeamVsTeam();
                    // System.out.println("Game mode set to " + mode);
                    // System.out.println("Gamemode Choisi" + mode);
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
    }

    public void endGame() {
        System.out.println("Game ended");
    }

    public void pauseGame() {
        System.out.println("Game paused");
    }

    public void resumeGame() {
        System.out.println("Game resumed");
    }
}
