package de.zortax.oneshot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zortax.oneshot.OneShot;

public class StatsCommandExecutor implements CommandExecutor {

	private OneShot os;
	
	public StatsCommandExecutor(OneShot os){
		this.os = os;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable,	String[] args) {
		
		if(sender instanceof Player){
			os.getStatsManager().sendStats((Player) sender);
		}
		
		return true;
	}

}
