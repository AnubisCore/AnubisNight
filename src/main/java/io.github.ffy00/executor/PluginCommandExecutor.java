/*
 * Copyright 2016 FFY00
 *
 * Simple Non Code License (SNCL) v1.10.0
 */
package io.github.ffy00.executor;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author FFY00 <FFY00 at ffy00.cf>
 */
public class PluginCommandExecutor implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] agrs) {

        if(!label.equalsIgnoreCase("servertime") || !sender.hasPermission("anubis.servertime"))
            return false;

        if(sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage("§cServer Time: " + p.getWorld().getTime());
        } else {
            sender.sendMessage("§cServer Time:");
            for(World w : Bukkit.getWorlds()){
                sender.sendMessage("    §c" + w.getName() + ": " + w.getTime());
            }
        }

        return true;
    }

}
