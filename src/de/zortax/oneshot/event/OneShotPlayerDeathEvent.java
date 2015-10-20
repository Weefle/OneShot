package de.zortax.oneshot.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.zortax.oneshot.user.User;

public class OneShotPlayerDeathEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();

	private User killed;
	private User killer;
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public OneShotPlayerDeathEvent(User killed, User killer){
		this.killed = killed;
		this.killer = killer;
	}
	
	
	//Getter-Methoden
	
	public User getUser() {
		return killed;
	}
	
	public Player getPlayer() {
		return killed.getPlayer();
	}
	
	public User getKiller() {
		return killer;
	}
	
	public Player getKillerPlayer() {
		return killer.getPlayer();
	}

}
