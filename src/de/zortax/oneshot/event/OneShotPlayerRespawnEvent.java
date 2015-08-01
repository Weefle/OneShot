package de.zortax.oneshot.event;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.zortax.oneshot.user.User;

public class OneShotPlayerRespawnEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	
	private User user;
	private Location location;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public OneShotPlayerRespawnEvent(User user, Location location){
		this.user = user;
		this.location = location;
	}
	
	
	//Getter-Methoden
	
	public User getUser(){
		return user;
	}
	
	public Location getRespawnLocation(){
		return location;
	}
	

}
