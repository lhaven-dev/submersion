package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.utils.BorderManager;
import fr.lhaven.submersion.utils.SeaLevelManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.ArrayList;
import java.util.List;

public class WaterSourceExpansionListener implements Listener {


    @EventHandler
    public void onWaterExpand(BlockFromToEvent event) {
        event.setCancelled(true);
    }
}
