package net.dejinn.septemberRainCore.Listeners;

import net.dejinn.septemberRainCore.Custom.Blocks.CustomBlockHandler;
import net.dejinn.septemberRainCore.Display.DisplayModel;
import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class BlockPlace implements Listener {
    @EventHandler
    public void placeBlock(BlockPlaceEvent e){
        ItemStack placedItem = e.getItemInHand();
        ItemMeta placedItemMeta = placedItem.getItemMeta();
        if (placedItemMeta.getPersistentDataContainer().has(Main.getItemInstance().getTag())){
            String customBlockId = placedItemMeta.getPersistentDataContainer().get(Main.getItemInstance().getTag(), PersistentDataType.STRING);
            e.setCancelled(true);
            placedItem.setAmount(placedItem.getAmount()-1);
            Main.getBlockInstance().PlaceCustomBlock(customBlockId,e.getBlock().getLocation());

        }
    }
}
