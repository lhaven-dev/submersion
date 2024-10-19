package fr.lhaven.submersion.border;

import org.bukkit.Bukkit;

public class BorderManager {
    // Instance statique unique de la classe
    private static BorderManager instance;

    // Attribut pour la taille de la bordure
    private int borderSize;

    // Constructeur privé pour empêcher l'instanciation extérieure
    private BorderManager() {
        // Initialisation, si nécessaire
        this.borderSize = 0; // ou une autre valeur par défaut
    }

    // Méthode statique pour obtenir l'instance unique
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