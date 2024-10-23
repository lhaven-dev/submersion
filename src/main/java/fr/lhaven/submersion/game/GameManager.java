package fr.lhaven.submersion.game;

public class GameManager {
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
        System.out.println("Game created");
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
