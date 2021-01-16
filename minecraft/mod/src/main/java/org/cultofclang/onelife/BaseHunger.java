package org.cultofclang.onelife;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;



//TODO using wrong tool hurts. punching trees hurts.
public class BaseHunger implements Runnable, Listener {
    //40 is fast, but survivalable
    final float BASE_EXHAUSTION = 40f/(20*60*5);
    
    // spirting costs 0.1 per m in base game.
    final  float MOVEMENT_EXHAUSTION_PER_M = 0.01f;

    @Override
    public void run() {
        for (Player player:
                OneLifePlugin.Instance.getServer().getOnlinePlayers()) {

            player.setExhaustion(player.getExhaustion() + BASE_EXHAUSTION);
        }
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        Location playerLoc = player.getLocation();
        float distance = (float)event.getFrom().subtract(event.getTo()).length();
        float de = MOVEMENT_EXHAUSTION_PER_M * distance;
        player.setExhaustion(player.getExhaustion() + de);
    }
}
