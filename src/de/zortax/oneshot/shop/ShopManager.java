package de.zortax.oneshot.shop;

import java.util.ArrayList;

import de.zortax.oneshot.OneShot;

public class ShopManager {

	private OneShot os;
	private ArrayList<ShopItem> allItems;
	
	public ShopManager(OneShot os){
		this.os = os;
		allItems = new ArrayList<>();
	}
	
	public void addItem(ShopItem item){
		allItems.add(item);
	}
	
	
	
	public ArrayList<ShopItem> getAllItems(){
		return allItems;
	}

}
