package es.meriland.commands;

import es.meriland.BoardManager;
import es.meriland.MeriScoreboard;
import es.meriland.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleScoreboardCommand implements CommandExecutor {
    MeriScoreboard plugin = MeriScoreboard.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        String uuid = player.getUniqueId().toString();
        boolean toggled = MeriScoreboard.getPlugin().getPlayersData().getBoolean(uuid);

        if(toggled) {
            BoardManager.hideBoard(player);
            player.sendMessage(Utils.messageFromConfig("disableScoreboard"));
        } else {
            BoardManager.showBoard(player, Utils.getTranslated(player));
            player.sendMessage(Utils.messageFromConfig("enableScoreboard"));
        }

        plugin.setPlayerData(uuid, !toggled);

        return false;
    }
}
