package fr.lhaven.submersion.utils;

import org.bukkit.Location;

import java.nio.channels.ClosedChannelException;
import java.util.HashMap;
import java.util.UUID;

public class CreateStructure {

    private static CreateStructure instance;
    private Location corner1;
    private Location corner2;
    // Private constructor pour éviter d'instancier cette classe en dehors de celle-ci
    private CreateStructure() {}

    // Méthode pour récupérer l'instance du Singleton
    public static CreateStructure getInstance() {
        if (instance == null) {
            instance = new CreateStructure();
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



    // Méthode pour définir les coins pour un joueur


    // Méthode pour récupérer les coins d'un joueur


}
