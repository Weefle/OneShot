package de.zortax.oneshot.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.zortax.oneshot.OneShot;


public class MapConfig {
	private OneShot plugin;
	private String name;
	
	
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	
	public MapConfig(String name, OneShot plugin){
		
		this.plugin = plugin;
		this.name = name + ".yml";
		
		this.reloadConfig();
	}
	
	
	
	
	public void reloadConfig() {
		
		
		
	    if (customConfigFile == null) {
	    	File maps = new File(plugin.getDataFolder().toString() + "\\maps");
	    	if(!maps.exists()){
				maps.mkdir();
	    	}
	    	customConfigFile = new File(plugin.getDataFolder().toString()+ "\\maps", name);
	    }
	    customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	 
	    
	}
	
	
	
	public FileConfiguration getConfig() {
	    if (customConfig == null) {
	        reloadConfig();
	    }
	    return customConfig;
	}
	
	
	
	public void saveConfig() {
	    if (customConfigFile == null) {
	    	File maps = new File(plugin.getDataFolder().toString() + "\\maps");
	    	if(!maps.exists()){
				maps.mkdir();
	    	}
	        customConfigFile = new File(plugin.getDataFolder().toString() + "\\maps", name);
	    }
	    if (!customConfigFile.exists()) {   
	    	try{
	    		customConfigFile.createNewFile();	    
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }
	}
	
	
	
	public void forceSave(){
		this.saveConfig();
		try {
			customConfig.save(customConfigFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
