package net.dejinn.septemberRainCore.Listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import net.dejinn.septemberRainCore.FileSystem.FileManager;
import net.dejinn.septemberRainCore.FileSystem.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerDeath implements Listener {
    @EventHandler
    void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getPlayer();
        String deathCause = e.getEntity().getLastDamageCause().toString();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        String currDate = format.format(now);
        String newDataName = "data-" + currDate + ".yml";
        new PlayerData().SetPlayerData(p,"death_cause",deathCause.toString());
        new FileManager().MoveFile("data.yml",newDataName, File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId(),File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId() + File.separatorChar + "Archive");
        if (Bukkit.getServer().getPluginManager().getPlugin("Towny") != null){
            Resident resident = TownyAPI.getInstance().getResident(p);
            resident.removeTown();
            resident.removeAllFriends();
        }
    }
}
