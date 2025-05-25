package net.dejinn.septemberRainCore.Custom.Blocks;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.dejinn.septemberRainCore.Custom.Items.ResearchTableItem;
import net.dejinn.septemberRainCore.Custom.Items.WorkstumpItem;
import net.dejinn.septemberRainCore.Display.DisplayModel;
import net.dejinn.septemberRainCore.JavaShit.SkullTexture;
import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;

public class CustomBlockHandler {
    private HashMap<ItemMeta,String> customBlockIds = new HashMap<>();

    public void InitializeCustomBlockIDs(){
        ItemStack workstumpHead = new WorkstumpItem().createItem();
        ItemMeta workstumpMeta = workstumpHead.getItemMeta();

        ItemStack researchTable = new ResearchTableItem().createItem();
        ItemMeta researchMeta = researchTable.getItemMeta();

        this.customBlockIds.put(workstumpMeta,"workstump");
        this.customBlockIds.put(researchMeta,"research_table");

    }

    public void PlaceCustomBlock(String blockId, Location location, Location yawLocation){
        switch (blockId){
            case "workstump":
                new WorkstumpBlock().Place(location,yawLocation);
                break;
            case "research_table":
                new ResearchTableBlock().Place(location,yawLocation);
                break;
        }
    }
    public void BreakCustomBlock(Entity rootEntity, String modelType){
        ItemStack dropItem = Main.getItemInstance().GetItemStack(modelType.toString());
        if (dropItem != null){
            rootEntity.getLocation().getChunk().getWorld().dropItem(rootEntity.getLocation(),dropItem);
        }

        switch (modelType){
            case "workstump":
                new WorkstumpBlock().Break(rootEntity);
                break;
            case "research_table":
                new ResearchTableBlock().Break(rootEntity);
                break;
        }

    }

    public String GetCustomBlockID(ItemStack itemStack){
        String customBlockId;

        if (this.customBlockIds.containsKey(itemStack.getItemMeta())){
            customBlockId = this.customBlockIds.get(itemStack.getItemMeta());
            return customBlockId;
        }

        return null;
    }
}
