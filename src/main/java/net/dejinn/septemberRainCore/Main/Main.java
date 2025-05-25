package net.dejinn.septemberRainCore.Main;


import net.dejinn.septemberRainCore.Commands.CustomItemCommand;
import net.dejinn.septemberRainCore.Commands.ProfileCommand;
import net.dejinn.septemberRainCore.Custom.Blocks.CustomBlockHandler;
import net.dejinn.septemberRainCore.Custom.Items.CustomItemHandler;
import net.dejinn.septemberRainCore.FileSystem.DisplayModels;
import net.dejinn.septemberRainCore.Listeners.BlockPlace;
import net.dejinn.septemberRainCore.Listeners.InteractEvent;
import net.dejinn.septemberRainCore.Listeners.PlayerAttackEntityEvent;
import net.dejinn.septemberRainCore.Listeners.PlayerDeath;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Files;


public final class Main extends JavaPlugin {
    private static Main instance;
    {
        instance = this;
    }
    public static Main getInstance() {
        return instance;
    }

    private static CustomBlockHandler blockInstance;
    {
        blockInstance = new CustomBlockHandler();
    }
    public static CustomBlockHandler getBlockInstance(){
        return blockInstance;
    }

    private static CustomItemHandler itemInstance;
    {
        itemInstance = new CustomItemHandler();
    }
    public static CustomItemHandler getItemInstance(){
        return itemInstance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();

        new DisplayModels().MakeDisplayModelsFile();
        blockInstance.InitializeCustomBlockIDs();
        itemInstance.InitializeCustomItems();


        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerAttackEntityEvent(), this);


        this.getCommand("profile").setExecutor(new ProfileCommand());
        this.getCommand("give_custom").setExecutor(new CustomItemCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
