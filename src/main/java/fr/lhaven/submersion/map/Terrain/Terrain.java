package fr.lhaven.submersion.map.Terrain;

import org.bukkit.Location;

public abstract class Terrain {

    protected int borderSize;
    protected String mapName;


    public Terrain () {
    }

    public abstract void generateTerrain();

    public abstract void generateBorder();

    public abstract int getBorderSize();

    public abstract String getMapName();

}
