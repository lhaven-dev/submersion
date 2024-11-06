package fr.lhaven.submersion.scenario.scenarios;

import fr.lhaven.submersion.utils.BorderManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.Locale;
import java.util.Random;

public class Zombie extends Scenario {
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
            spawnZombie();
        }

    }
private void spawnZombie() {
                Location loc = getlocation();
        Bukkit.getWorld("world").spawnEntity(loc, EntityType.ZOMBIE);
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
