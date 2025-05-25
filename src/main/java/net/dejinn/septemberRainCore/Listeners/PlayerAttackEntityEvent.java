package net.dejinn.septemberRainCore.Listeners;

import net.dejinn.septemberRainCore.Display.DisplayModel;
import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class PlayerAttackEntityEvent implements Listener {
    @EventHandler
    public void onAttackEntityEvent(EntityDamageByEntityEvent e) {

        if (e.getDamager().getType() == EntityType.PLAYER){
            Player p = (Player) e.getDamager();
            if (e.getEntityType() == EntityType.INTERACTION){
                Set<String> entityScoreboardTags = e.getEntity().getScoreboardTags();
                StringBuffer modelType = new StringBuffer();
                for (String i : entityScoreboardTags){
                    if (i.startsWith("modelid:")){
                        modelType.append(i.replace("modelid:",""));
                        break;
                    }
                }
                if (!modelType.isEmpty()){
                    Main.getBlockInstance().BreakCustomBlock(e.getEntity(),modelType.toString());
                }

            }
        }
    }
}
