package lihad.SOTMExtender.Objects;

import java.io.File;

public class Villian extends Character{
	
	private static final long serialVersionUID = -2416479829344299627L;
	
	private boolean advanced;
	
	public Villian(String name, int difficulty, int health, File file, boolean advanced) {
		super(name, difficulty, health, file);
		this.advanced = advanced;
	}
	
	public boolean isAdvanced(){
		return this.advanced;
	}
}