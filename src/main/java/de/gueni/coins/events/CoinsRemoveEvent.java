package de.gueni.coins.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class CoinsRemoveEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private UUID uuid;
    private int removedCoins;
    private boolean cancelled;

    public CoinsRemoveEvent(UUID uuid, int removedCoins, boolean cancelled) {
        this.uuid = uuid;
        this.removedCoins = removedCoins;
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

    public int getRemovedCoins() {
        return removedCoins;
    }

    public void setRemovedCoins( int removedCoins ) {
        this.removedCoins = removedCoins;
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
