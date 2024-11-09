package fr.lhaven.submersion.utils.Structure;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StructureSaver {
    private static final Gson gson = new Gson();

    // Sauvegarde de la structure, en excluant l'air, et en sauvegardant les coins
    public static void saveStructureToJson(String structureName, List<Structure.StructureBlockData> structureData, Location structureLocation, Location corner1, Location corner2) {
        try (FileWriter writer = new FileWriter("plugins/Submersion/structures/" + structureName + ".json")) {
            JsonObject structureObject = new JsonObject();

            // Sauvegarder les coins
            structureObject.addProperty("corner1X", corner1.getX());
            structureObject.addProperty("corner1Y", corner1.getY());
            structureObject.addProperty("corner1Z", corner1.getZ());

            structureObject.addProperty("corner2X", corner2.getX());
            structureObject.addProperty("corner2Y", corner2.getY());
            structureObject.addProperty("corner2Z", corner2.getZ());


            structureObject.add("blocks", gson.toJsonTree(structureData));

            gson.toJson(structureObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Chargement de la structure depuis le fichier JSON
    public static Structure.StructureData loadStructureFromJson(String structureName) {
        try (FileReader reader = new FileReader("plugins/Submersion/structures/" + structureName + ".json")) {
            JsonObject structureObject = gson.fromJson(reader, JsonObject.class);

            // Récupérer les coins
            double corner1X = structureObject.get("corner1X").getAsDouble();
            double corner1Y = structureObject.get("corner1Y").getAsDouble();
            double corner1Z = structureObject.get("corner1Z").getAsDouble();

            double corner2X = structureObject.get("corner2X").getAsDouble();
            double corner2Y = structureObject.get("corner2Y").getAsDouble();
            double corner2Z = structureObject.get("corner2Z").getAsDouble();

            // Créer les objets Location pour les coins
            Location corner1 = new Location(null, corner1X, corner1Y, corner1Z);  // Remplacer 'null' par le monde approprié
            Location corner2 = new Location(null, corner2X, corner2Y, corner2Z);  // Remplacer 'null' par le monde approprié

            // Charger les blocs
            Type listType = new TypeToken<List<Structure.StructureBlockData>>() {}.getType();
            List<Structure.StructureBlockData> blocks = gson.fromJson(structureObject.get("blocks"), listType);

            // Retourner un nouvel objet StructureData avec les coins et les blocs
            return new Structure.StructureData(blocks,corner1, corner2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
