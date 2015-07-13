package de.zortax.oneshot;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.plugin.java.JavaPlugin;

import de.zortax.oneshot.command.StartCommandExecutor;
import de.zortax.oneshot.command.StatsCommandExecutor;
import de.zortax.oneshot.data.ConfigManager;
import de.zortax.oneshot.game.GameManager;
import de.zortax.oneshot.listener.PlayerListener;
import de.zortax.oneshot.listener.ShopListener;
import de.zortax.oneshot.map.MapManager;
import de.zortax.oneshot.stats.StatsManager;
import de.zortax.oneshot.user.UserManager;

public class OneShot extends JavaPlugin {
	
	//Manager-Objekte deklarieren
	private ConfigManager configManager;
	private GameManager gameManager;
	private MapManager mapManager;
	private StatsManager statsManager;
	private UserManager userManager;
	

	
	@Override
	public void onEnable(){
		
		// Alle Listener und Command Ecexutors registrieren...
		this.getLogger().info("Lade Plugin...");
		
		
		// command executor
		this.getCommand("start").setExecutor(new StartCommandExecutor(this));
		this.getCommand("stats").setExecutor(new StatsCommandExecutor(this));
		
		
		// listener
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		this.getServer().getPluginManager().registerEvents(new ShopListener(this), this);
		
		
		//Manager-Objekte erzeugen
		configManager = new ConfigManager(this);
		gameManager = new GameManager(this);
		mapManager = new MapManager(this);
		statsManager = new StatsManager(this);
		userManager = new UserManager(this);
		
		
		
	}
	
	
	@Override
	public void onDisable(){
		
	}
	
	
	
	// Manager-Getter
	
	public ConfigManager getConfigManager(){
		return configManager;
	}
	
	public GameManager getGameManager(){
		return gameManager;
	}
	
	public MapManager getMapManager(){
		return mapManager;
	}
	
	public StatsManager getStatsManager(){
		return statsManager;
		
	}
	
	public UserManager getUserManager(){
		return userManager;
	}
	
	
	public static String color(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
}
