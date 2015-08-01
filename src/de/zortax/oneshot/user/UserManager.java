package de.zortax.oneshot.user;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import de.zortax.oneshot.OneShot;

public class UserManager {

	private OneShot os;
	
	private ArrayList<User> allUsers;
	private ArrayList<User> users;
	
	private Scoreboard board;
	private Objective obj;
	private Score scr;
	
	public UserManager(OneShot os){
		this.os = os;
		
		allUsers = new ArrayList<>();
		users = new ArrayList<>();
		
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		obj = board.registerNewObjective("pcount", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(os.getConfigManager().getRawMessage("lobbyboard_title"));
		scr = obj.getScore(os.getConfigManager().getRawMessage("lobbyboard_pcount"));
		
	}
	
	public void join(User user){
		if(!users.contains(user)){
			users.add(user);
		}
		
		user.getPlayer().teleport(os.getConfigManager().getLobbySpawn());
		user.getPlayer().setScoreboard(board);
		os.getGameManager().setUserCount(users.size());
		this.updateScoreboard();
		
		ItemStack is = new ItemStack(Material.CHEST);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(os.getConfigManager().getRawMessage("voting_menu_title"));
		List<String> lore = im.getLore();
		if(lore == null){
			lore = new ArrayList<>();
		}
		lore.add(os.getConfigManager().getRawMessage("rightclick_to_open"));
		im.setLore(lore);
		is.setItemMeta(im);
		user.getPlayer().getInventory().clear();
		user.getPlayer().getInventory().addItem(is);
		
		if(os.getGameManager().getPlayerCount() >= os.getConfig().getInt("players_to_start")){
			os.getGameManager().startGame();
		}
	}
	
	public void quit(User user){
		if(users.contains(user)) users.remove(user);
		os.getGameManager().setUserCount(users.size());
		this.updateScoreboard();
	}
	
	public void updateScoreboard(){
		scr.setScore(os.getGameManager().getPlayerCount());
	}
	
	
	//Getter-Methoden
	
	public User getUser(Player p){
		
		for(User c : allUsers){
			if(c.getPlayer().getName().equalsIgnoreCase(p.getName())){
				return c;
			}
		}
		
		User u = new User(p);
		allUsers.add(u);
		return u;
	}	
	
	public ArrayList<User> getUsers(){
		return users;
	}
}