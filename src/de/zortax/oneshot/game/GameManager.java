package de.zortax.oneshot.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.map.Map;
import de.zortax.oneshot.user.User;

public class GameManager {

	private OneShot os;
	
	private GameState state;
	private int userCount;
	private Map selectedMap;
	
	public GameManager(OneShot os){
		this.os = os;
		state = GameState.DISABLED;
		userCount = 0;
		
		if(os.getConfig().getBoolean("enabled")) state = GameState.LOBBY;
		
	}
	
	
	public void setUserCount(int userCount){
		this.userCount = userCount;
		
		if(state == GameState.LOBBY){
			
		}
		
	}
	
	public void startGame(){
		
		if(state == GameState.LOBBY && userCount >= os.getConfig().getInt("min_players")){
			state = GameState.STARTING;
			StartTimer st = new StartTimer();
			st.start();
		}
		
	}

	
	//Getter-Methoden
	
	public GameState getState(){
		return state;
		
	}
	
	public int getPlayerCount(){
		return userCount;
	}
	
	public ItemStack getShopItem(){
		ItemStack is = new ItemStack(Material.EMERALD);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(os.getConfigManager().getRawMessage("shop_title"));
		List<String> lore = im.getLore();
		if(lore == null) lore = new ArrayList<>();
		lore.clear();
		lore.add(os.getConfigManager().getRawMessage("rightclick_to_open"));
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	
	
	public class StartTimer extends Thread{
		
		public StartTimer(){}
		
		@Override
		public void run(){
			
			state = GameState.STARTING;
			
			for(User c : os.getUserManager().getUsers()){
				c.getPlayer().getInventory().clear();
			}
			
			selectedMap = os.getVotingManager().endVoting();
			
			for(Player c : Bukkit.getOnlinePlayers()){
				c.getInventory().clear();				
			}
			
			Bukkit.broadcastMessage(os.getConfigManager().getPrefixedMessage("voting_ended").replace("%map%", selectedMap.getName()));
			
			for(int i = 30; i > 0; i--){
				
				
				if(i == 30 || i == 20 || i <= 10)
					Bukkit.broadcastMessage(os.getConfigManager().getPrefixedMessage("game_teleport_starting").replace("%sec%", i + ""));
				
				for(Player c : Bukkit.getOnlinePlayers()){
					c.setLevel(i);
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			state = GameState.WARMUP;
			
			for(int i = 0; i < os.getUserManager().getUsers().size(); i++){
				os.getUserManager().getUsers().get(i).getPlayer().teleport(selectedMap.getSpawns().get(i));
			}
			
			for(int i = 10; i > 0; i--){
				
				Bukkit.broadcastMessage(os.getConfigManager().getPrefixedMessage("game_starting").replace("%sec%", i + ""));
				
				for(User c : os.getUserManager().getUsers()){
					c.getPlayer().setLevel(i);
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for(User c : os.getUserManager().getUsers()){
				c.getPlayer().getInventory().clear();
				c.getPlayer().getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD));
				c.getPlayer().getInventory().setItem(1, new ItemStack(Material.BOW));
				c.getPlayer().getInventory().setItem(2, new ItemStack(Material.ARROW));
				c.getPlayer().getInventory().setItem(8, getShopItem());
				
			}
			
			state = GameState.INGAME;
			
		}
		
	}
	
}