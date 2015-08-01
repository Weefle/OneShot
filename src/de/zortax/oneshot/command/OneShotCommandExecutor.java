package de.zortax.oneshot.command;

import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.map.Map;

public class OneShotCommandExecutor implements CommandExecutor {
	
	private OneShot os;
	private HashMap<Player, Map> selected;

	public OneShotCommandExecutor(OneShot os){
		this.os = os;
		selected = new HashMap<>();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable,	String[] args) {
		
		if(!sender.isOp()){
			sender.sendMessage(os.getConfigManager().getPrefixedMessage("no_permissions"));
		}else{
			if(args.length < 1){
				sender.sendMessage(OneShot.color("&7▬▬▬▬ &6&lHILFE &7▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
				sender.sendMessage(OneShot.color("&7├&6/oneshot map <...>&7: Map-Commands"));
				sender.sendMessage(OneShot.color("&7├&6/oneshot reload&7: Reload OneShot"));
				sender.sendMessage(OneShot.color("&7├&6/oneshot setlobby&7: Setzt den Lobby-Spawn"));
				sender.sendMessage(OneShot.color("&7├&6/oneshot enable&7: OneShot einschalten"));
				sender.sendMessage(OneShot.color("&7├&6/oneshot disable&7: OneShot für die Konfiguration ausschalten"));
			}else{
				if(args[0].equalsIgnoreCase("map")){
					
					if(!(sender instanceof Player)){
						sender.sendMessage(os.getConfigManager().getPrefixedMessage("only_player_command"));
						return true;
					}
					
					if(args.length <= 1){
						sender.sendMessage(OneShot.color("&7▬▬▬▬ &6&lHILFE (Map-Setup) &7▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
						sender.sendMessage(OneShot.color("&7├&6/oneshot map create <name>&7: Map erstellen"));
						sender.sendMessage(OneShot.color("&7├&6/oneshot map delete <name>&7: Map löschen"));
						sender.sendMessage(OneShot.color("&7├&6/oneshot map list&7: Listet alle Maps auf"));
						sender.sendMessage(OneShot.color("&7├&6/oneshot map select <name>&7: Eine Map für die weitere Konfiguration auswählen"));
						sender.sendMessage(OneShot.color("&7├&6/oneshot map addspawn&7: Spawn zu der ausgewählten Map hinzufügen"));
						sender.sendMessage(OneShot.color("&7├&6/oneshot map delspawn <nummer>&7: Spawn Nummer <...> löschen"));
						sender.sendMessage(OneShot.color("&7├&6/oneshot map delspawns&7: Alle Spawns der ausgewählten Map löschen"));
					}else{
						
						// Command: /oneshot map -> Argumente
						
						
						
						Player p = (Player) sender;
						
						switch(args[1].toLowerCase()){
						case "create":{
							
							if(args.length <= 2){
								p.sendMessage(os.getConfigManager().getPrefixedMessage("must_enter_mapname_create"));
								return true;
							}
							
							if(os.getMapManager().getMap(args[2]) != null){
								p.sendMessage(os.getConfigManager().getPrefixedMessage("map_allready_exists").replace("%map%", args[2]));
								return true;
							}
							
							Map map = new Map(args[2], os);
							os.getMapManager().getMaps().add(map);
							List<String> maps = os.getMapManager().getConfig().getStringList("maps");
							maps.add(map.getName());
							os.getMapManager().getConfig().set("maps", maps);
							os.getMapManager().getCustomConfig().forceSave();
							p.sendMessage(os.getConfigManager().getPrefixedMessage("map_created").replace("%map%", map.getName()));
							selected.put(p, map);
							return true;						
							
						}
						case "delete":{
							
							return true;
						}
						case "list":{
							
							p.sendMessage(OneShot.color("&7▬▬▬ &6&lLISTE ALLER MAPS &r&7▬▬▬"));
							for(Map c : os.getMapManager().getMaps()){
								p.sendMessage(OneShot.color("&7├ &6" + c.getName() + " &7(Status: " + c.getState().name() + ")"));
							}
							p.sendMessage(OneShot.color("&7▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
							
							return true;
						}
						case "select":{
							
							if(args.length <= 2){
								p.sendMessage(os.getConfigManager().getPrefixedMessage("must_enter_mapname"));
								return true;
							}
							
							Map c = os.getMapManager().getMap(args[2]);
							
							if(c == null){
								p.sendMessage(os.getConfigManager().getPrefixedMessage("couldnt_find_map").replace("%map%", args[2]));
								return true;
							}else{
								selected.put(p, c);
								p.sendMessage(os.getConfigManager().getPrefixedMessage("map_selected").replace("%map%", c.getName()));
								return true;
							}
							
						}
						case "addspawn":{
							
							if(!selected.containsKey(p)){
								p.sendMessage(os.getConfigManager().getPrefixedMessage("must_select_map"));
								return true;
							}else{
								selected.get(p).addSpawn(p.getLocation());
								p.sendMessage(os.getConfigManager().getPrefixedMessage("spawn_added").replace("%number%", selected.get(p).getSpawns().size() + ""));
								return true;
							}
							
						}
						case "delspawn":{
							
							if(!selected.containsKey(p)){
								p.sendMessage(os.getConfigManager().getPrefixedMessage("must_select_map"));
								return true;
							}
							
						}
						case "delspawns":{
							
							if(!selected.containsKey(p)){
								p.sendMessage(os.getConfigManager().getPrefixedMessage("must_select_map"));
								return true;
							}
							
						}
						default:{
							
							p.sendMessage(os.getConfigManager().getPrefixedMessage("unknown_map_argument"));
							return true;
							
						}
						}
						
						
						
						
					}
					
					
					
				}else if(args[0].equalsIgnoreCase("setlobby")){
					
					if(!(sender instanceof Player)){
						sender.sendMessage(os.getConfigManager().getPrefixedMessage("only_player_command"));
						return true;
					}
					
					os.getConfigManager().setLobbySpawn(((Player) sender).getLocation());
					sender.sendMessage(os.getConfigManager().getPrefixedMessage("lobbyspawn_set"));
					
				}else if(args[0].equalsIgnoreCase("reload")){
					
					// TODO: Relaod Code
					
					os.reloadConfig();
					os.getConfigManager().getCustomConfig().reloadConfig();
					os.getMapManager().getCustomConfig().reloadConfig();
					os.getStatsManager().getCustomConfig().reloadConfig();
					
					sender.sendMessage(os.getConfigManager().getPrefixedMessage("reloaded"));
					
					//Bukkit.getPluginManager().disablePlugin(os);
					//Bukkit.getPluginManager().enablePlugin(os);
					
					
				}else{
					sender.sendMessage(os.getConfigManager().getPrefixedMessage("unknown_argument"));
				}
			}
		}

		return true;
	}

}
