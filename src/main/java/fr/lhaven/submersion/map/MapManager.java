package fr.lhaven.submersion.map;

import fr.lhaven.submersion.map.Terrain.Island;
import fr.lhaven.submersion.map.Terrain.Terrain;

public class MapManager {
    private static MapManager instance;
    private Terrain terrain;

    // Constructeur privé pour le Singleton
    private MapManager() {
        // Initialisation du terrain
        this.terrain = new Island(); // On peut définir un terrain par défaut ici si nécessaire
    }

    // Méthode pour obtenir l'instance du Singleton
    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    // Méthode pour définir le terrain de la carte
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
        this.terrain.generateTerrain(); // Générer le terrain lorsque le terrain est défini
        this.terrain.generateBorder(); // Générer la bordure
        System.out.println("Terrain défini : " + terrain.getMapName());
    }

    // Méthode pour obtenir le terrain actuel
    public Terrain getTerrain() {
        return terrain;
    }

    // Méthode pour vérifier si un terrain est défini
    public boolean isTerrainSet() {
        return terrain != null;
    }

    // Méthode pour obtenir le nom de la carte
    public String getMapName() {
        return terrain != null ? terrain.getMapName() : "Aucune carte définie";
    }

    // Autres méthodes pertinentes pour gérer la carte peuvent être ajoutées ici
}
