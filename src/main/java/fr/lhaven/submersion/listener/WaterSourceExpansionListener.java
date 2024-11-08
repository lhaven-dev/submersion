package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.utils.BorderManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import static java.lang.Thread.sleep;

public class WaterSourceExpansionListener implements Listener {

    @EventHandler
    public void onWaterExpand(BlockFromToEvent event) {
        int borderSize = BorderManager.getInstance().getBorderSize() / 2;

        // Vérifie que c'est de l'eau qui se propage
        if (event.getBlock().getType() == Material.WATER) {
            Block block = event.getBlock();
            Block toBlock = event.getToBlock();
        // Vérifie si la propagation est dans les limites définies
       //     if (toBlock.getX() <= borderSize && toBlock.getX() >= -borderSize
       //             && toBlock.getZ() <= borderSize && toBlock.getZ() >= -borderSize) {


                    toBlock.setType(Material.WATER);
                    // Vérification des blocs autour (nord, sud, est, ouest)
                    Block northBlock = toBlock.getRelative(BlockFace.NORTH);
                    Block southBlock = toBlock.getRelative(BlockFace.SOUTH);
                    Block eastBlock = toBlock.getRelative(BlockFace.EAST);
                    Block westBlock = toBlock.getRelative(BlockFace.WEST);
                    Block downBlock = toBlock.getRelative(BlockFace.DOWN);

                    Block sourcenorthBlock = block.getRelative(BlockFace.NORTH);
                    Block sourcesouthBlock = block.getRelative(BlockFace.SOUTH);
                    Block sourceeastBlock = block.getRelative(BlockFace.EAST);
                    Block sourcewestBlock = block.getRelative(BlockFace.WEST);
                    Block sourcedownBlock = block.getRelative(BlockFace.DOWN);


                    // Remplacement des blocs d'air par de l'eau
                    if (northBlock.getType() == Material.AIR) {
                        northBlock.setType(Material.WATER);
                    }
                    if (southBlock.getType() == Material.AIR) {
                        southBlock.setType(Material.WATER);
                    }
                    if (eastBlock.getType() == Material.AIR) {
                        eastBlock.setType(Material.WATER);
                    }
                    if (westBlock.getType() == Material.AIR) {
                        westBlock.setType(Material.WATER);
                    }
                    if(downBlock.getType() == Material.AIR) {
                        downBlock.setType(Material.WATER);
                    }



                    // Remplacement des blocs d'air par de l'eau
                    if (sourcenorthBlock.getType() == Material.AIR) {
                        sourcenorthBlock.setType(Material.WATER);
                    }
                    if (sourcesouthBlock.getType() == Material.AIR) {
                        sourcesouthBlock.setType(Material.WATER);
                    }
                    if (sourceeastBlock.getType() == Material.AIR) {
                        sourceeastBlock.setType(Material.WATER);
                    }
                    if (sourcewestBlock.getType() == Material.AIR) {
                        sourcewestBlock.setType(Material.WATER);
                    }
                    if(sourcedownBlock.getType() == Material.AIR) {
                        sourcedownBlock.setType(Material.WATER);
                    }
                    // Répandre l'eau dans le bloc cible


                toBlock.setType(Material.WATER);
            //
        } }
    //}
    }


