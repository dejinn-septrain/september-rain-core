package net.dejinn.septemberRainCore.Commands;

import net.dejinn.septemberRainCore.CharacterData.CharacterCreator;
import net.dejinn.septemberRainCore.CharacterData.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (new CharacterCreator().checkCharExistence(player)){
                new Profile().OpenProfile(player);
            } else {
                new CharacterCreator().openCharacterCreator(player);
            }
            return true;
        } else {
            return false;
        }

    }
}
