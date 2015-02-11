package lihad.SOTMExtender.Objects;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity{

	private static final long serialVersionUID = 3939681750099282976L;
	
	private Map<Hero, Integer> experience = new HashMap<Hero, Integer>();
	private int gold;
	private String fullname;
	
	public Player(String name, String fullname) {
		super(name);
		this.fullname = fullname;
	}
	
	public int getLevel(Hero hero){
		int level = 1, incr = 500, exp = experience.get(hero) != null ? experience.get(hero) : 0;
		
		while(incr < exp){
			level++;
			exp = exp-incr;
			incr=(int) (incr+(incr*.1));
		}
		return level+(exp/incr);	
	}
	
	public void addExperience(Hero hero, int experience){
		this.experience.put(hero, (this.experience.get(hero) != null ? this.experience.get(hero) : 0) + experience);
	}
	
	public void resetExperience(Hero hero){
		this.experience.put(hero, 0);
	}
	
	public void addGold(int gold){
		this.gold += gold;
	}
	
	public void removeGold(int gold){
		this.gold -= gold;
	}
	
	public int getGold(){
		return this.gold;
	}
	
	public int getExperience(Hero hero){
		return this.experience.get(hero);
	}
	
	public boolean hasExperience(Hero hero){
		return this.experience.containsKey(hero);
	}
	
	public String getFullname(){
		return this.fullname;
	}

}
