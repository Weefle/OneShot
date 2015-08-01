package de.zortax.oneshot.user;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.zortax.oneshot.event.OneShotMoneyChangeEvent;
import de.zortax.oneshot.event.OneShotMoneyChangeEvent.MoneyChangeReason;
import de.zortax.oneshot.shop.Shop;
import de.zortax.oneshot.shop.ShopItem;

public class User {
	
	private Player player;
	private Shop shop;
	private ArrayList<ShopItem> boughtItems;
	private int money;
	
	public User(Player player){
		this.player = player;
		boughtItems = new ArrayList<>();
		money = 0;
	}
	
	public void addShopItem(ShopItem item){
		if(!boughtItems.contains(item)) boughtItems.add(item);
	}
	
	public void removeShopItem(ShopItem item){
		if(boughtItems.contains(item)) boughtItems.remove(item);
	}
	
	public void addMoney(int value, MoneyChangeReason reason){
		
		Bukkit.getPluginManager().callEvent(new OneShotMoneyChangeEvent(money, money + value, this, reason));
		money += value;
		
	}
	
	//Getter-Methoden
	
	public Player getPlayer(){
		return player;
	}
	
	public Shop getShop(){
		return shop;
	}
	
	public ArrayList<ShopItem> getBoughtItems(){
		return boughtItems;
	}
	

}
