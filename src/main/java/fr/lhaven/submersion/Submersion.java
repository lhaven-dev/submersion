package fr.lhaven.submersion;
import fr.lhaven.submersion.commands.CreateGame;
import fr.lhaven.submersion.commands.StructGenCommand;
import fr.lhaven.submersion.commands.StructGenReloadCommand;
import fr.lhaven.submersion.listener.*;
import fr.lhaven.submersion.utils.Structure.StructureManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Submersion extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic


        this.getCommand("structGenReload").setExecutor(new StructGenReloadCommand());
        this.getCommand("structGen").setExecutor(new StructGenCommand());
        this.getCommand("creategame").setExecutor(new CreateGame());

        getServer().getPluginManager().registerEvents(new OnBlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new OnChestListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new OnElytraUseListener(), this);
        getServer().getPluginManager().registerEvents(new WaterSourceExpansionListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Submersion is disable");

    }
}
