package fr.lhaven.submersion.scenario.scenarios;

import fr.lhaven.submersion.utils.BorderManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;

import java.util.Random;

public class Alien extends Scenario {

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
        Random rand = new Random();
        int number = rand.nextInt(100);
        for (int i = 0; i < number; i++) {
            spawnAlien();
        }

    }
    private void spawnAlien() {
        Location loc = getlocation();
        if (new Random().nextBoolean()) {
            Bukkit.getWorld("world").spawnEntity(loc, EntityType.CAVE_SPIDER);
        } else {
            Spider spider = (Spider) Bukkit.getWorld("world").spawnEntity(loc, EntityType.CAVE_SPIDER);
            Skeleton skeleton = (Skeleton) Bukkit.getWorld("world").spawnEntity(loc, EntityType.SKELETON);
            spider.addPassenger(skeleton); // Place le squelette sur l'araignée        }
        }
    }

    private Location getlocation() {
        int size = BorderManager.getInstance().getBorderSize()/2;
        Random rand = new Random();
        int x = rand.nextInt(size);
        int z = rand.nextInt(size);
        int y = Bukkit.getServer().getWorld("world").getHighestBlockYAt(x, z);
        return new Location(null, x, y, z);
    }
}
