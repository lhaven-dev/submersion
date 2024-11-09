package fr.lhaven.submersion.scenario.scenarios;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.utils.BorderManager;
import fr.lhaven.submersion.utils.SeaLevelManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Blizzard extends Scenario {
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


        SeaLevelManager.getInstance().setBlizzard(true);

        new BukkitRunnable() {
            @Override
            public void run() {
                // Désactive le blizzard
                SeaLevelManager.getInstance().setBlizzard(false);
            }
        }.runTaskLater(Submersion.getPlugin(Submersion.class), 1200L); // 1200L = 10 minutes en ticks
    }

    }




