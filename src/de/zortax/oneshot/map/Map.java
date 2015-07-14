package de.zortax.oneshot.map;

import java.util.ArrayList;

import org.bukkit.Location;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.data.Config;

public class Map {
	
	private String name;
	private Config config;
	private OneShot os;
	private ArrayList<Location> spawns;
	
	public Map(String name, OneShot os){
		
		this.os = os;
		this.name = name;
		config = new Config(this.name, this.os);
		spawns = new ArrayList<>();
		
		
		
	}
	
	
	public void loadSpawns(){
		
	}
	
	
	
	
	//Getter-Methode
	public String getName(){
		return name;
	}

}
