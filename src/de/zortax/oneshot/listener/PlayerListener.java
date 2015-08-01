package de.zortax.oneshot.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.game.GameState;
import de.zortax.oneshot.map.Map;
import de.zortax.oneshot.map.MapState;


public class PlayerListener implements Listener{
	
	private OneShot os;
	
	public PlayerListener(OneShot os){
		this.os = os;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		os.getStatsManager().join(event.getPlayer());
		
		if(os.getGameManager().getState() == GameState.INGAME || os.getGameManager().getState() == GameState.STARTING || os.getGameManager().getState() == GameState.WARMUP || os.getGameManager().getState() == GameState.RESET){
			event.getPlayer().kickPlayer(os.getConfigManager().getRawMessage("allready_started"));
			return;
		}
		
		if(os.getGameManager().getState() == GameState.DISABLED){
			if(event.getPlayer().isOp()) event.getPlayer().sendMessage(os.getConfigManager().getPrefixedMessage("plugin_disabled"));
		}
		
		if(os.getGameManager().getState() == GameState.LOBBY){
			if(os.getGameManager().getPlayerCount() > os.getConfig().getInt("max_players")){
				event.getPlayer().kickPlayer(os.getConfigManager().getRawMessage("full"));
				return;
			}
			
			//Join-Code
			os.getUserManager().join(os.getUserManager().getUser(event.getPlayer()));
			event.setJoinMessage(os.getConfigManager().getPrefixedMessage("player_join").replace("%player%", event.getPlayer().getName()).replace("%online%", ""+os.getGameManager().getPlayerCount()).replace("%max%", ""+os.getConfig().getInt("max_players")));
			
		}
		
		
		
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event){
		if(os.getGameManager().getState() == GameState.WARMUP){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event){
		
		if(os.getGameManager().getState() != GameState.DISABLED){
			event.setCancelled(true);
			// Wie ich die Klasse "HumanEntity" hasse ._.
			((Player) event.getEntity()).setFoodLevel(20);
		}
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event){
		
		if(event.getEntity() instanceof Player){
			
			if(os.getGameManager().getState() == GameState.LOBBY) event.setCancelled(true);
			
			// TODO
			
		}
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(os.getGameManager().getState() != GameState.DISABLED) event.setCancelled(true);
	}
	
	
	//Voting
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		
		if(os.getGameManager().getState() == GameState.LOBBY){
			
			event.setCancelled(true);
			
			if(event.getInventory().getTitle().equalsIgnoreCase(os.getConfigManager().getRawMessage("voting_menu_title"))){
				
				if(event.getCurrentItem() == null)
					return;
				
				String mapTitle = event.getCurrentItem().getItemMeta().getDisplayName();
				
				for(Map c : os.getMapManager().getMaps()){
					if(c.getState() == MapState.READY){
						if(mapTitle.equalsIgnoreCase(ChatColor.GOLD + c.getName()))
							os.getVotingManager().vote((Player) event.getWhoClicked(), c);
					}
				}
				
			}
			
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event){
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(os.getGameManager().getState() == GameState.LOBBY){
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(os.getConfigManager().getRawMessage("voting_menu_title")) || event.getItem().getItemMeta().getDisplayName().contains(os.getConfigManager().getRawMessage("voting_menu_title"))){
					event.getPlayer().openInventory(os.getVotingManager().getVoteInventory(event.getPlayer()));
				}
			}
		}
	}

}