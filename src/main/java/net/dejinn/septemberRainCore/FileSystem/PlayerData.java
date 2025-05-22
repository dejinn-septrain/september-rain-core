package net.dejinn.septemberRainCore.FileSystem;


import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerData {






    public void MakePlayerFolder(Player p){
        File playerDataFolder = new FileManager().MakeFolder("PlayerData","");
        if (playerDataFolder.exists()){
            UUID pUUID = p.getUniqueId();
            File playerFolder = new FileManager().MakeFolder(pUUID.toString(),File.separatorChar + "PlayerData");
            Main.getInstance().getLogger().info("Successfully made data folder for "+p.getDisplayName());
        } else {
            Main.getInstance().getLogger().warning("Player data folder does not exist!");
        }
        File archiveFolder = new FileManager().MakeFolder("Archive",File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId());
    }

    public void MakePlayerFile(Player p)  {
        new FileManager().MakeNewFile("data.yml",File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId());
        this.ReconcilePlayerData(p);
    }



    public void ReconcilePlayerData(Player p){
        HashMap<String, Object> defaultPlayerData = new HashMap<String,Object>();

        String currDate = Main.getInstance().getConfig().get("server-date").toString();

        defaultPlayerData.put("first_name","");
        defaultPlayerData.put("last_name","");
        defaultPlayerData.put("height",1);
        defaultPlayerData.put("research_level",0);
        defaultPlayerData.put("research_tied_to_nation",false);
        defaultPlayerData.put("creation_date",currDate);

        File playerDataFile = new FileManager().MakeNewFile("data.yml",File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId());
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        // Reconcile new data
        for (String key : defaultPlayerData.keySet()){
            if (!playerData.contains(key)){
                playerData.set(key,defaultPlayerData.get(key));
            }
        }
        try {
            playerData.save(playerDataFile);
        } catch (IOException ex) {
            Main.getInstance().getLogger().warning(ex.toString());
        }
    }

    public void SetPlayerData(Player p, String dataKey, Object dataValue){
        File playerDataFile = new FileManager().MakeNewFile("data.yml",File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId());
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);

        playerData.set(dataKey,dataValue);

        try {
            playerData.save(playerDataFile);
        } catch (IOException ex) {
            Main.getInstance().getLogger().warning(ex.toString());
        }
    }

    public Object GetPlayerData(Player p, String dataKey){
        File playerDataFile = new FileManager().MakeNewFile("data.yml",File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId());
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);

        return playerData.get(dataKey);
    }

    public boolean CheckPlayerData(Player p){
        return new FileManager().CheckFile("data.yml", File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId());
    }

}
