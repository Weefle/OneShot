package de.zortax.oneshot.map;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.data.MapConfig;

public class Map {
	
	private String name;
	private MapConfig config;
	private OneShot os;
	private ArrayList<Location> spawns;
	private MapState state;
	
	public Map(String name, OneShot os){
		
		this.os = os;
		this.name = name;
		config = new MapConfig(this.name, this.os);
		config.saveConfig();
		spawns = new ArrayList<>();
		state = MapState.LOADING;
		loadSpawns();
		
		
		
	}
	
	
	public void loadSpawns(){
		
		for(int i = 0; i >=0; i++){
			if(config.getConfig().getConfigurationSection("spawn" + i) == null){
				
				if(spawns.size() < os.getConfig().getInt("max_players")){
					state = MapState.SMALL;
					return;
				}else{
					state = MapState.READY;
					return;
				}
				
			}
			Location loc = new Location(null, 0, 0, 0);
			loc.setWorld(Bukkit.getWorld(config.getConfig().getConfigurationSection("spawn" + i).getString("world")));
			loc.setX(config.getConfig().getConfigurationSection("spawn" + i).getDouble("X"));
			loc.setY(config.getConfig().getConfigurationSection("spawn" + i).getDouble("Y"));
			loc.setZ(config.getConfig().getConfigurationSection("spawn" + i).getDouble("Z"));
			loc.setYaw((float) config.getConfig().getConfigurationSection("spawn" + i).getDouble("YAW"));
			loc.setPitch((float) config.getConfig().getConfigurationSection("spawn" + i).getDouble("PITCH"));
			spawns.add(loc);
		}
		
		state = MapState.ERROR;
		
	}
	
	public void saveSpawns(){
		
		for(int i = 0; i < spawns.size(); i++){
			if(config.getConfig().getConfigurationSection("spawn" + i) == null) config.getConfig().createSection("spawn" + i);
			config.getConfig().getConfigurationSection("spawn" + i).set("world", spawns.get(i).getWorld().getName());
			config.getConfig().getConfigurationSection("spawn" + i).set("X", spawns.get(i).getX());
			config.getConfig().getConfigurationSection("spawn" + i).set("Y", spawns.get(i).getY());
			config.getConfig().getConfigurationSection("spawn" + i).set("Z", spawns.get(i).getZ());
			config.getConfig().getConfigurationSection("spawn" + i).set("YAW", spawns.get(i).getYaw());
			config.getConfig().getConfigurationSection("spawn" + i).set("PITCH", spawns.get(i).getPitch());
		}
		config.forceSave();
		
	}
	
	public void addSpawn(Location loc){
		spawns.add(loc);
		saveSpawns();
		
		if(spawns.size() < os.getConfig().getInt("max_players")){
			state = MapState.SMALL;
		}else if(spawns.size() >= os.getConfig().getInt("max_players")){
			state = MapState.READY;
		}
	}
	
	
	
	//Getter-Methode
	public String getName(){
		return name;
	}
	
	public MapState getState(){
		return state;
	}
	
	public ArrayList<Location> getSpawns(){
		return spawns;
	}
	
	public Location getRandomSpawn(){
		Random rand = new Random();
		return spawns.get(rand.nextInt(spawns.size()));
	}

}
