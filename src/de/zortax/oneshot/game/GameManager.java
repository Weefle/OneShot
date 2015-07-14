package de.zortax.oneshot.game;

import de.zortax.oneshot.OneShot;

public class GameManager {

	private OneShot os;
	
	private GameState state;
	private int userCount;
	
	public GameManager(OneShot os){
		this.os = os;
		state = GameState.LOBBY;
		userCount = 0;
	}
	
	
	public void setUserCount(int userCount){
		this.userCount = userCount;
		
		if(state == GameState.LOBBY){
			
		}
		
	}
	
	public void startGame(){
		
		if(state == GameState.LOBBY && userCount >= os.getConfig().getInt("min_players")){
			
		}
		
	}

	
	
	//Getter-Methoden
	public GameState getState(){
		return state;
		
	}
	
	
	
	public class StartTimer extends Thread{
		
		public StartTimer(){
			
		}
		
		@Override
		public void run(){
			
		}
		
	}
	
}
