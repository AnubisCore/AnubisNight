/*
 * Copyright 2017 FFY00
 *
 * Simple Non Code License (SNCL) v1.10.0
 */

package io.github.ffy00.adapter;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import io.github.ffy00.structure.ProtocolPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author FFY00 <FFY00 at ffy00.github.io>
 */
public class TimeAdapter extends PacketAdapter {

    protected ProtocolPlugin plugin;
    protected HashMap<String, Long> zero = new HashMap<String, Long>();
    protected int i = 0;

    public TimeAdapter(JavaPlugin plugin){
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.UPDATE_TIME);
        this.plugin = (ProtocolPlugin) plugin;
    }

    @Override
    public void onPacketSending(PacketEvent e){
        e.setCancelled(true);

        if(i == 10){
            i = 0;
            return;
        }

        i++;

        PacketContainer packet = e.getPacket();

        World w = e.getPlayer().getWorld();
        if(w.getTime() > 20000)
            w.setTime(16000);

        if(!zero.containsKey(w.getName()))
            zero.put(w.getName(), packet.getLongs().read(0));

        long ticks = packet.getLongs().read(0) - zero.get(w.getName());
        if(ticks > 23900){
            ticks = 0;
            zero.put(w.getName(), packet.getLongs().read(0));
        }

        packet.getLongs().write(1, ticks);

        try {
            plugin.getProtocolManager().sendServerPacket(e.getPlayer(), packet);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException("Cannot send packet " + packet, ex);
        } catch (java.lang.StackOverflowError ex) { }
    }

}
