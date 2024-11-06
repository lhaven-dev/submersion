package fr.lhaven.submersion.scenario.scenarios;

import org.bukkit.Bukkit;

import java.util.Random;

public class PluieAcide extends Scenario {
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
            Bukkit.getWorld("world").setStorm(true);
            RunScenario(); // Lancer le scénario

        }
        }


    @Override
    public void RunScenario() {
        // Ici le code du scénario

    }
}
