package com.hectorhw.capnether;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CapNether extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("registered events");
        getLogger().info("started CapNether");
    }

    public void processPlayer(Player player){
        //only apply to those who can be damaged
        if(player.getGameMode()== GameMode.ADVENTURE || player.getGameMode()==GameMode.SURVIVAL){
            //only those who are in nether
            if(player.getWorld().getName().endsWith("nether")){
                int y = player.getLocation().getBlockY();
                if(y>=128){ //bedrock in nether is at 128
                    player.damage(1.0);
                }
            }
        }
    }

    @EventHandler
    public void onWorldTick(ServerTickStartEvent event){
        if (event.getTickNumber()%20==0){ //execute every second
            for(Player p: getServer().getOnlinePlayers()){
                processPlayer(p);
            }
        }
    }

}
