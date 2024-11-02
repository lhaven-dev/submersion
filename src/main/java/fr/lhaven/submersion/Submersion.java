package fr.lhaven.submersion;
import fr.lhaven.submersion.commands.CreateGameCommand;
import fr.lhaven.submersion.listener.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Submersion extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Submersion is enable");


        this.getCommand("creategame").setExecutor(new CreateGameCommand());

        getServer().getPluginManager().registerEvents(new OnBlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new OnChestListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerKillListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Submersion is disable");

    }
}
