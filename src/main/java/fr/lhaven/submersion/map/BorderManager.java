package fr.lhaven.submersion.map;

import org.bukkit.Bukkit;

public class BorderManager {
    // Instance statique unique de la classe
    private static BorderManager instance;

    private int borderSize;

    private BorderManager() {
        this.borderSize = 0; // ou une autre valeur par défaut
    }

    public static BorderManager getInstance() {
        if (instance == null) {
            instance = new BorderManager();
        }
        return instance;
    }

    // Méthode pour obtenir la taille de la bordure
    public int getBorderSize() {
        return borderSize;
    }

    // Méthode pour définir la taille de la bordure
    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        setMinecraftBorder(this.borderSize);
    }

    private void setMinecraftBorder(int size) {
        Bukkit.getWorld("world").getWorldBorder().setSize(size);}


}