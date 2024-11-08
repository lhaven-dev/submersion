package fr.lhaven.submersion.scenario.scenarios;

import fr.lhaven.submersion.utils.BorderManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;

import java.util.Random;

public class Cyber extends Scenario{
    private static boolean isActive = false; // Statut du scénario
    private static boolean hasAlreadyActivated  = false; // Statut de l'activation

    @Override
    public void startScenario() {

        if(hasAlreadyActivated||isActive){
            return;
        }
        Random rand = new Random();
        if(rand.nextInt(100) < 50){
            isActive = true;
            hasAlreadyActivated = true;
            RunScenario(); // Lancer le scénario

        }

    }
    @Override
    public void RunScenario() {
       for(Player player : Bukkit.getOnlinePlayers()){
           if(player.isDead()){
               continue;
           }
           Glow(player);
       }
    }

    private void Glow(Player player){
        player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.GLOWING, 20*5, 1));
    }


}
