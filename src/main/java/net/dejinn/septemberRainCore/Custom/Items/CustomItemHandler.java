package net.dejinn.septemberRainCore.Custom.Items;

import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class CustomItemHandler {
    private HashMap<String,ItemStack> customItems = new HashMap<>();
    private NamespacedKey idTag = new NamespacedKey(Main.getInstance(),"custom_id"); ;

    public void InitializeCustomItems(){
        ItemStack workstumpHead = new WorkstumpItem().createItem();
        ItemStack researchTable = new ResearchTableItem().createItem();

        this.customItems.put("workstump",workstumpHead);
        this.customItems.put("research_table",researchTable);
    }

    public NamespacedKey getTag(){
        return this.idTag;
    }

    public ItemStack GetItemStack(String itemId){
        return this.customItems.get(itemId);
    }

    public void GiveItem(Player p, String itemId){
        if (this.customItems.get(itemId) != null){
            ItemStack giveItem = this.GetItemStack(itemId);
            p.give(giveItem);
        }
    }

}
