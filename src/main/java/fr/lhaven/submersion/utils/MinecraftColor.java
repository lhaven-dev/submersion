package fr.lhaven.submersion.utils;

public class MinecraftColor {

    public enum Color {
        WHITE("Blanc", "#FFFFFF"),
        ORANGE("Orange", "#F9801D"),
        MAGENTA("Magenta", "#C74EBD"),
        LIGHT_BLUE("Bleu clair", "#3AB3DA"),
        YELLOW("Jaune", "#FED83D"),
        LIME("Vert lime", "#80C71F"),
        PINK("Rose", "#F38BAA"),
        GRAY("Gris", "#474F52"),
        LIGHT_GRAY("Gris clair", "#9D9D97"),
        CYAN("Cyan", "#169C9C"),
        PURPLE("Violet", "#8932B8"),
        BLUE("Bleu", "#3C44AA"),
        BROWN("Marron", "#835432"),
        GREEN("Vert", "#5E7C16"),
        RED("Rouge", "#B02E26"),
        BLACK("Noir", "#1D1D21"),
        GOLD("Or", "#FFD700"),
        SILVER("Argent", "#C0C0C0");

        private final String name;
        private final String hexCode;

        // Constructeur pour les couleurs
        Color(String name, String hexCode) {
            this.name = name;
            this.hexCode = hexCode;
        }

        // Récupère le nom de la couleur
        public String getName() {
            return name;
        }

        // Récupère le code hexadécimal de la couleur
        public String getHexCode() {
            return hexCode;
        }
    }

  }
