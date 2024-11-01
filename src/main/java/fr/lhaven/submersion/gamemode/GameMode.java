package fr.lhaven.submersion.gamemode;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.map.Terrain.Terrain;

import java.util.List;
import java.util.UUID;

public abstract class GameMode {

    protected boolean isStarted;
    protected boolean isFinished;
    protected boolean isRunning;
    protected Terrain terrain;

    protected int timeElapsed;






    public GameMode() {

    }
    public abstract void ChooseMap(String mapName);

    public abstract void addplayerToGame(UUID uuid);

    public abstract void addspectatorToGame(UUID uuid);

    public abstract void startGame();

    // Méthode pour ajouter un joueur


    // Méthode pour gérer les paramètres spécifiques à chaque mode de jeu

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void endGame() {
        isFinished = true;
        isRunning = false;
    }
}