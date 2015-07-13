package de.zortax.oneshot.listener;

import org.bukkit.event.Listener;

import de.zortax.oneshot.OneShot;

public class PlayerListener implements Listener{
	
	private OneShot os;
	
	public PlayerListener(OneShot os){
		this.os = os;
	}

}
