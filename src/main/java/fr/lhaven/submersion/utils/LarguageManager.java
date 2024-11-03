package fr.lhaven.submersion.utils;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class LarguageManager {

    Random random ;

    private static LarguageManager instance;

    private PlayerManager playerManager;
    public static LarguageManager getInstance() {
        if (instance == null) {
            instance = new LarguageManager();
        }
        return instance;
    }
    private LarguageManager() {
    }

    private Location getlocation(int Border) {
        random = new Random();
            int halfZone = Border / 2;
        Location location;

        do {
            int x = random.nextInt(Border) - halfZone; // Cela donne des valeurs de -halfZone à halfZone
            int z = random.nextInt(Border) - halfZone; // Cela donne des valeurs de -halfZone à halfZone
            location = new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockYAt(x, z) + 100, z);
        } while (location.getBlock().isLiquid()); // continue jusqu'à ce qu'on trouve un bloc non liquide

        return location;
    }

    private Color GetRandomColor()
    {
        random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.fromRGB(r, g, b); // Retourne une couleur RGB

    }

    private void giveKit(Player player) {
        player.getInventory().clear();

        player.setHealth(20);  // Donne 20 points de vie au joueur
        player.setFoodLevel(20);  // Donne 20 points de nourriture au joueur
        player.setSaturation(20);  // Donne 20 points de saturation au joueur
        player.setGameMode(GameMode.SURVIVAL);  // Met le joueur en mode survie
        Color randomColor = GetRandomColor();
        // Créer le casque
        ItemStack casque = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta casqueMeta = (LeatherArmorMeta) casque.getItemMeta();
        casqueMeta.setColor(randomColor); // Appliquer la couleur
        casque.setItemMeta(casqueMeta);
        player.getInventory().setHelmet(casque); // Équiper le casque

        // Créer le pantalon
        ItemStack pantalon = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta pantalonMeta = (LeatherArmorMeta) pantalon.getItemMeta();
        pantalonMeta.setColor(randomColor); // Appliquer la même couleur
        pantalon.setItemMeta(pantalonMeta);
        player.getInventory().setLeggings(pantalon); // Équiper le pantalon

        // Créer les bottes
        ItemStack bottes = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bottesMeta = (LeatherArmorMeta) bottes.getItemMeta();
        bottesMeta.setColor(randomColor); // Appliquer la même couleur
        bottes.setItemMeta(bottesMeta);
        player.getInventory().setBoots(bottes); // Équiper les bottes


        ItemStack elytra = new ItemStack(Material.ELYTRA);
        // Réduit la durabilité pour que l’elytra soit presque usée
        elytra.setDurability((short) (elytra.getType().getMaxDurability() - 10)); // Par exemple, 10 points avant la rupture
        player.getInventory().setChestplate(elytra);
        // Épée en bois
        player.getInventory().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
        // 16 steaks
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
    }
     public void Start(PlayerData playerData) {
         Player player =playerData.getPlayer();

         // Vérifie si le joueur est en ligne, pas spectateur, et vivant
         if (player != null && player.isOnline() && playerData.isAlive() && !playerData.isSpectator()) {
             int border = BorderManager.getInstance().getBorderSize();
             Location location = getlocation(border);  // Génère une localisation dans la bordure
             giveKit(player);  // Donne l'équipement au joueur
             player.teleport(location);  // Téléporte le joueur
             // Diffuse le message de début de jeu pour le joueur
             CustomBroadcast.StartGameBroadcast(player);
         }
    }
}
