package net.dejinn.septemberRainCore.Main;

import net.dejinn.septemberRainCore.CharacterData.CharacterCreator;
import net.dejinn.septemberRainCore.CharacterData.Profile;
import net.dejinn.septemberRainCore.Commands.ProfileCommand;
import net.dejinn.septemberRainCore.FileSystem.FileManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class Main extends JavaPlugin {
    private static Main instance;
    {
        // Assign your instance to the field as soon as it's created
        instance = this;
    }

    // Now you can access this method to get your main instance whenever you need it
    public static Main getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new CharacterCreator(), this);

        this.getCommand("profile").setExecutor(new ProfileCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
