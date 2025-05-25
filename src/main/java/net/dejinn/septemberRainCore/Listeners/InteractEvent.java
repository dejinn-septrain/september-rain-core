package net.dejinn.septemberRainCore.Listeners;

import net.dejinn.septemberRainCore.Display.DisplayModel;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Set;

public class InteractEvent implements Listener {
    @EventHandler
    public void playerInteractEvent(PlayerInteractAtEntityEvent e){
        if (e.getRightClicked().getType() == EntityType.INTERACTION){
            Entity clickedEntity = e.getRightClicked();
            Set<String> entityScoreboardTags = clickedEntity.getScoreboardTags();
            StringBuffer modelType = new StringBuffer();
            for (String i : entityScoreboardTags){
                if (i.startsWith("modelid:")){
                    modelType.append(i.replace("modelid:",""));
                    break;
                }
            }
            if (!modelType.isEmpty()){
                DisplayModel displayEntity = new DisplayModel();
                displayEntity.SetRootEntity(clickedEntity);
                displayEntity.SetModelType(modelType.toString());


            }


        }
    }
}
