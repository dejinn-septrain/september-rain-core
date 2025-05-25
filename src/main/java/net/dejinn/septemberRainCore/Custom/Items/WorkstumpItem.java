package net.dejinn.septemberRainCore.Custom.Items;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.dejinn.septemberRainCore.JavaShit.SkullTexture;
import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

public class WorkstumpItem {
    public ItemStack createItem(){
        ItemStack workstumpHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta workstumpSkullMeta = (SkullMeta) workstumpHead.getItemMeta();
        PlayerProfile workstumpPlayerProfile = new SkullTexture().getProfile("http://textures.minecraft.net/texture/57da4aa480cc6af5b8053e09605dc71b86911d24da8a8c0e4392a575646aa6c1");

        workstumpSkullMeta.setOwnerProfile(workstumpPlayerProfile);

        workstumpSkullMeta.setDisplayName(ChatColor.WHITE + "Workstump");

        NamespacedKey idTag = Main.getItemInstance().getTag();

        workstumpSkullMeta.getPersistentDataContainer().set(idTag, PersistentDataType.STRING,"workstump");

        workstumpHead.setItemMeta(workstumpSkullMeta);

        return workstumpHead;
    }
}
