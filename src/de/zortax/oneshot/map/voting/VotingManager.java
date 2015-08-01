package de.zortax.oneshot.map.voting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.map.Map;
import de.zortax.oneshot.map.MapState;

public class VotingManager {
	
	private OneShot os;
	private HashMap<Map, Integer> votes;
	private ArrayList<Player> voted;
	private ArrayList<Map> won;
	
	public VotingManager(OneShot os){
		this.os = os;
		votes = new HashMap<>();
		voted = new ArrayList<>();
		won = new ArrayList<>();
		
		this.startVoting();
	}
	
	public void startVoting(){
		votes.clear();
		voted.clear();
		
		for(Map c : os.getMapManager().getMaps()){
			if(c.getState() == MapState.READY){
				votes.put(c, 0);
			}
		}
		
	}
	
	public void vote(Player player, Map map){
		
		if(voted.contains(player)){
			player.sendMessage(os.getConfigManager().getPrefixedMessage("allready_voted"));
			return;
		}
		
		if(!votes.containsKey(map)){
			votes.put(map, 0);
		}
		
		int i = votes.get(map);
		i++;
		
		votes.put(map, i);
		voted.add(player);
		
		player.sendMessage(os.getConfigManager().getPrefixedMessage("succsesfully_voted").replace("%map%", map.getName()));
		
	}
	
	public Map endVoting(){
		
		int maxVotes = 0;
		
		ArrayList<Map> maps = new ArrayList<>();
		
		for(Map c : os.getMapManager().getMaps()){
			if(c.getState() == MapState.READY){
				maps.add(c);
			}
		}
		
		for(Map c : maps){
			if(!votes.containsKey(c)){
				votes.put(c, 0);
			}
			if(votes.get(c) > maxVotes){
				maxVotes = votes.get(c);
				won.clear();
				won.add(c);
			}
			if(votes.get(c) == maxVotes){
				won.add(c);
			}
		}
		
		Random rand = new Random();
		return won.get(rand.nextInt(won.size()));
	}
	
	
	
	public Inventory getVoteInventory(Player p){
		
		ArrayList<Map> readyMaps = new ArrayList<>();
		
		for(Map c : os.getMapManager().getMaps()){
			if(c.getState() == MapState.READY) readyMaps.add(c);
		}
		
		int invSize = readyMaps.size();
		invSize = invSize * 9;
		
		while(invSize > readyMaps.size()){
			invSize = invSize - 9;
		}
		invSize = invSize + 9;
		
		Inventory inv = Bukkit.createInventory(p, invSize, os.getConfigManager().getRawMessage("voting_menu_title"));
		
		for(Map c : readyMaps){
			
			ItemStack is = new ItemStack(Material.PAPER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(ChatColor.GOLD + c.getName());
			List<String> lore = im.getLore();
			if(lore == null){
				lore = new ArrayList<>();
			}
			lore.clear();
			
			if(votes.containsKey(c)){
				lore.add(ChatColor.GRAY + "Votes: " + votes.get(c));
			}else{
				lore.add(ChatColor.GRAY + "Votes: 0");
			}
			im.setLore(lore);
			
			is.setItemMeta(im);
			
			inv.addItem(is);
			
		}
		
		return inv;
	}
	
	

}
