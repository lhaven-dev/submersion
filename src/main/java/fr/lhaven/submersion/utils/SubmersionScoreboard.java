package fr.lhaven.submersion.utils;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class SubmersionScoreboard {

    private static SubmersionScoreboard instance;

    // Variables pour le scoreboard
    private int waterLevel;
    private int remainingPlayers;
    private String formattedTime;

    private SubmersionScoreboard() {
        // Constructeur privé pour le singleton
        this.waterLevel = 0; // Valeur initiale
        this.remainingPlayers = 0;
        this.formattedTime = "00:00"; // Temps initial
    }

    public static SubmersionScoreboard getInstance() {
        if (instance == null) {
            instance = new SubmersionScoreboard();
        }
        return instance;
    }

    public void initializeScoreboard(Player player) {
        waterLevel = SeaLevelManager.getInstance().getSealevel();
        remainingPlayers = PlayerManager.getInstance().getAlivePlayersCount();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("submersion", "dummy", ChatColor.GREEN + "SUBMERSION");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        rebuildScoreboard(scoreboard,player);
        player.setScoreboard(scoreboard);
    }

    // Méthode pour reconstruire le scoreboard
    public void rebuildScoreboard(Scoreboard scoreboard, Player player) {

        Objective objective = scoreboard.getObjective("submersion");
        if (objective != null) {
            // Réinitialiser les scores
            for (String entry : scoreboard.getEntries()) {
                scoreboard.resetScores(entry);
            }
            objective.getScore(ChatColor.YELLOW + "Temps passé : " + ChatColor.WHITE + formattedTime).setScore(4);
            objective.getScore(ChatColor.AQUA + "Niveau d'eau : " + ChatColor.WHITE + waterLevel).setScore(3);
            objective.getScore(ChatColor.RED + "Joueurs Restant : " + ChatColor.WHITE + remainingPlayers).setScore(2);
            objective.getScore(ChatColor.GOLD + "Nombre de kill : " + ChatColor.WHITE + PlayerManager.getInstance().getPlayerData(player.getUniqueId()).getKills()).setScore(1);

        }
    }



    // Méthodes pour mettre à jour les variables
    public void updateWaterLevel(int newWaterLevel) {
        this.waterLevel = newWaterLevel;
        updateAllScoreboards();
    }
    public static String formatTime(int seconds) {
        int hours = seconds / 3600; // Calculer les heures
        int minutes = (seconds % 3600) / 60; // Calculer les minutes restantes
        int remainingSeconds = seconds % 60; // Calculer les secondes restantes
        // Formater le temps avec des zéros devant si nécessaire
        return String.format("%02dh%02dm%02ds", hours, minutes, remainingSeconds);
    }
    public void updateRemainingPlayers(int newRemainingPlayers) {
        this.remainingPlayers = newRemainingPlayers;
        updateAllScoreboards();
    }

    public void updateTime(int time) {
        this.formattedTime = formatTime(time);
        updateAllScoreboards();
    }



    // Méthode pour mettre à jour tous les scoreboards
    private void updateAllScoreboards() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            rebuildScoreboard(player.getScoreboard(),player);
        }
    }
}
