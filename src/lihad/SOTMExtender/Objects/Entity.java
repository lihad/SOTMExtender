package lihad.SOTMExtender.Objects;

import java.io.Serializable;

public class Entity implements Serializable{
	
	private static final long serialVersionUID = -3118338494826077584L;
	
	private String name;
	
	Entity(String name){this.name = name;}
	
	public String getName(){
		return this.name;
	}

}
