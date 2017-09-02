/*
 * Copyright 2017 FFY00
 *
 * Simple Non Code License (SNCL) v1.10.0
 */

package io.github.ffy00;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import io.github.ffy00.adapter.TimeAdapter;
import io.github.ffy00.executor.PluginCommandExecutor;
import io.github.ffy00.structure.ProtocolPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author FFY00 <FFY00 at ffy00.github.io>
 */
public class AnubisNight extends JavaPlugin implements ProtocolPlugin{

    private ProtocolManager pm;

    @Override
    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage("§bEnabling §cAnubisNight §bv" + getDescription().getVersion() + " by FFY00!");

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§bDisabling §cAnubisNight §bv" + getDescription().getVersion() + " by FFY00!");
    }

    /**
     * Register Listeners
     */
    private void registerListeners(){
        pm = ProtocolLibrary.getProtocolManager();
        pm.addPacketListener(new TimeAdapter(this));
    }

    /*
    * Register Commands
    */
    private void registerCommands(){
        getCommand("servertime").setExecutor(new PluginCommandExecutor());
    }

    /**
     * Get Protocol Manager
     */
    public ProtocolManager getProtocolManager() {
        return pm;
    }
}
