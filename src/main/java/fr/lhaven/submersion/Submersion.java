package fr.lhaven.submersion;

import fr.lhaven.submersion.commands.SetSeaLevelCommand;
import fr.lhaven.submersion.commands.SetBorderCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Submersion extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Submersion is enable");


        this.getCommand("raiseSeaLevel").setExecutor(new SetSeaLevelCommand());
        this.getCommand("SetBorderSize").setExecutor(new SetBorderCommand());



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Submersion is disable");

    }
}
