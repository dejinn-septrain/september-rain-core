package net.dejinn.septemberRainCore.Display;

import net.dejinn.septemberRainCore.FileSystem.DisplayModels;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;

import java.util.Collection;
import java.util.List;


public class DisplayModel {
    private String modelType;
    private Interaction rootEntity;

    public void CreateModel(String modelId, Location location){
        Location centerLoc = location.toCenterLocation();
        centerLoc.subtract(0,0.5,0);
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        String stringZ = String.valueOf(z);
        String modelTag = modelId + "-x" + stringX + "-y" + stringY + "-z" + stringZ;
        String visualModelTag = modelId + "-x" + stringX + "-y" + stringY + "-z" + stringZ + "-visual";
        String command = new DisplayModels().getDisplayModelCommand(modelId);
        String formattedCommand = this.getFormattedSummonCMD(command,visualModelTag,stringX,stringY,stringZ);

        this.rootEntity = (Interaction) location.getWorld().spawnEntity(centerLoc, EntityType.INTERACTION);
        this.rootEntity.addScoreboardTag(modelTag);
        this.rootEntity.addScoreboardTag("modelid:"+modelId);

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        Bukkit.dispatchCommand(console,formattedCommand);

        this.modelType = modelId;
    }

    public void SetModelType(String newModelType){
        this.modelType = newModelType;
    }

    public void SetRootEntity(Entity newRootEntity){
        this.rootEntity = (Interaction) newRootEntity;
    }

    public void DeleteModel(){
        Location location = this.rootEntity.getLocation().toBlockLocation();
        Collection<Entity> entities = location.getNearbyEntities(3,3,3);
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        String stringZ = String.valueOf(z);
        String modelTag = this.modelType + "-x" + stringX + "-y" + stringY + "-z" + stringZ;
        String visualModelTag = this.modelType + "-x" + stringX + "-y" + stringY + "-z" + stringZ + "-visual";
        for (Entity e : entities){
            if (e.getScoreboardTags().contains(visualModelTag)){
                List<Entity> passengers = e.getPassengers();
                for (Entity p : passengers){
                    p.remove();
                }
                e.remove();
            } else if (e.getScoreboardTags().contains(modelTag)){
                e.remove();
            }
        }
    }

    public void SetInteractionWidth(float width){
        this.rootEntity.setInteractionWidth(width);
    }

    public void SetInteractionHeight(float height){
        this.rootEntity.setInteractionHeight(height);
    }

    private String getFormattedSummonCMD(String command, String modelTag,String x, String y, String z){
        return String.format(command,x,y,z,modelTag);
    }
}
