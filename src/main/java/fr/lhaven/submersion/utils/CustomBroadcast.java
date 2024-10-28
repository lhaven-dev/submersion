package fr.lhaven.submersion.utils;
import org.bukkit.*;
import org.bukkit.entity.Player;


public class CustomBroadcast {
  public static void LooserBroadcast(Player player) {
        // Envoie le titre et le sous-titre à tous les joueurs
        player.sendTitle("Défaite", "Vous avez perdu", 10, 70, 20);
        player.sendMessage(ChatColor.DARK_RED + "Titre diffusé !");

  }

    public static void StartGameBroadcast(Player player) {
        // Envoie le titre et le sous-titre à tous les joueurs
        player.sendTitle("Début de la partie", "La partie commence", 10, 70, 20);
        player.sendMessage(ChatColor.DARK_GRAY + "Titre diffusé !");

    }

    public static void WinnerBroadcast(Player player) {
        // Envoie le titre et le sous-titre à tous les joueurs
        player.sendTitle("Victoire", "Vous avez gagné", 10, 70, 20);
        player.sendMessage(ChatColor.GREEN + "Titre diffusé !");
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 1, 1);
        player.spawnParticle(Particle.FIREWORK, player.getLocation(), 100, 00, 00, 00, 00);

    }

 }



