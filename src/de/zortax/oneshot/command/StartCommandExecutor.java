package de.zortax.oneshot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.game.GameState;

public class StartCommandExecutor implements CommandExecutor {

	private OneShot os;
	
	public StartCommandExecutor (OneShot os){
		this.os = os;
	}
	
	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label,	String[] args) {
		
		if (!sender.isOp()) {
			sender.sendMessage(os.getConfigManager().getPrefixedMessage("no_permissions"));
		} else {
			if (os.getGameManager().getState() == GameState.LOBBY){
				if (os.getGameManager().getPlayerCount() <= os.getConfig().getInt("min_players")){
					sender.sendMessage(os.getConfigManager().getPrefixedMessage("not_enough_players"));
					return true;
				}
				sender.sendMessage(os.getConfigManager().getPrefixedMessage("forced_start"));
				os.getGameManager().startGame();
			} else {
				sender.sendMessage(os.getConfigManager().getPrefixedMessage("allready_started"));
			}
		}

		return true;
	}

}
