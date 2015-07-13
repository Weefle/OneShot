package de.zortax.oneshot.data;

import de.zortax.oneshot.OneShot;

public class ConfigManager {

	private OneShot os;
	private Config msgConfig;
	
	public ConfigManager(OneShot os){
		this.os = os;
		
		msgConfig = new Config("messages", os);
		
		
	}
	
	
	public String getPrefixedMessage(String key){
		return OneShot.color(msgConfig.getConfig().getString("prefix") + " " + msgConfig.getConfig().getString(key));
	}
	
	public String getRawMessage(String key){
		return OneShot.color(msgConfig.getConfig().getString(key));
	}
	
}