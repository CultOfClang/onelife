package org.cultofclang.onelife;


import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class OneLifePlugin extends JavaPlugin implements Listener {
    public static OneLifePlugin Instance;
    public Player currentPlayer;


    public HashSet<Player> deadPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        getLogger().info("Their can only be one.");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MainListener(), this);
        pm.registerEvents(new BaseHunger(), this);
        pm.registerEvents(this, this);

        for (World world:
             getServer().getWorlds()) {
            world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 3);
        }


        getServer().getScheduler().scheduleSyncRepeatingTask(this, new BaseHunger(), 1, 1);
        Instance = this;
    }

    @EventHandler
    public  void  onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(currentPlayer == null && !deadPlayers.contains(player)){
            makeIntoHuman(player);
        }
        else{
            makeIntoMonster(player);
        }
    }

    private Player findNextPlayer(){
        Player bestPlayer = null;
        for (Player player:
                getServer().getOnlinePlayers()) {
            bestPlayer = player;
        }
        return null;
    }

    private boolean makeIntoMonster(Player player){
        //if(currentPlayer == player)  return false;
        player.sendMessage("e: "+player.toString());
        MobDisguise mobDisguise = new MobDisguise(DisguiseType.SNOWMAN);
        DisguiseAPI.disguiseIgnorePlayers(player, mobDisguise, player);
        return true;
    }

    private  void makeIntoHuman(Player player){
        if(currentPlayer!=null)
            throw new RuntimeException("You suck!!!");
        getServer().broadcastMessage(player+ " is now Player 1!");
        currentPlayer = player;
    }

    @EventHandler
    public  void  onPlayerSpawn(PlayerRespawnEvent event){
        makeIntoMonster(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player died = event.getEntity();
        Player killer = died.getKiller();
        if(died == currentPlayer){
            // need to ban them if they died
            deadPlayers.add(died);
            currentPlayer = null;


            Player nextPlayer = null;

            if(killer!= null && died != killer && !deadPlayers.contains(killer)){
                nextPlayer = killer;
            }
            else {
                nextPlayer = findNextPlayer();
            }
            if(nextPlayer!=null){
                makeIntoHuman(nextPlayer);
            }
        }
        //died.kickPlayer(event.getDeathMessage()+" Please reconnect to continue the fight!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        getLogger().info("hello");
        sender.sendMessage("i hate you");

        for (Player player:
                sender.getServer().getOnlinePlayers()) {
            makeIntoMonster(player);
        }

        return super.onCommand(sender, command, label, args);


    }
}
