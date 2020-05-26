package de.gueni.coins.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class CoinsSetEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private UUID uuid;
    private int settedCoins;
    private boolean cancelled;

    public CoinsSetEvent(UUID uuid, int settedCoins, boolean cancelled) {
        this.uuid = uuid;
        this.settedCoins = settedCoins;
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

    public int getSettedCoins() {
        return settedCoins;
    }

    public void setSettedCoins( int settedCoins ) {
        this.settedCoins = settedCoins;
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
