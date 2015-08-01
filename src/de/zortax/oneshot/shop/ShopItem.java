package de.zortax.oneshot.shop;

import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.event.OneShotItemBuyEvent;
import de.zortax.oneshot.event.OneShotPlayerDeathEvent;
import de.zortax.oneshot.event.OneShotPlayerRespawnEvent;

public abstract class ShopItem {
	
	private OneShot os;
	private ItemStack item;
	private String name;
	private int price;
	private String description;
	
	public ShopItem(OneShot os, ItemStack is, String name, int price, String description){
		this.os = os;
		
		this.item = is;
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	
	private final void updateItemMeta(){
		ItemMeta im = item.getItemMeta();
		
		List<String> lore = im.getLore();
		lore.clear();
		String[] lines = this.description.split("\n");
		for(String c : lines){
			lore.add(c);
		}
		
		lore.add(os.getConfigManager().getRawMessage("price") + " " + ChatColor.GRAY + this.price);
		im.setLore(lore);
		
		im.setDisplayName(this.name);
		
		this.item.setItemMeta(im);
		
		
	}
	
	
	
	//Setter-Methode
	
	public final void setItem(ItemStack is){
		this.item = is;
		this.updateItemMeta();		
	}
	
	public final void setPrice(int value){
		this.price = value;
		this.updateItemMeta();
	}
	
	public final void setName(String name){
		this.name = name;
		this.updateItemMeta();
	}
	
	public final void setDescription(String description){
		this.description = description;
		this.updateItemMeta();
	}
	
	
	//Getter-Methoden
	
	public final ItemStack getItem(){
		return this.item;
	}
	
	public final int getPrice(){
		return this.price;
	}
	
	public final String getName(){
		return this.name;
	}
	
	public final String getDescription(){
		return this.description;
	}
	
	
	//Abstrakte Methode
	public abstract void onDeath(OneShotPlayerDeathEvent event);
	public abstract void onRespawn(OneShotPlayerRespawnEvent event);
	public abstract void onBuy(OneShotItemBuyEvent event);
	public abstract void onKill(OneShotPlayerDeathEvent event);
	public abstract void onHit(EntityDamageEvent event);
	public abstract void onEnemyHit(EntityDamageEvent event);
	public abstract void onShot(EntityDamageEvent event);
	public abstract void onMoneyChange();
	public abstract void onFallDamage(EntityDamageEvent event);

}
