package net.dejinn.septemberRainCore.Custom.Blocks;

import net.dejinn.septemberRainCore.Display.DisplayModel;
import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class WorkstumpBlock {

    private Sound placeSound = Sound.BLOCK_WOOD_PLACE;
    private Sound breakSound = Sound.BLOCK_WOOD_BREAK;

    public void Place(Location location){

        DisplayModel newDisplay = new DisplayModel();
        newDisplay.CreateModel("workstump",location);
        newDisplay.SetInteractionWidth(1.02f);
        newDisplay.SetInteractionHeight(1.02f);

        location.getWorld().playSound(location,this.placeSound,1f,1f);

        new BukkitRunnable() {
            @Override
            public void run() {
                Block block = location.getBlock();
                block.setType(Material.BARRIER);
            }
        }.runTaskLater(Main.getInstance(), 1L);
    }

    public void Break(Entity root){
        root.getLocation().getBlock().setType(Material.AIR);

        root.getLocation().getWorld().playSound(root.getLocation(),this.breakSound,1f,1f);

        DisplayModel displayEntity = new DisplayModel();
        displayEntity.SetRootEntity(root);
        displayEntity.SetModelType("workstump");
        displayEntity.DeleteModel();


    }
}
