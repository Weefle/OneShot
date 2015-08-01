package de.zortax.oneshot.stats;

import java.util.HashMap;

import org.bukkit.entity.Player;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.data.Config;

public class StatsManager {

	private OneShot os;
	
	private Config data;
	private HashMap<Player, Stat> stats;
	
	public StatsManager(OneShot os){
		this.os = os;
		
		data = new Config("stats", os);
		data.saveConfig();
		stats = new HashMap<>();
		
	}
	
	public void join(Player p){
		
		if(stats.containsKey(p)){
			return;
		}else{
			stats.put(p, getStat(p));
		}
		
	}
	
	public void sendStats(Player p){
		Stat stat = getStat(p);
		
		double kd;
		
		if(stat.deaths == 0){
			kd = stat.kills;
		}else{
			kd = (double) stat.kills / stat.deaths;
		}
		
		p.sendMessage(OneShot.color("&7▬▬▬▬ &6STATS &7▬▬▬▬"));
		p.sendMessage(OneShot.color("&7├&6Kills: &c" + stat.kills));
		p.sendMessage(OneShot.color("&7├&6Tode: &c" + stat.deaths));
		p.sendMessage(OneShot.color("&7├&6K/D: &c" + String.format("%.2f", kd)));
		p.sendMessage(OneShot.color("&7├&6Spiele: &c" + stat.games));
		p.sendMessage(OneShot.color("&7▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
		
	}
	
	//Getter-Methoden
	
	public Stat getStat(Player p){
		
		Stat stat = new Stat();
		
		if(data.getConfig().getConfigurationSection(p.getName()) == null){
			data.getConfig().createSection(p.getName());
			data.getConfig().getConfigurationSection(p.getName()).set("kills", 0);
			data.getConfig().getConfigurationSection(p.getName()).set("deaths", 0);
			data.getConfig().getConfigurationSection(p.getName()).set("games", 0);
			data.forceSave();
		}else{
			stat.kills = data.getConfig().getConfigurationSection(p.getName()).getInt("kills");
			stat.deaths = data.getConfig().getConfigurationSection(p.getName()).getInt("deaths");
			stat.games = data.getConfig().getConfigurationSection(p.getName()).getInt("games");	
		}
		
		return stat;
	}
	
	public Config getCustomConfig(){
		return data;
	}
	
}
