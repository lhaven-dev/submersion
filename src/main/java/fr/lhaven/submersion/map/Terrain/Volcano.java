package fr.lhaven.submersion.map.Terrain;

import fr.lhaven.submersion.utils.BorderManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Volcano extends Terrain {

    public Volcano() {
        this.mapName = "Volcano";
        this.borderSize = 1000;
        generateBorder();

    }

    @Override
    public void generateTerrain() {
        // La ou la génération du terrain se fait
        // récuperer la map en ressources et la générer
    }

    @Override
    public void generateBorder() {
        BorderManager.getInstance().setBorderSize(this.borderSize);
        // la ou on apelle la border pour la set a la bonne taille
    }

    @Override
    public int getBorderSize() {
        return this.borderSize;
    }

    @Override
    public String getMapName() {
        return this.mapName;
    }
}
