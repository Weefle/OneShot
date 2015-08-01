package de.zortax.oneshot.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.zortax.oneshot.shop.ShopItem;
import de.zortax.oneshot.user.User;

public class OneShotItemBuyEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private ShopItem item;
	private User user;
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public OneShotItemBuyEvent(ShopItem item, User user){
		this.item = item;
		this.user = user;
	}
	
	
	//Getter-Methoden
	
	public ShopItem getItem(){
		return item;
	}
	
	public User getUser(){
		return user;
	}
	
}
