package lihad.SOTMExtender.Objects;

import java.io.File;

public class Environment extends TableEntity{

	private static final long serialVersionUID = -8256336362262050897L;

	public Environment(String name, int difficulty, File file) {
		super(name, difficulty, file);
	}

}
