package fr.lhaven.submersion.players.team;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;

public class TeamManager {
    private static TeamManager instance;
    private Map<String, Team> teams;
    private TeamManager() {

        teams = new HashMap<>(); // On initialise la map des équipes
    }

    public static TeamManager getInstance() {
        if (instance == null) {
            instance = new TeamManager();
        }
        return instance;
    }

    public void createTeam(String name) {
        if (!teams.containsKey(name)) {
            teams.put(name, new Team(name));
            System.out.println("L'équipe '" + name + "' a été créée avec succès.");
        } else {
            System.out.println("L'équipe '" + name + "' existe déjà, aucune action effectuée.");
        }    }

    public void addPlayerToTeam(Player player, String teamName) {

        Team team = TeamManager.getInstance().getTeam(teamName);

        if (team == null || !teams.containsKey(team.getName())) {
            System.out.println("L'équipe '" + team.getName() + "' n'existe pas, aucune action effectuée.");
            return;
        }

        PlayerData playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId());
        if (playerData != null) {
            playerData.setTeam(team);
            team.addPlayer(playerData);
            System.out.println("Le joueur '" + player.getName() + "' a été ajouté à l'équipe '" + team.getName() + "'.");
        } else {
            System.out.println("Le joueur avec l'UUID " + player.getUniqueId() + " n'existe pas.");
        }
    }

    public Team getTeam(String name) {
        Team team = teams.get(name);

        // Vérifie si l'équipe existe
        if (team == null) {
            System.out.println("L'équipe avec le nom '" + name + "' n'existe pas.");
        }

        return team; // retourne null si l'équipe n'existe pas
    }

    public Map<String,Team> getTeams() {
        return teams;
    }

    public void removePlayerFromTeam(Player player, Team team) {
        if (team == null || !teams.containsKey(team.getName())) {
            System.out.println("L'équipe avec le nom '" + team.getName() + "' n'existe pas, aucune action effectuée.");
            return; // Sortir de la méthode si l'équipe n'existe pas
        }

        PlayerData playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId());

        // Vérifie si le joueur existe
        if (playerData == null) {
            System.out.println("Le joueur avec l'UUID " + player.getUniqueId() + " n'existe pas.");
            return; // Sortir de la méthode si le joueur n'existe pas
        }

        // Vérifie si le joueur est dans l'équipe avant de le retirer
        if (!team.hasPlayer(playerData)) {
            System.out.println("Le joueur '" + player.getName() + "' n'est pas dans l'équipe '" + team.getName() + "'.");
            return; // Sortir de la méthode si le joueur n'est pas dans l'équipe
        }

        // Retire le joueur de l'équipe
        team.removePlayer(playerData);
        System.out.println("Le joueur '" + player.getName() + "' a été retiré de l'équipe '" + team.getName() + "'.");    }
}

