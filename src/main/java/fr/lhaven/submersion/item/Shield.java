package fr.lhaven.submersion.item;

import org.bukkit.Material;
import org.bukkit.block.data.type.Switch;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Shield {

    private static Shield instance;
    private ItemStack Smallshield;
    private ItemStack Mediumshield;
    private ItemStack Bigshield;




    private Shield() {
        SmallShield();
        MediumShield();
        BigShield();
    }


    public static Shield getInstance() {
        if (instance == null) {
            instance = new Shield();
        }
        return instance;
    }



    public  void GiveShield(Player player, TypeShield TypeShield)
    {
        switch (TypeShield) {
            case SMALL:
                player.getInventory().addItem(Smallshield);
                break;
            case MEDIUM:
                player.getInventory().addItem(Mediumshield);
                break;
            case LARGE:
                player.getInventory().addItem(Bigshield);
                break;
        }
    }


    public void UseShield(Player player, TypeShield typeShield) {
        int amplifier = 0;
        int absorption = 0;
        switch(typeShield) {
            case SMALL:
                amplifier = 0;
                absorption = 4;
                player.sendMessage("You used a small shield. Absorption: " + amplifier);
                ShieldUsing(player, amplifier, absorption, Smallshield);
                break;
            case MEDIUM:
                amplifier = 1;
                absorption = 8;
                player.sendMessage("You used a medium shield. Absorption: " + amplifier);
                ShieldUsing(player, amplifier, absorption, Mediumshield);
                break;
            case LARGE:
                amplifier = 2;
                absorption = 12;
                player.sendMessage("You used a large shield. Absorption: " + amplifier);
                ShieldUsing(player, amplifier, absorption, Bigshield);
                break;
        }
    }

    private void ShieldUsing(Player player, int amplifier, int absorption, ItemStack bigshield) {
        if(player.getPotionEffect(PotionEffectType.ABSORPTION) == null ||player.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier() < amplifier || player.getAbsorptionAmount() < absorption)
        {
            player.removePotionEffect(PotionEffectType.ABSORPTION);
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 999999, amplifier));
            player.getInventory().removeItem(bigshield);

        }
    }


    private void BigShield() {
       String name = "Big Shield";
       String description = "A Big shield that can block some damage";

        this.Bigshield = new ItemStack(Material.PAPER);  // Création de l'ItemStack pour le bouclier
        ItemMeta meta = this.Bigshield.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(description));
        meta.setCustomModelData(1);  // Assurez-vous que la texture est configurée pour le modèle 1
        this.Bigshield.setItemMeta(meta);
    }

    private void MediumShield() {
        String name = "Medium Shield";
        String description = "A Medium shield that can block some damage";

        this.Mediumshield = new ItemStack(Material.PAPER);  // Création de l'ItemStack pour le bouclier
        ItemMeta meta = this.Mediumshield.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(description));
        meta.setCustomModelData(1);  // Assurez-vous que la texture est configurée pour le modèle 1
        this.Mediumshield.setItemMeta(meta);
    }

    private void SmallShield() {
        String name = "Small Shield";
        String description = "A small shield that can block some damage";

        this.Smallshield = new ItemStack(Material.PAPER);  // Création de l'ItemStack pour le bouclier
        ItemMeta meta = this.Smallshield.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(description));
        meta.setCustomModelData(1);  // Assurez-vous que la texture est configurée pour le modèle 1
        this.Smallshield.setItemMeta(meta);
    }

    public ItemStack getSmallShield() {
        if (Smallshield == null) {
            SmallShield();
        }
        return Smallshield;
    }

    public ItemStack getMediumShield() {
        if (Mediumshield == null) {
            MediumShield();
        }
        return Mediumshield;
    }

    public ItemStack getBigShield() {
        if (Bigshield == null) {
            BigShield();
        }
        return Bigshield;
    }
}
