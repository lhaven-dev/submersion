package fr.lhaven.submersion.caisse;

import org.bukkit.entity.Player;

public class CaisseManager {
    private static CaisseManager instance;

    private CaisseManager() {
    }

    public static CaisseManager getInstance() {
        if (instance == null) {
            instance = new CaisseManager();
        }
        return instance;
    }


    public void getLoot(TypeCaisse typeCaisse, Player player) {
        System.out.println("Loot");

        switch (typeCaisse) {
            case COMMON:
                player.sendMessage("Vous avez looté un item commun");
                break;
            case RARE:
                player.sendMessage("Vous avez looté un item rare");
                break;
            case EPIC:
                player.sendMessage("Vous avez looté un item Epique");
                break;
            case LEGENDARY:
                System.out.println("Loot légendaire");
                break;
        }
          }
}
