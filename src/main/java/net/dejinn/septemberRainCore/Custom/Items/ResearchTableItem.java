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

public class ResearchTableItem {
    public ItemStack createItem(){
        ItemStack researchTableItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) researchTableItem.getItemMeta();
        PlayerProfile rtPlayerProfile = new SkullTexture().getProfile("http://textures.minecraft.net/texture/2fec6f76db12c501c9a946fd307a4f0deda488ca6b8904ca0820a996ab8f9859");

        skullMeta.setOwnerProfile(rtPlayerProfile);

        skullMeta.setDisplayName(ChatColor.WHITE + "Research Table");

        NamespacedKey idTag = Main.getItemInstance().getTag();

        skullMeta.getPersistentDataContainer().set(idTag, PersistentDataType.STRING,"research_table");

        researchTableItem.setItemMeta(skullMeta);

        return researchTableItem;
    }
}
