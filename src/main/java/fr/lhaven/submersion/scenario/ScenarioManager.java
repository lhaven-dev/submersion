package fr.lhaven.submersion.scenario;

import java.util.HashMap;
import java.util.Map;

public class ScenarioManager {
    private static ScenarioManager instance;
    private Map<String, Boolean> scenarios;

    private Map<String,Scenario> scenarioMap = new HashMap<>();

    // Constructeur privé pour empêcher l'instanciation
    private ScenarioManager() {
        scenarios = new HashMap<>();
        initializeScenarios();
    }

    public static ScenarioManager getInstance() {
        if (instance == null) {
            instance = new ScenarioManager();
        }
        return instance;
    }

    private void initializeScenarios() {
        scenarios.put("Pluie acide", false);
        // Initialiser d'autres scénarios...
    }

    public boolean getScenarioStatus(String scenario) {
        return scenarios.getOrDefault(scenario, false);
    }


    public void activateScenario(String scenario) {
        if (scenarios.containsKey(scenario)) {
            scenarios.put(scenario, true);
            System.out.println("Scénario activé : " + scenario);
            if (scenario.equals("Pluie acide")) {
                PluieAcide.toggleScenario(true); // Démarre la pluie acide
            }
        }
    }

    public void deactivateScenario(String scenario) {
        if (scenarios.containsKey(scenario)) {
            scenarios.put(scenario, false);
            System.out.println("Scénario désactivé : " + scenario);
            if (scenario.equals("Pluie acide")) {
                PluieAcide.toggleScenario(false); // Désactive la pluie acide
            }
        }
    }

}

