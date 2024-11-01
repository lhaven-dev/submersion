package fr.lhaven.submersion.utils; // Adapte le package selon ta structure

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.DisplaySlot;

public class Scoreboards {

    private  ScoreboardManager manager;
    private Scoreboard globalScoreboard;
    private Objective globalObjective;

    public Scoreboards() {
        this.manager = Bukkit.getScoreboardManager();
        setupGlobalScoreboard(); // Configure le scoreboard global à l'initialisation
    }

    // Configure le scoreboard global
    private void setupGlobalScoreboard() {
        globalScoreboard = manager.getNewScoreboard();
        globalObjective = globalScoreboard.registerNewObjective("globalScore", "dummy", "Submersion");
        globalObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    // Méthode pour configurer le scoreboard pour un joueur
    public void setupScoreboard(Player player) {
        player.setScoreboard(globalScoreboard);
    }

    // Mettre à jour les scores globaux
    public void updateGlobalScore(String entry, int score) {
        if (globalObjective != null) {
            globalObjective.getScore(entry).setScore(score);
        }
    }

    // Méthode pour mettre à jour le scoreboard d'un joueur
    public void refreshScoreboard(Player player) {
        player.setScoreboard(globalScoreboard);
    }
}
