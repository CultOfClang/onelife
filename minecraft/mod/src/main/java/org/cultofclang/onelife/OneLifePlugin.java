package org.cultofclang.onelife;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OneLifePlugin extends JavaPlugin {
    public static OneLifePlugin Instance;
    @Override
    public void onEnable() {
        getLogger().info("Their can only be one.");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MainListener(), this);
        Instance = this;

        var players = getServer().getOnlinePlayers();
        //getCommand("onelife").setExecutor(new );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        getLogger().info("hello");
        return super.onCommand(sender, command, label, args);
    }
}
