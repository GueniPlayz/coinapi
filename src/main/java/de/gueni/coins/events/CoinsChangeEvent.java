package de.gueni.coins.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class CoinsChangeEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();
    private UUID uuid;
    private int changedCoins;
    private boolean cancelled;

    public CoinsChangeEvent(UUID uuid, int changedCoins, boolean cancelled) {
        this.uuid = uuid;
        this.changedCoins =  changedCoins;
        this.cancelled = cancelled;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public int getChangedCoins() {
        return this.changedCoins;
    }

    public void setChangedCoins(int changedCoins) {
        this.changedCoins = changedCoins;
    }


    public static HandlerList getHandlerList() {
        return handlers;
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
}
