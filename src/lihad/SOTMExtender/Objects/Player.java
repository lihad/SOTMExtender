package lihad.SOTMExtender.Objects;

public class Player extends Entity{

	private static final long serialVersionUID = 3939681750099282976L;
	
	private int experience;
	private int gold;
	private String fullname;
	
	public Player(String name, String fullname) {
		super(name);
		this.fullname = fullname;
	}
	
	public double getLevel(){
		int level = 1, incr = 500, exp = experience;
		
		while(incr < exp){
			level++;
			exp = exp-incr;
			incr=(int) (incr+(incr*.1));
		}
		return level+(exp/incr);	
	}
	
	public void addExperience(int experience){
		this.experience += experience;
	}
	
	public void addGold(int gold){
		this.gold += gold;
	}
	
	public int getGold(){
		return this.gold;
	}
	
	public int getExperience(){
		return this.experience;
	}
	
	public String getFullname(){
		return this.fullname;
	}

}
