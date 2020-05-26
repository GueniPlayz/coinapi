package de.gueni.coins;

import de.gueni.coins.api.Coins;
import de.gueni.coins.database.MySQL;
import de.gueni.coins.listener.PlayerJoinListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CoinPlugin extends JavaPlugin {

    @Getter
    private static CoinPlugin instance;
    private String prefix = getConfig().getString( "Options.Prefix" ).replace( "&", "§" );
    private String noPermissionMessage = getConfig().getString( "Options.notPermitted" ).replace( "&", "§" );
    private MySQL mySQL;
    private Coins coins;

    @Override
    public void onEnable() {
        instance = this;

        setConfiguration();

        mySQL = new MySQL(
                getConfig().getString( "MySQL.host" ),
                getConfig().getString( "MySQL.database" ),
                getConfig().getString( "MySQL.username" ),
                getConfig().getString( "MySQL.password" ),
                getConfig().getInt( "MySQL.port" ) );
        coins = new Coins( this );

        try {
            mySQL.connect();
        } catch ( Exception exception ) {
            Bukkit.getPluginManager().disablePlugin( this );
        }

        if ( mySQL.isConnected() ) {
            coins.createTable();
            new PlayerJoinListener( this );

            Bukkit.getConsoleSender().sendMessage( prefix + "§7The plugin loaded successfully" );
            Bukkit.getConsoleSender().sendMessage( "§7Plugin by §a\n" +
                    "  ____                  _ ____  _\n" +
                    " / ___|_   _  ___ _ __ (_)  _ \\| | __ _ _   _ ____\n" +
                    "| |  _| | | |/ _ \\ '_ \\| | |_) | |/ _` | | | |_  /\n" +
                    "| |_| | |_| |  __/ | | | |  __/| | (_| | |_| |/ /\n" +
                    " \\____|\\__,_|\\___|_| |_|_|_|   |_|\\__,_|\\__, /___|\n" +
                    "                                        |___/" );
        } else {
            Bukkit.getConsoleSender().sendMessage( prefix + "§cPlease ensure that your mysql data is correct. " );
        }
    }

    @Override
    public void onDisable() {
        mySQL.closeConnection();
    }

    private void setConfiguration() {

        getConfig().options().copyDefaults( true );
        this.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage( this.prefix + "§7The configuration loaded successfully" );
    }
}
