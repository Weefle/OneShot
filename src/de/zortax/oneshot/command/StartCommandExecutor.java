package de.zortax.oneshot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.game.GameState;

public class StartCommandExecutor implements CommandExecutor {

	private OneShot os;
	
	public StartCommandExecutor(OneShot os){
		this.os = os;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable,	String[] args) {
		
		if(!sender.isOp()){
			sender.sendMessage(os.getConfigManager().getPrefixedMessage("no_permissions"));
		}else{
			if(os.getGameManager().getState() == GameState.LOBBY){
				sender.sendMessage(os.getConfigManager().getPrefixedMessage("forced_start"));
			}else{
				sender.sendMessage(os.getConfigManager().getPrefixedMessage("allready_started"));
			}
		}
		
		
		
		
		
		
		return true;
	}

}
