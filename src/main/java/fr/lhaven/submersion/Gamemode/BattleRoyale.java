package fr.lhaven.submersion.Gamemode;


import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.team.Team;

import java.util.List;

public class BattleRoyale extends GameMode {

    public BattleRoyale() {
    }

    @Override
    public void randomizeTeams() {

    }

    @Override
    public void ConfigurationGame() {

    }

    @Override
    public void ChooseMap() {

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