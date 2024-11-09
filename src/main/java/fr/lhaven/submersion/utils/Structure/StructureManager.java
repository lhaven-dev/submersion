package fr.lhaven.submersion.utils.Structure;

import org.bukkit.Location;

public class StructureManager {

    private static StructureManager instance;

    // Coins de la structure
    private Location corner1;
    private Location corner2;

    private StructureManager() {
    }

    public static StructureManager getInstance() {
        if (instance == null) {
            instance = new StructureManager();
        }
        return instance;
    }

    public void setCorner1(Location corner1) {
        this.corner1 = corner1;
    }

    public void setCorner2(Location corner2) {
        this.corner2 = corner2;
    }

    public Location getCorner1() {
        return corner1;
    }

    public Location getCorner2() {
        return corner2;
    }

    public boolean hasCorners() {
        return corner1 != null && corner2 != null;
    }
}
