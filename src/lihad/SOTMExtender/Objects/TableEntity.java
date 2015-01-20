package lihad.SOTMExtender.Objects;

import java.io.File;

import lihad.SOTMExtender.Extender;

public class TableEntity extends Entity{

	private static final long serialVersionUID = 3257913771108822483L;

	private int difficulty;
	private File image_file;

	TableEntity(String name, int difficulty, File file) {
		super(name);
		this.difficulty = difficulty;
		this.image_file = file;
	}

	public int getDifficulty(){
		return this.difficulty;
	} 

	public File getImageFile(){
		return this.image_file;
	}
	
	public int getTimesPlayed(){
		int count = 0;
		for(Game game : Extender.getGames()){
			if(this instanceof Villian && game.getVillian().equals(this))  count++;
			else if(this instanceof Hero && game.getHeroes().contains(this))  count++;
			else if(this instanceof Environment && game.getEnvironment().equals(this))  count++;
		}
		return count;
	}
	
	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
	
	public void setImageFile(File image_file){
		this.image_file = image_file;
	}

}
