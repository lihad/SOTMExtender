package lihad.SOTMExtender.Objects;

import java.io.File;

public class Villain extends Character{
	
	private static final long serialVersionUID = -2416479829344299627L;
	
	private int advanced_difficulty;
	
	public Villain(String name, int difficulty, int health, File file, int advanced_difficulty) {
		super(name, difficulty, health, file);
		this.advanced_difficulty = advanced_difficulty;
	}
	
	public int getAdvancedDifficulty(){
		return this.advanced_difficulty;
	}
	
	public void setAdvancedDifficulty(int adv){
		this.advanced_difficulty = adv;
	}
}
