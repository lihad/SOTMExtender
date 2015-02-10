package lihad.SOTMExtender.Objects;

import java.io.File;

import lihad.SOTMExtender.Extender;

public class Character extends TableEntity{

	private static final long serialVersionUID = 2063865130359225969L;
	
	private int health;
	
	Character(String name, int difficulty, int health, File file) {
		super(name, difficulty, file);
		this.health = health;
	}
	
	public int getWins(){
		int count = 0;
		for(Game game : Extender.getGames()){
			if(this instanceof Villain && game.isCompleted() && !game.getCompletedGameData().isVictorious())  count++;
			else if(this instanceof Hero && game.isCompleted() && game.getCompletedGameData().isVictorious())  count++;
		}
		return count;
	}
	
	public int getDeaths(){
		int count = 0;
		for(Game game : Extender.getGames()){
			if(this instanceof Villain && game.isCompleted() && game.getCompletedGameData().isVictorious())  count++;
			else if(this instanceof Hero && game.isCompleted() && game.getCompletedGameData().getHitpointsAtEnd(this) == 0)  count++;
		}
		return count;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
}
