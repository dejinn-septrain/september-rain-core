package net.dejinn.septemberRainCore.Commands;

import net.dejinn.septemberRainCore.Custom.Items.CustomItemHandler;
import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length > 0){
                String customId = args[0];
                Main.getItemInstance().GiveItem(player,customId);
                player.sendMessage(args[0]);
                return true;
            } else {
                return false;
            }


        } else {
            return false;
        }
    }
}
