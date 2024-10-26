package fr.lhaven.submersion.gui;


public enum MenuType {
    CHOICE_GAMEMODE("Submersion_ChoiceGamemode"),
    CHOICE_MAP("Submersion_ChoiceMap"),
    CHOICE_PLAYER("Submersion_ChoicePlayer"),
    MENU_PRINCIPAL("Submersion_MenuPrincipal");

    private final String metaKey;

    MenuType(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaKey() {
        return metaKey;
    }

    // Méthode pour obtenir l'enum à partir d'une valeur de métadonnée
    public static MenuType fromMetaKey(String metaKey) {
        for (MenuType type : values()) {
            if (type.getMetaKey().equals(metaKey)) {
                return type;
            }
        }
        return null; // Ou lever une exception si besoin
    }
}

