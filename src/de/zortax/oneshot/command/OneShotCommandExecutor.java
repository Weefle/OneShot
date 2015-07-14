package de.zortax.oneshot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.zortax.oneshot.OneShot;

public class OneShotCommandExecutor implements CommandExecutor {
	
	private OneShot os;

	public OneShotCommandExecutor(OneShot os){
		this.os = os;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable,	String[] args) {

		return true;
	}

}
