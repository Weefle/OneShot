package de.zortax.oneshot.map;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.configuration.Configuration;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.data.Config;

public class MapManager {

	private OneShot os;
	private ArrayList<Map> maps;
	private Config config;
	
	public MapManager(OneShot os){
		this.os = os;
		maps = new ArrayList<>();
		config = new Config("maps", this.os);
		config.saveConfig();
	}
	
	public void loadMaps(){
		
		for(String c : config.getConfig().getStringList("maps")){
			maps.add(new Map(c, os));
		}
	}
	
	
	public void saveConfig(){
		config.saveConfig();
		config.forceSave();
	}
	
	
	//Getter-Methoden
	
	public ArrayList<Map> getMaps(){
		return maps;
	}
	
	public Map getMap(String name){
		for(Map c : maps){
			if(c.getName().equalsIgnoreCase(name)) return c;
		}
		return null;
	}
	
	public Configuration getConfig(){
		return config.getConfig();
	}
	
	public Config getCustomConfig(){
		return config;
	}
	
	public Map getRandomMap(){
		
		Random rand = new Random();
		
		ArrayList<Map> readyMaps = new ArrayList<>();
		
		for(Map c : maps){
			
			if(c.getState() == MapState.READY){
				readyMaps.add(c);
			}
		
		}
		
		if(readyMaps.size() == 0){
			return null;
		}else{
			return readyMaps.get(rand.nextInt(readyMaps.size()));
		}
	}
	
}