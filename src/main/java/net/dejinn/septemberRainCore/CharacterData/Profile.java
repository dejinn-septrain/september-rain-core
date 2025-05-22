package net.dejinn.septemberRainCore.CharacterData;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import net.dejinn.septemberRainCore.FileSystem.PlayerData;
import net.dejinn.septemberRainCore.JavaShit.SkullTexture;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

import com.palmergames.bukkit.towny.TownyAPI;

public class Profile {
    public void OpenProfile(Player p){
        String firstName = new PlayerData().GetPlayerData(p,"first_name").toString();
        String lastName = new PlayerData().GetPlayerData(p,"last_name").toString();
        int height = (int) new PlayerData().GetPlayerData(p,"height");

        ChestGui gui = new ChestGui(5,"Profile");

        Pattern pattern = new Pattern(
                "121212121",
                "200000002",
                "100000001",
                "200000002",
                "121212121"
        );

        PatternPane pane = new PatternPane(0,0,9,5,pattern);

        ItemStack fillBlackStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack fillOrangeStack = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemStack fillRedStack = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        ItemMeta fillBlackMeta = fillBlackStack.getItemMeta();
        fillBlackMeta.setDisplayName(ChatColor.BLACK + "");

        ItemMeta fillOrangeMeta = fillOrangeStack.getItemMeta();
        fillOrangeMeta.setDisplayName(ChatColor.BLACK + "");

        ItemMeta fillRedMeta = fillRedStack.getItemMeta();
        fillRedMeta.setDisplayName(ChatColor.BLACK + "");

        fillBlackStack.setItemMeta(fillBlackMeta);
        fillOrangeStack.setItemMeta(fillOrangeMeta);
        fillRedStack.setItemMeta(fillRedMeta);

        pane.bindItem('0',new GuiItem(fillBlackStack));
        pane.bindItem('1',new GuiItem(fillOrangeStack));
        pane.bindItem('2',new GuiItem(fillRedStack));

        pane.setOnClick(e -> e.setCancelled(true));

        gui.addPane(pane);

        StaticPane profilePane = new StaticPane(3,1,3,2, Pane.Priority.HIGH);

        ItemStack profileStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) profileStack.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
        skullMeta.setDisplayName(ChatColor.GREEN + "--== SeptemberRain ==--");

        ArrayList<String> skullLore = new ArrayList<String>();
        skullLore.add(ChatColor.WHITE + "-----= Character Info =-----");
        skullLore.add(ChatColor.WHITE + "Name:");
        skullLore.add(ChatColor.YELLOW + firstName + " " + lastName);
        skullLore.add(" ");
        skullLore.add(ChatColor.WHITE + "Height:");
        switch (height){
            case 0:
                skullLore.add(ChatColor.YELLOW + "Short");
                break;
            case 1:
                skullLore.add(ChatColor.YELLOW + "Average");
                break;
            case 2:
                skullLore.add(ChatColor.YELLOW + "Tall");
                break;
        }
        if (Bukkit.getServer().getPluginManager().getPlugin("Towny") != null){
            skullLore.add(ChatColor.WHITE + "--------= Towny Info =--------");
            skullLore.add(ChatColor.WHITE + "Town:");
            Town town = TownyAPI.getInstance().getTown(p);
            if (town == null){
                skullLore.add(ChatColor.RED + "Not in a town!");
            } else {
                skullLore.add(ChatColor.GREEN + town.getName());
            }
            skullLore.add(" ");
            skullLore.add(ChatColor.WHITE + "Nation:");
            Nation nation = TownyAPI.getInstance().getNation(p);
            if (nation == null){
                skullLore.add(ChatColor.RED + "Not in a nation!");
            } else {
                skullLore.add(ChatColor.GREEN + nation.getName());
            }
        }
        skullLore.add(ChatColor.WHITE + "-----==================-----");

        skullMeta.setLore(skullLore);

        profileStack.setItemMeta(skullMeta);

        profilePane.addItem(new GuiItem(profileStack, e -> {
            e.setCancelled(true);
        }),1,0);

        ItemStack researchStack = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta researchMeta = researchStack.getItemMeta();
        researchMeta.setDisplayName(ChatColor.GOLD + "Research Menu");

        ArrayList<String> researchLore = new ArrayList<String>();
        researchLore.add(ChatColor.WHITE + "-----==================-----");
        researchLore.add(ChatColor.WHITE + "Access your (or if you're in");
        researchLore.add(ChatColor.WHITE + "a nation, your nation's) ");
        researchLore.add(ChatColor.WHITE + "research menu, showing you");
        researchLore.add(ChatColor.WHITE + "your current tech level and");
        researchLore.add(ChatColor.WHITE + "the servers era.");
        researchLore.add(ChatColor.WHITE + "-----==================-----");

        researchMeta.setLore(researchLore);
        researchStack.setItemMeta(researchMeta);

        profilePane.addItem(new GuiItem(researchStack, e -> {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);
            // Open the research tab later on here.
        }),0,1);

        if (Bukkit.getPluginManager().getPlugin("TownyMenus") != null){
            ItemStack townHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta townMeta = (SkullMeta) townHead.getItemMeta();
            townMeta.setDisplayName(ChatColor.GREEN + "Town Menu");

            ArrayList<String> townLore = new ArrayList<String>();
            townLore.add(ChatColor.WHITE + "-----==================-----");
            townLore.add(ChatColor.WHITE + "Opens your town's menu.");
            townLore.add(ChatColor.WHITE + "-----==================-----");

            townMeta.setLore(townLore);

            PlayerProfile townPlayerProfile = new SkullTexture().getProfile("http://textures.minecraft.net/texture/b25b27ce62ca88743840a95d1c39868f43ca60696a84f564fbd7dda259be00fe");

            townMeta.setOwnerProfile(townPlayerProfile);

            townHead.setItemMeta(townMeta);

            profilePane.addItem(new GuiItem(townHead, e -> {
                e.setCancelled(true);
                p.performCommand("town menu");
            }),1,1);
            // Nation
            ItemStack nationHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta nationMeta = (SkullMeta) nationHead.getItemMeta();
            nationMeta.setDisplayName(ChatColor.YELLOW + "Nation Menu");

            ArrayList<String> nationLore = new ArrayList<String>();
            nationLore.add(ChatColor.WHITE + "-----==================-----");
            nationLore.add(ChatColor.WHITE + "Opens your nation's menu.");
            nationLore.add(ChatColor.WHITE + "-----==================-----");

            nationMeta.setLore(nationLore);

            PlayerProfile nationPlayerProfile = new SkullTexture().getProfile("http://textures.minecraft.net/texture/15015470e286e4ed77a03876cbbfd7b3d358a60606b446d2c4bc8d8e9c373ee9");

            nationMeta.setOwnerProfile(nationPlayerProfile);

            nationHead.setItemMeta(nationMeta);

            profilePane.addItem(new GuiItem(nationHead, e -> {
                e.setCancelled(true);
                p.performCommand("nation menu");
            }),2,1);
        }

        gui.addPane(profilePane);

        gui.show(p);

    }
}
