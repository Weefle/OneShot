package de.zortax.oneshot.game;

import de.zortax.oneshot.OneShot;

public class GameManager {

	private OneShot os;
	
	private GameState state;
	
	public GameManager(OneShot os){
		this.os = os;
		state = GameState.LOBBY;
	}
	
	
	
	
	
	//Getter-Methoden
	public GameState getState(){
		return state;
	}
	
}
