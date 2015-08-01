package de.zortax.oneshot.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.zortax.oneshot.user.User;

public class OneShotMoneyChangeEvent extends Event{
	
	private static final HandlerList handlers = new HandlerList();
	
	private int oldValue;
	private int newValue;
	private int difference;
	private User user;
	private MoneyChangeReason reason;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public OneShotMoneyChangeEvent(int oldValue, int newValue, User user, MoneyChangeReason reason){
		
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.difference = newValue - oldValue;
		this.user = user;
		this.reason = reason;
		
	}
	
	
	
	//Getter-Methoden
	
	public int getOldValue(){
		return oldValue;
	}
	
	public int getNewValue(){
		return newValue;
	}
	
	public int getNewMoney(){
		return difference;
	}
	
	public User getUser(){
		return user;
	}
	
	public MoneyChangeReason getReason(){
		return reason;
	}
	
	
	
	public enum MoneyChangeReason{
		KILL, SHOPITEM;
	}
}
