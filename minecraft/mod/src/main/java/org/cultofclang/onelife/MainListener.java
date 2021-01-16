package org.cultofclang.onelife;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.event.block.BlockGrowEvent;

import org.bukkit.event.player.PlayerBucketEvent;

public class MainListener implements Listener {
    @EventHandler
    public void onBucketEvent(BlockGrowEvent event){
        boolean isOkay = event.getBlock().getLightFromSky() >= 9;
        if(!isOkay) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onStructureGrow(StructureGrowEvent event){

        Location location = event.getLocation();
        boolean isOkay = location.getBlock().getLightFromSky() >= 9;
        isOkay |= event.isFromBonemeal();
        //OneLifePlugin.Instance.getLogger().info("grow baby "+ isOkay);

        if(!isOkay) {
            event.setCancelled(true);
        }
    }
}
