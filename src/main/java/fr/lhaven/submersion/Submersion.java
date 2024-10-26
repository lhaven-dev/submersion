package fr.lhaven.submersion;
import fr.lhaven.submersion.commands.CreateGameCommand;
import fr.lhaven.submersion.listener.OnChestListener;
import fr.lhaven.submersion.listener.PlayerJoinListener;
import fr.lhaven.submersion.listener.GuiListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Submersion extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Submersion is enable");


        this.getCommand("creategame").setExecutor(new CreateGameCommand());

      //  getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new OnChestListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Submersion is disable");

    }
}
