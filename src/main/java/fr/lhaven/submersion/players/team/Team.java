package fr.lhaven.submersion.players.team;
import fr.lhaven.submersion.players.PlayerData;
import java.util.HashSet;
import java.util.Set;

public class Team {
    private final String name;
    private final Set<PlayerData> players = new HashSet<>();

    public Team(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public boolean hasPlayer(PlayerData playerData) {
        return players.contains(playerData);
    }

    public void addPlayer(PlayerData playerData) {
        players.add(playerData);
    }

    public void removePlayer(PlayerData playerData) {
        players.remove(playerData);
    }

    public Set<PlayerData> getPlayers() {
        return players;
    }
}
