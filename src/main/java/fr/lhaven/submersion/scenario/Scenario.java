package fr.lhaven.submersion.scenario;

import org.checkerframework.common.returnsreceiver.qual.This;

public abstract class  Scenario {

    private static boolean isActive = false; // Statut du scénario
    private static boolean hasAlreadyActivated  = false; // Statut de l'activation

    public Scenario() {
        isActive = false;
        hasAlreadyActivated = false;
    }


    public abstract void startScenario();


    public  void toggleScenario(boolean activate) {
       isActive = activate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public abstract void RunScenario();

}

