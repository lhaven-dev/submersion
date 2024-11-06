package fr.lhaven.submersion.scenario;

import fr.lhaven.submersion.scenario.scenarios.PluieAcide;
import fr.lhaven.submersion.scenario.scenarios.Scenario;
import fr.lhaven.submersion.scenario.scenarios.Zombie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenarioManager {
    private static ScenarioManager instance;
    private Map<ScenarioList, Boolean> scenarioList;

    private List<Scenario> scenarios;

    private ScenarioManager() {
        scenarioList = new HashMap<>();
        initializeScenarios();
    }

    public static ScenarioManager getInstance() {
        if (instance == null) {
            instance = new ScenarioManager();
        }
        return instance;
    }

    private void RunScenarios() {

        // ici le runnable des scénario
    }
    private void initializeScenarios() {
        for (ScenarioList scenario : ScenarioList.values()) {

            switch (scenario) {
                case ACID_RAIN:
                    scenarios.add(new PluieAcide());
                    break;
                case TORNADO:
                    scenarioList.put(scenario, false);
                    break;
                case EARTHQUAKE:
                    scenarioList.put(scenario, false);
                    break;
                case TSUNAMI:
                    scenarioList.put(scenario, false);
                    break;
                case METEORITE:
                    scenarioList.put(scenario, false);
                    break;
                case NUCLEAR:
                    scenarioList.put(scenario, false);
                    break;
                case ZOMBIE:
                    scenarios.add(new Zombie());
                    break;
                case ALIEN:
                    scenarioList.put(scenario, false);
                    break;
                case BLIZARD:
                    scenarioList.put(scenario, false);
                    break;
                default:
                    scenarioList.put(scenario, false);
                    break;
            }
        }


    }

    public void triggerScenario() {
        for (Scenario scenario : scenarios) {
            if (scenario.getIsActive()) {
                scenario.RunScenario();
            }
        }
    }
}

