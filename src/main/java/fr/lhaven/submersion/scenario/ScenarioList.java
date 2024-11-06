package fr.lhaven.submersion.scenario;

public enum ScenarioList {
    ACID_RAIN("Pluie acide"),
    BLIZARD("BLIZARD"),
    EARTHQUAKE("Tremblement de terre"),
    TORNADO("Tornade"),
    METEORITE("Météorite"),
    TSUNAMI("Tsunami"),
    NUCLEAR("Nucléaire"),
    ZOMBIE("Zombie"),
    ALIEN("Alien"),
    CYBER("Cyber"),
    LARGUAGE("Larguage"),

    APOCALYPSE("Apocalypse");


    private final String name;

    ScenarioList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
