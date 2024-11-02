package fr.lhaven.submersion.game.gamemode;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.map.Terrain.Terrain;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.scenario.ScenarioManager;
import fr.lhaven.submersion.utils.SeaLevelManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public abstract class GameMode {
    private GameTimer gameTimer; // Classe séparée pour gérer le timer
    protected boolean isStarted;
    protected boolean isFinished;
    protected boolean isRunning;
    protected int timeElapsed;
    public GameMode() {
        this.isStarted = false;
        this.isFinished = false;
        this.isRunning = false;
        this.timeElapsed = 0;
    }

    public abstract void startGame();
    public abstract Terrain getCurrentMap();

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void incrementTimeElapsed() {
        timeElapsed++;
    }

    public void endGame() {
        isFinished = true;
        isRunning = false;
        if (gameTimer != null) {
            gameTimer.stop();
        }
    }

    protected void startGameTimer() {
        gameTimer = new GameTimer(this);
        gameTimer.runTaskTimer(Submersion.getPlugin(Submersion.class), 0, 20); // Exécution toutes les secondes (20 ticks)
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    // Classe interne pour gérer le timer du jeu
    private static class GameTimer extends BukkitRunnable {
        private final GameMode gameMode;

        public GameTimer(GameMode gameMode) {
            this.gameMode = gameMode;
        }

        @Override
        public void run() {
            if (gameMode.isRunning() && !gameMode.isFinished()) {
                gameMode.incrementTimeElapsed(); // Incrémente le temps
                gameMode.checkWaterLevelIncrease(); // Vérifie l'augmentation du niveau d'eau
                gameMode.triggerRandomEvents(); // Déclenche les événements aléatoires

            } else {
                this.cancel(); // Arrête le timer quand le jeu est fini
            }
        }

        public void stop() {
            this.cancel(); // Méthode pour arrêter le timer
        }
    }

    public void checkWaterLevelIncrease() {
        if (timeElapsed % 300 == 0) { // toutes les 5 minutes (300 secondes)
            SeaLevelManager.getInstance().updateSeaLevel(SeaLevelManager.getInstance().getSealevel() + 1);
            System.out.println("Le niveau d'eau a monté !");
        }
    }

    public void triggerRandomEvents() {
        if (timeElapsed % 300 == 0) { // toutes les 5 minutes (300 secondes)
            double random = Math.random();
            if (random < 0.05) {
                System.out.println("Un événement aléatoire a eu lieu !");
                if (ScenarioManager.getInstance().getScenarioStatus("pluie Acide") &&
                        !ScenarioManager.getInstance().getScenarioStatus("PluieAcideActivated")) {
                    // Code pour gérer l'événement de pluie acide
                }
            }
        }
    }
}
