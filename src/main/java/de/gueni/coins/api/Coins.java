package de.gueni.coins.api;

import de.gueni.coins.CoinPlugin;
import java.sql.ResultSet;
import java.util.UUID;

public class Coins {

    private CoinPlugin plugin;

    public Coins( CoinPlugin plugin ) {
        this.plugin = plugin;
    }

    public void createTable() {
        try {

            plugin.getMySQL().update( "CREATE TABLE IF NOT EXISTS coins (uuid VARCHAR(100), coins INT(16));" );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public void register( UUID uuid ) {
        try {

            plugin.getMySQL().update("INSERT INTO coins (uuid, coins) VALUES ('" + uuid + "', '0')" );

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public boolean isRegistered( UUID uuid ) {
        try {
            ResultSet resultSet = plugin.getMySQL().asyncQuery("SELECT * FROM coins WHERE uuid= '" + uuid + "'" );
            if(resultSet.next()) {
                return resultSet.getString( "uuid" ) != null;
            }
            return false;

        } catch ( Exception ex ) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getCoins( UUID uuid ) {
        try {

            ResultSet resultSet = plugin.getMySQL().asyncQuery( "SELECT coins FROM coins WHERE uuid= '" + uuid + "'" );
            if(resultSet.next()) {
                return resultSet.getInt( "coins" );
            }
            return 0;

        } catch ( Exception ex ) {
            ex.printStackTrace();
            return 0;
        }
    }

    public boolean hasEnoughCoins( UUID uuid, int coins ) {
        if(getCoins( uuid ) >= coins) {
            return true;
        } else {
            return false;
        }
     }

    public void setCoins( UUID uuid, int coins ) {
        try {

          plugin.getMySQL().update( "UPDATE coins SET coins= '" + coins + "' WHERE uuid= '" + uuid + "'");

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public void addCoins( UUID uuid, int coins ) {
        try {

            setCoins( uuid, getCoins( uuid ) + coins );

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public void removeCoins( UUID uuid, int coins ) {
        try {

            setCoins( uuid, getCoins( uuid ) - coins );

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

}
