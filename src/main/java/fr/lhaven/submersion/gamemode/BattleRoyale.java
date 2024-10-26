package fr.lhaven.submersion.gamemode;


import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.map.Terrain.Island;
import fr.lhaven.submersion.map.Terrain.Terrain;
import fr.lhaven.submersion.map.Terrain.Volcano;
import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.team.Team;

public class BattleRoyale extends GameMode {

    private GameManager gameManager;

    public BattleRoyale() {
        gameManager = GameManager.getInstance();
    }

    @Override
    public void randomizeTeams() {

    }

    @Override
    public void ConfigurationGame() {

    }

    @Override
    public void ChooseMap(String mapName) {

        if(gameManager.isGameCreated())
        {
            System.out.println("Parti crée , choix la map possible ");
            if (mapName == "Volcano") {
                Terrain terrain = new Volcano();
                // Logique pour choisir la map
            }
            else if (mapName == "Island") {
                Terrain terrain = new Island();
                // Logique pour choisir la map
            }
            else {
                System.out.println("La partie n'a pas été créée");
            }

            // Logique pour choisir la map
        }
        else {
            System.out.println("La partie n'a pas été créée");
        }

    }

    @Override
    public void addplayerToGame(PlayerData player) {

    }

    @Override
    public void addplayerToTeam(PlayerData player, Team team) {

    }

    @Override
    public void removePlayerFromGame(PlayerData player) {

    }

    @Override
    public void removePlayerFromTeam(PlayerData player, Team team) {

    }

    @Override
    public void addspectatorToGame(PlayerData player) {

    }

    @Override
    public void removeSpectatorFromGame(PlayerData player) {

    }

    @Override
    public void startGame() {
        System.out.println("Démarrage du mode Battle Royale !");
        // Logique pour démarrer la partie Battle Royale (chaque joueur pour soi)
    }

    @Override
    public void configureParameters() {
        System.out.println("Pas d'équipes en Battle Royale.");
        // Aucune équipe à configurer, pas de paramètres spécifiques
    }
}