package fr.lhaven.submersion.Gamemode;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.map.Terrain;
import fr.lhaven.submersion.players.team.Team;

import java.util.List;

public abstract class GameMode {

    protected List<PlayerData> players;
    protected int maxPlayers;
    protected boolean isStarted;
    protected boolean isFinished;
    protected boolean isPaused;
    protected boolean isRunning;
    protected boolean hasTeams;

    protected Terrain Terrain;

    protected int timeElapsed;

    protected int timeLeft;

    protected int timeLimit;

    protected PlayerData PlayerWin;

    protected List<PlayerData> PlayerList;

    protected List<PlayerData> PlayerAliveList;

    protected List<PlayerData> PlayerDeadList;

    protected List<PlayerData> PlayerSpectatorList;

    protected List<Team> TeamList;


    public GameMode() {


        // cette classe sert de template pour tout les modes de jeux , l'idée c'est que dedans y'aient les methodes communes
        // ajout de players , demarage de la partie , configuration des events , configuration des parametres , etc
    }

    // Méthode à implémenter par chaque mode de jeu pour démarrer

    public abstract void randomizeTeams();

    public abstract void ConfigurationGame();

    public abstract void ChooseMap();

    public abstract void addplayerToGame(PlayerData player);
    public abstract void addplayerToTeam(PlayerData player, Team team);

    public abstract void removePlayerFromGame(PlayerData player);

    public abstract void removePlayerFromTeam(PlayerData player, Team team);

    public abstract void addspectatorToGame(PlayerData player);

    public abstract void removeSpectatorFromGame(PlayerData player);
    public abstract void startGame();

    // Méthode pour ajouter un joueur


    // Méthode pour gérer les paramètres spécifiques à chaque mode de jeu
    public abstract void configureParameters();
}