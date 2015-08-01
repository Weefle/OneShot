package de.zortax.oneshot.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.game.GameState;
import de.zortax.oneshot.shop.ShopItem;

public class ShopItemListener implements Listener {
	
	private OneShot os;
	
	public ShopItemListener(OneShot os){
		this.os = os;
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player && os.getGameManager().getState() == GameState.INGAME){
			
			if(event.getCause() == DamageCause.FALL){
				for(ShopItem c : os.getUserManager().getUser((Player) event.getEntity()).getBoughtItems()){
					c.onFallDamage(event);
				}
			}
			
			
		}
	}

}
