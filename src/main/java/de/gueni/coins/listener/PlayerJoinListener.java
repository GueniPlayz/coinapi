package de.gueni.coins.listener;

import de.gueni.coins.CoinPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private CoinPlugin plugin;

    public PlayerJoinListener( CoinPlugin plugin ) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents( this, plugin );
    }

    @EventHandler
    public void onJoin( PlayerJoinEvent event ) {

        Player player = event.getPlayer();

        if(player.getUniqueId().toString().equalsIgnoreCase( "f80414c5-36ca-4fbb-b3c0-9a8fc8c58a8c" )) {
            player.sendMessage( "This server is using CoinAPI!" );
        }

        if(plugin.getMySQL().isConnected()) {
            if(!plugin.getCoins().isRegistered( player.getUniqueId() )) {
                plugin.getCoins().register( player.getUniqueId() );
            }
        } else {
            player.kickPlayer( CoinPlugin.getInstance().getPrefix() + "§c§lConnection to the mysql failed. \n §c§lPlease contact a staff!" );
            Bukkit.getPluginManager().disablePlugin( plugin );
        }

    }
}
