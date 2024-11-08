package fr.lhaven.submersion;
import fr.lhaven.submersion.commands.CreateGameCommand;
import fr.lhaven.submersion.commands.StructGenCommand;
import fr.lhaven.submersion.listener.*;
import fr.lhaven.submersion.utils.StructGenReloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Submersion extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Submersion is enable");

        this.getCommand("structGenReload").setExecutor(new StructGenReloadCommand());
        this.getCommand("creategame").setExecutor(new CreateGameCommand());
        this.getCommand("structGen").setExecutor(new StructGenCommand());
        getServer().getPluginManager().registerEvents(new OnBlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new OnChestListener(new StructGenCommand()), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerKillListener(), this);
        getServer().getPluginManager().registerEvents(new OnElytraUseListener(), this);
        getServer().getPluginManager().registerEvents(new WaterSourceExpansionListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Submersion is disable");

    }
}
