package de.zortax.oneshot.shop;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.user.User;

public class Shop {
	
	private User owner;
	private Inventory inv;
	private OneShot os;
	private ArrayList<ShopItem> items;
	
	public Shop(User owner, OneShot os){
		this.os = os;
		this.owner = owner;
		this.inv = Bukkit.createInventory(this.owner.getPlayer(), 27, this.os.getConfigManager().getRawMessage("shop_title"));
	}
	
	public void addItem(ShopItem item){
		
	}
	
	public void removeItem(ShopItem item){
		
	}
	
	public void open(){
		
		inv.clear();
		
		for(ShopItem c : items){
			inv.addItem(c.getItem());
		}
		
		owner.getPlayer().openInventory(inv);
		
	}
	
	
	
	//Getter-Methoden
	
	public ArrayList<ShopItem> getItems(){
		return items;
	}

}
