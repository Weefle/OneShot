package de.zortax.oneshot.user;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import de.zortax.oneshot.OneShot;

public class UserManager {

	private OneShot os;
	
	private ArrayList<User> users;
	
	public UserManager(OneShot os){
		this.os = os;
		
		users = new ArrayList<>();
		
	}
	
	public void join(User user){
		
	}
	
	
	public User getUser(Player p){
		
		for(User c : users){
			if(c.getPlayer().getName().equalsIgnoreCase(p.getName())){
				return c;
			}
		}
		
		return new User(p);
	}
	
	
	
}
