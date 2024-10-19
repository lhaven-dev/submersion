package fr.lhaven.submersion.players;

import fr.lhaven.submersion.players.Team;

import java.util.HashSet;
import java.util.List;

public class PlayerManager {
    // Instance unique de PlayerManager (singleton)
    private static PlayerManager instance;

    // Liste des joueurs

    public List<Team> teams;

    private List<Player> players;

    // Constructeur privé pour empêcher l'instanciation directe
    private PlayerManager() {

    }

    // Méthode pour obtenir l'instance unique de PlayerManager
    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    // Ajouter un joueur
    public void addPlayer(Player player) {
    }



    // Supprimer un joueur
    public void removePlayer(Player player) {
    }
//----------------------------------

    public void createTeam(String name) {
        Team team = new Team(name);
        teams.add(team);
    }

    public void removeTeam(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                teams.remove(team);
                break;
            }
        }
    }


    public void addPlayerToTeam(Player player, String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                team.addPlayer(player);
                break;
            }
        }
    }

}
