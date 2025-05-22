package net.dejinn.septemberRainCore.CharacterData;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;

import net.dejinn.septemberRainCore.FileSystem.FileManager;
import net.dejinn.septemberRainCore.FileSystem.PlayerData;
import net.dejinn.septemberRainCore.JavaShit.MutableInteger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CharacterCreator implements Listener {

    public void openCharacterCreator(Player p){

        p.getAttribute(Attribute.SCALE).setBaseValue(1.0);

        p.sendMessage(ChatColor.RED + "If you accidentally closed the Character Creator menu, please rejoin! You won't be able to play without it.");

        StringBuffer fName = new StringBuffer();
        StringBuffer lName = new StringBuffer();

        MutableInteger heightSelect = new MutableInteger(1);

        ChestGui gui = new ChestGui(5,"Character Creator");
        AnvilGui firstNameGui = new AnvilGui("Set First Name");
        AnvilGui lastNameGui = new AnvilGui("Set Last Name");

        Pattern pattern = new Pattern(
                "111111111",
                "100000001",
                "100000001",
                "100000001",
                "111111111"
        );

        PatternPane pane = new PatternPane(0,0,9,5,pattern);


        ItemStack fillBlackStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack fillWhiteStack = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta fillBlackMeta = fillBlackStack.getItemMeta();
        fillBlackMeta.setDisplayName(ChatColor.BLACK + "");
        ItemMeta fillWhiteMeta = fillBlackStack.getItemMeta();
        fillWhiteMeta.setDisplayName(ChatColor.BLACK + "");
        fillBlackStack.setItemMeta(fillBlackMeta);
        fillWhiteStack.setItemMeta(fillWhiteMeta);

        pane.bindItem('0',new GuiItem(fillBlackStack));
        pane.bindItem('1',new GuiItem(fillWhiteStack));


        pane.setOnClick(e -> e.setCancelled(true));

        StaticPane menu = new StaticPane(2,2,5,1,Pane.Priority.HIGH);

        ItemStack firstName = new ItemStack(Material.OAK_HANGING_SIGN);
        ItemMeta firstNameMeta = firstName.getItemMeta();
        String firstNameDisplay = ChatColor.YELLOW + "Set First Name";
        ArrayList<String> firstNameLore = new ArrayList<String>();
        firstNameLore.add(ChatColor.WHITE + "First Name:");
        firstNameLore.add(ChatColor.RED + "No first name set!");

        firstNameMeta.setLore(firstNameLore);
        firstNameMeta.setDisplayName(firstNameDisplay);

        firstName.setItemMeta(firstNameMeta);

        menu.addItem(new GuiItem(firstName, e ->{
            firstNameGui.show(p);
            p.playSound(p.getLocation(),Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);
        }),0,0);

        ItemStack lastName = new ItemStack(Material.DARK_OAK_HANGING_SIGN);
        ItemMeta lastNameMeta = lastName.getItemMeta();

        String lastNameDisplay = ChatColor.YELLOW + "Last Name";
        lastNameMeta.setDisplayName(lastNameDisplay);

        ArrayList<String> lastNameLore = new ArrayList<String>();
        lastNameLore.add(ChatColor.WHITE + "Last Name:");
        lastNameLore.add(ChatColor.RED + "No last name set!");

        lastNameMeta.setLore(lastNameLore);

        lastName.setItemMeta(lastNameMeta);

        menu.addItem(new GuiItem(lastName, e ->{
            lastNameGui.show(p);
            p.playSound(p.getLocation(),Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);
        }),1,0);

        ItemStack heightSelector = new ItemStack(Material.SPECTRAL_ARROW);
        ItemMeta heightMeta = heightSelector.getItemMeta();

        String heightDisplay = ChatColor.YELLOW + "Height";
        heightMeta.setDisplayName(heightDisplay);

        ArrayList<String> heightLore = new ArrayList<String>();
        heightLore.add(ChatColor.WHITE + "Change your characters height:");
        heightLore.add(ChatColor.DARK_GRAY + "Short");
        heightLore.add(ChatColor.YELLOW + "> " + ChatColor.WHITE + "Average");
        heightLore.add(ChatColor.DARK_GRAY + "Tall");

        heightMeta.setLore(heightLore);


        heightSelector.setItemMeta(heightMeta);

        menu.addItem(new GuiItem(heightSelector, e ->{
            p.playSound(p.getLocation(),Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);
            if (e.getClick() == ClickType.LEFT){
                heightSelect.set(heightSelect.intValue() + 1);
                if (heightSelect.intValue() > 2){
                    heightSelect.set(0);
                }
            } else if (e.getClick() == ClickType.RIGHT){
                heightSelect.set(heightSelect.intValue() - 1);
                if (heightSelect.intValue() < 0){
                    heightSelect.set(2);
                }
            }
            if (heightSelect.intValue() == 0){
                heightLore.clear();
                heightLore.add(ChatColor.WHITE + "Change your characters height:");
                heightLore.add(ChatColor.YELLOW + "> " + ChatColor.WHITE + "Short");
                heightLore.add(ChatColor.DARK_GRAY + "Average");
                heightLore.add(ChatColor.DARK_GRAY + "Tall");

                heightMeta.setLore(heightLore);


                heightSelector.setItemMeta(heightMeta);

                gui.update();
            } else if (heightSelect.intValue() == 1){
                heightLore.clear();
                heightLore.add(ChatColor.WHITE + "Change your characters height:");
                heightLore.add(ChatColor.DARK_GRAY + "Short");
                heightLore.add(ChatColor.YELLOW + "> " + ChatColor.WHITE + "Average");
                heightLore.add(ChatColor.DARK_GRAY + "Tall");

                heightMeta.setLore(heightLore);


                heightSelector.setItemMeta(heightMeta);

                gui.update();
            } else if (heightSelect.intValue() == 2) {
                heightLore.clear();
                heightLore.add(ChatColor.WHITE + "Change your characters height:");
                heightLore.add(ChatColor.DARK_GRAY + "Short");
                heightLore.add(ChatColor.DARK_GRAY + "Average");
                heightLore.add(ChatColor.YELLOW + "> " + ChatColor.WHITE + "Tall");

                heightMeta.setLore(heightLore);


                heightSelector.setItemMeta(heightMeta);

                gui.update();
            }
        }),2,0);

        ItemStack gap = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta gapMeta = gap.getItemMeta();

        String gapDisplay = ChatColor.BLACK + "";
        gapMeta.setDisplayName(gapDisplay);



        gap.setItemMeta(gapMeta);

        menu.addItem(new GuiItem(gap, e ->{
            e.setCancelled(true);
        }),3,0);

        ItemStack create = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

        ItemMeta createMeta = create.getItemMeta();

        String createDisplay = ChatColor.GREEN + "Finish Character";
        createMeta.setDisplayName(createDisplay);

        ArrayList<String> createLore = new ArrayList<String>();
        createLore.add(ChatColor.WHITE + "Enter into the world of SeptemberRain!");
        createLore.add("");
        createLore.add(ChatColor.RED + "Disclaimer: If you die as this character, all of your");
        createLore.add(ChatColor.RED + "character info will be erased and you will have to");
        createLore.add(ChatColor.RED + "make a new character.");

        create.setLore(createLore);

        create.setItemMeta(createMeta);



        menu.addItem(new GuiItem(create, e ->{
            p.playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);
            if (!fName.isEmpty() && !lName.isEmpty()){
                p.closeInventory();
                p.sendTitle(ChatColor.GOLD.toString() + ChatColor.BOLD + "Welcome To SeptemberRain!",ChatColor.WHITE + fName.toString() + " " + lName.toString() + "!");
                p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1f,1f);
                if (heightSelect.intValue() == 0){
                    p.getAttribute(Attribute.SCALE).setBaseValue(0.8);
                } else if (heightSelect.intValue() == 2){
                    p.getAttribute(Attribute.SCALE).setBaseValue(1.3);
                }
                new PlayerData().MakePlayerFile(p);
                new PlayerData().SetPlayerData(p,"first_name",fName.toString());
                new PlayerData().SetPlayerData(p,"last_name",lName.toString());
                new PlayerData().SetPlayerData(p,"height",heightSelect.intValue());
            } else {
                e.setCancelled(true);
            }
        }),4,0);

        StaticPane nameTag = new StaticPane(0,0,1,1, Pane.Priority.NORMAL);
        ItemStack nameTagStack = new ItemStack(Material.NAME_TAG);
        ItemMeta nameTagMeta = nameTagStack.getItemMeta();
        nameTagMeta.setDisplayName("Enter Text Here");
        nameTagStack.setItemMeta(nameTagMeta);
        nameTag.addItem(new GuiItem(nameTagStack),0,0);

        nameTag.setOnClick(e -> {
            e.setCancelled(true);
        });

        StaticPane confirmFirstName = new StaticPane(0,0,1,1, Pane.Priority.NORMAL);
        StaticPane confirmLastName = new StaticPane(0,0,1,1, Pane.Priority.NORMAL);
        ItemStack confirmStack = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta confirmMeta = confirmStack.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN + "Confirm");
        ArrayList<String> confirmLore = new ArrayList<String>();
        confirmLore.add(ChatColor.WHITE + "Name has to be shorter than " + 16 + " characters!");
        confirmLore.add("");
        confirmLore.add(ChatColor.WHITE + "Name has to contain only letters!");
        confirmStack.setItemMeta(confirmMeta);
        confirmFirstName.addItem(new GuiItem(confirmStack),0,0);
        confirmLastName.addItem(new GuiItem(confirmStack),0,0);

        firstNameGui.getFirstItemComponent().addPane(nameTag);
        firstNameGui.getResultComponent().addPane(confirmFirstName);

        confirmFirstName.setOnClick(e -> {
            String newName = firstNameGui.getRenameText();
            int errorCount = 0;
            if (newName.length() > 16){
                errorCount ++;
            }
            if (!newName.matches("[a-zA-ZöäõüÖÜÕÄ]+")){
                errorCount ++;
            }
            if (errorCount > 0) {
                e.setCancelled(true);
            } else {
                fName.delete(0,15);
                fName.append(newName);

                firstNameLore.clear();
                firstNameLore.add(ChatColor.WHITE + "First Name:");
                firstNameLore.add(ChatColor.YELLOW + fName.toString());

                firstNameMeta.setLore(firstNameLore);
                firstName.setItemMeta(firstNameMeta);

                p.playSound(p.getLocation(),Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);

                menu.addItem(new GuiItem(firstName, e1 -> {
                    firstNameGui.show(p);
                    p.playSound(p.getLocation(),Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);
                }),0,0);

                gui.show(p);
            }
        });



        lastNameGui.getFirstItemComponent().addPane(nameTag);
        lastNameGui.getResultComponent().addPane(confirmLastName);

        confirmLastName.setOnClick(e -> {
            String newName = lastNameGui.getRenameText();
            int errorCount = 0;
            if (newName.length() > 16){
                errorCount ++;
            }
            if (!newName.matches("[a-zA-ZöäõüÖÜÕÄ]+")){
                errorCount ++;
            }
            if (errorCount > 0) {
                e.setCancelled(true);
            } else {
                lName.delete(0,15);
                lName.append(newName);

                lastNameLore.clear();
                lastNameLore.add(ChatColor.WHITE + "Last Name:");
                lastNameLore.add(ChatColor.YELLOW + lName.toString());

                lastNameMeta.setLore(lastNameLore);
                lastName.setItemMeta(lastNameMeta);

                p.playSound(p.getLocation(),Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);

                menu.addItem(new GuiItem(lastName, e1 -> {
                    lastNameGui.show(p);
                    p.playSound(p.getLocation(),Sound.BLOCK_STONE_BUTTON_CLICK_ON,1f,1f);
                }),1,0);

                gui.show(p);
            }
        });

        gui.addPane(pane);
        gui.addPane(menu);

        gui.show(p);
    }
    public boolean checkCharExistence(Player player){
        new PlayerData().MakePlayerFolder(player);
        return new PlayerData().CheckPlayerData(player);
    }

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getPlayer();
        String deathCause = e.getEntity().getLastDamageCause().toString();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        String currDate = format.format(now);
        String newDataName = "data-" + currDate + ".yml";
        new PlayerData().SetPlayerData(p,"death_cause",deathCause.toString());
        new FileManager().MoveFile("data.yml",newDataName,File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId(),File.separatorChar + "PlayerData" + File.separatorChar + p.getUniqueId() + File.separatorChar + "Archive");
    }
}
