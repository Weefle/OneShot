package de.zortax.oneshot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.game.GameState;

public class ShopListener implements Listener{
	
	private OneShot os;
	
	public ShopListener(OneShot os){
		this.os = os;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event){
		
		if(os.getGameManager().getState() == GameState.INGAME){
			
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(event.getItem().getItemMeta().getDisplayName().equals(os.getConfigManager().getRawMessage("shop_title"))){
					os.getUserManager().getUser(event.getPlayer()).getShop().open();
				}
			}
			
		}
		
	}

}
