package de.gueni.coins.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class CoinsAddEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private UUID uuid;
    private int addedCoins;
    private boolean cancelled;

    public CoinsAddEvent(UUID uuid, int addedCoins, boolean cancelled) {
        this.uuid = uuid;
        this.addedCoins = addedCoins;
        this.cancelled = cancelled;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public UUID getUniqueID() {
        return uuid;
    }

    public void setUniqueID( UUID uuid ) {
        this.uuid = uuid;
    }

    public int getAddedCoins() {
        return addedCoins;
    }

    public void setAddedCoins( int addedCoins ) {
        this.addedCoins = addedCoins;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled( boolean cancelled ) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

 }
