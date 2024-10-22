package fr.lhaven.submersion;
import fr.lhaven.submersion.commands.AddPlayersToTeamCommand;
import fr.lhaven.submersion.commands.CreateTeamCommand;
import fr.lhaven.submersion.commands.SetSeaLevelCommand;
import fr.lhaven.submersion.commands.SetBorderCommand;
import fr.lhaven.submersion.listener.BlockBreakListener;
import fr.lhaven.submersion.listener.OnChestListener;
import fr.lhaven.submersion.listener.PlayerJoinListener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Submersion extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Submersion is enable");


        this.getCommand("raiseSeaLevel").setExecutor(new SetSeaLevelCommand());
        this.getCommand("SetBorderSize").setExecutor(new SetBorderCommand());
        this.getCommand("CreateTeam").setExecutor(new CreateTeamCommand());
        this.getCommand("AddPlayerToTeam").setExecutor(new AddPlayersToTeamCommand());

      //  getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new OnChestListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Submersion is disable");

    }
}
