package lihad.SOTMExtender.Objects;

import java.io.File;

public class Hero extends Character{

	private static final long serialVersionUID = -9118303498953143364L;

	public Hero(String name, int difficulty, int health, File file) {
		super(name, difficulty, health, file);
	}

}
