package de.zortax.oneshot.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import de.zortax.oneshot.OneShot;

public class ConfigManager {

	private OneShot os;
	private Config msgConfig;
	private Config lobbyConfig;
	private Location lobbySpawn;
	
	public ConfigManager(OneShot os){
		this.os = os;
		
		msgConfig = new Config("messages", this.os);
		lobbyConfig = new Config("lobby", this.os);
	
		msgConfig.saveConfig();
		lobbyConfig.saveConfig();
		
		
	}
	
	public void loadLobbySpawn(){
		lobbySpawn = new Location(null, 0, 0, 0);
		
		if(lobbyConfig.getConfig().getConfigurationSection("lobbyspawn") == null){
			lobbyConfig.getConfig().createSection("lobbyspawn");
		}
		
		lobbySpawn.setWorld(Bukkit.getWorld(lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").getString("world")));
		lobbySpawn.setX(lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").getDouble("X"));
		lobbySpawn.setY(lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").getDouble("Y"));
		lobbySpawn.setZ(lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").getDouble("Z"));
		lobbySpawn.setYaw((float) lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").getDouble("YAW"));
		lobbySpawn.setPitch((float) lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").getDouble("PITCH"));
		
	}
	
	public void saveLobbySpawn(){
		
		if(lobbyConfig.getConfig().getConfigurationSection("lobbyspawn") == null){
			lobbyConfig.getConfig().createSection("lobbyspawn");
		}
		
		lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").set("world", lobbySpawn.getWorld().getName());
		lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").set("X", lobbySpawn.getX());
		lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").set("Y", lobbySpawn.getY());
		lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").set("Z", lobbySpawn.getZ());
		lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").set("YAW", lobbySpawn.getYaw());
		lobbyConfig.getConfig().getConfigurationSection("lobbyspawn").set("PITCH", lobbySpawn.getPitch());
		lobbyConfig.forceSave();
	}
	
	public void setLobbySpawn(Location lobbySpawn){
		this.lobbySpawn = lobbySpawn;
		this.saveLobbySpawn();
	}
	
	
	
	//Getter-Methoden
	
	public String getPrefixedMessage(String key){
		return OneShot.color(msgConfig.getConfig().getString("prefix") + " " + msgConfig.getConfig().getString(key));
	}
	
	public String getRawMessage(String key){
		return OneShot.color(msgConfig.getConfig().getString(key));
	}
	
	public Config getCustomConfig(){
		return msgConfig;
	}
	
	public Location getLobbySpawn(){
		
		if(lobbySpawn == null) this.loadLobbySpawn();
		
		return lobbySpawn;
	}
	
}