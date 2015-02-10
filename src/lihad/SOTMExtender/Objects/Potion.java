package lihad.SOTMExtender.Objects;

public enum Potion {
	
	CHOOSE_VILLAIN("Potion of Villain Choice", 75, PotionRound.ONE, true),
	CHOOSE_ENVIRONMENT("Potion of Environment Choice", 50, PotionRound.ONE, true),
	CHOOSE_HERO("Potion of Hero Choice", 100, PotionRound.ONE, false),
	MAKE_ADVANCED("Potion of Enragement", 50, PotionRound.ONE, true),
	MAKE_NORMAL("Potion of Calming", 25, PotionRound.TWO, true),
	RANDOM_LOW_LEVEL("Potion of Noobery", 25, PotionRound.ONE, false),
	REROLL_ALL("Potion of Rolling Reset", 100, PotionRound.TWO, true),
	REROLL_HERO("Potion of Lost Heroism", 75, PotionRound.TWO, false),
	REROLL_ENVIRONMENT("Potion of Lost Maps", 25, PotionRound.TWO, true),
	REROLL_VILLAIN("Potion of Lost Evil", 50, PotionRound.TWO, true),
	NONE("- Nothing -", 0, PotionRound.ALL, true);
	
	public enum PotionRound{ALL,ONE,TWO}
	
	private String name;
	private int cost;
	private PotionRound round;
	private boolean group;
	
	Potion(String name, int cost, PotionRound round, boolean group){
		this.name = name; this.cost = cost; this.round = round; this.group = group;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getCost(){
		return this.cost;
	}
	
	public PotionRound getPotionRound(){
		return this.round;
	}
	
	public boolean isGroupable(){
		return this.group;
	}
	
}
