package lihad.SOTMExtender.Objects;

public enum Award {
	
	BIGGEST_CHEATER("Biggest Cheater"),
	MOST_CARDS_PLAYED("Most Cards Played"),
	BEST_SUPPORT("Best Support"),
	MOST_DAMAGE("Most Damage"),
	FIRST_WORLD_PROBLEM("First World Problem"),
	BIGGEST_BITCH("Biggest Bitch"),
	MVP("Most Valuable Player"),
	LVP("Least Valuable Player"),
	BIGGEST_DOUCHER("Biggest Doucher"),
	BIGGEST_SPONGE("Biggest Sponge"),
	MOST_HEALTH("Most Health"),
	LEAST_HEALTH("Least Health");

	
	private String name;
	
	Award(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

}
