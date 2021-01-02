package org.cultofclang.onelife;


import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class OneLifePlugin extends JavaPlugin {
    public static OneLifePlugin Instance;
    @Override
    public void onEnable() {
        getLogger().info("Their can only be one.");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MainListener(), this);
        pm.registerEvents(new BaseHunger(), this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new BaseHunger(), 1, 1);
        Instance = this;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        getLogger().info("hello");
        sender.sendMessage("i hate you");

        List<Entity> entities = sender.getServer().selectEntities(sender, "@e[dx=100,dy=100,dz=100]");
        for (Entity entity:
             entities) {
            sender.sendMessage("e: "+entity.toString());
            MobDisguise mobDisguise = new MobDisguise(DisguiseType.SNOWMAN);
            DisguiseAPI.disguiseToAll(entity, mobDisguise);
        }

        return super.onCommand(sender, command, label, args);


    }
}
