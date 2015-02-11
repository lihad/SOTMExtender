package lihad.SOTMExtender.Objects;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import lihad.SOTMExtender.Extender;

public class Game implements Serializable{
	
	private static final long serialVersionUID = -5513069088992500237L;
	
	private Map<Player, Hero> player_hero_map; // [Hero, Integer]
	private Villain villain;
	private Environment environment;
	private boolean iscompleted;
	private boolean isadvanced;
	private long timestamp;
	private DifficultyType d_type;
	private CompletedGameData cgdata;
	
	public Game(Set<Player> players, DifficultyType d_type){
		this.d_type = d_type;
		this.player_hero_map = new HashMap<Player, Hero>();
		
		int built_dif = 0;
		
		for(Player p : players){
			Hero h = Extender.getHeroes().toArray(new Hero[0])[new Random().nextInt(Extender.getHeroes().size())];
			while(this.player_hero_map.size() > 0 && this.player_hero_map.values().contains(h)) h = Extender.getHeroes().toArray(new Hero[0])[new Random().nextInt(Extender.getHeroes().size())];
			built_dif += h.getDifficulty();
			this.player_hero_map.put(p, h);
		}
		
		Map<Villain, Set<Environment>> matchups =  new HashMap<Villain, Set<Environment>>();
		for(Villain v : Extender.getVillains()){
			Set<Environment> environments = new HashSet<Environment>();
			for(Environment e : Extender.getEnvironments()){
				if(d_type.getBottomRange() < v.getDifficulty() + e.getDifficulty() + built_dif && v.getDifficulty() + e.getDifficulty() + built_dif < d_type.getTopRange()){
					environments.add(e);
				}
			}
			if(environments.size() > 0) matchups.put(v, environments);
		}
		
		this.villain = matchups.keySet().toArray(new Villain[0])[new Random().nextInt(matchups.size())];
		this.environment = matchups.get(this.villain).toArray(new Environment[0])[new Random().nextInt(matchups.get(this.villain).size())];		
		this.timestamp = System.currentTimeMillis(); iscompleted = false;
		
		Extender.getLogger().info(Game.class, "game "+timestamp+" has been created.  total games: "+(Extender.getGames().size()+1));
		Extender.getLogger().info(Game.class, "-- Heroes:");
		for(Entry<Player, Hero> entry : player_hero_map.entrySet()) Extender.getLogger().info(Game.class, "---- ("+entry.getKey().getName()+" | "+entry.getValue().getName()+")");
		Extender.getLogger().info(Game.class, "-- Villain: "+villain.getName()+" | Advanced: "+isadvanced);
		Extender.getLogger().info(Game.class, "-- Environment: "+environment.getName());
		Extender.getLogger().info(Game.class, "-- Difficulty: "+this.getDifficultyValue()+ " | "+this.d_type.toString());
		Extender.getLogger().info(Game.class, "-- Projected Experience Per: "+this.getExperience());
	}
	
	public Game(Map<Player, Hero> map, Villain villain, Environment environment, boolean isadvanced){
		
		this.player_hero_map = map;
		this.villain = villain;
		this.environment = environment;		
		this.timestamp = System.currentTimeMillis(); iscompleted = false;
		this.d_type = getDifficultyType();
		this.isadvanced = isadvanced;
		
		Extender.getLogger().info(Game.class, "game "+timestamp+" has been created.  total games: "+(Extender.getGames().size()+1));
		Extender.getLogger().info(Game.class, "-- Heroes:");
		for(Entry<Player, Hero> entry : player_hero_map.entrySet()) Extender.getLogger().info(Game.class, "---- ("+entry.getKey().getName()+" | "+entry.getValue().getName()+")");
		Extender.getLogger().info(Game.class, "-- Villain: "+villain.getName()+" | Advanced: "+isadvanced);
		Extender.getLogger().info(Game.class, "-- Environment: "+environment.getName());	
		Extender.getLogger().info(Game.class, "-- Difficulty: "+this.getDifficultyValue()+ " | "+this.d_type.toString());
		Extender.getLogger().info(Game.class, "-- Projected Experience Per: "+this.getExperience());
	}
	
	public Set<Player> getPlayers(){
		return this.player_hero_map.keySet();
	}
	
	public Collection<Hero> getHeroes(){	
		return player_hero_map.values();
	}
	
	public Player getPlayer(Hero hero){
		for(Entry<Player, Hero> entry : this.player_hero_map.entrySet()) if(entry.getValue().equals(hero)) return entry.getKey();
		return null;
	}
	
	public Hero getHero(Player player){
		return this.player_hero_map.get(player);
	}
	
	public Villain getVillain(){
		return this.villain;
	}
	
	public Environment getEnvironment(){
		return this.environment;
	}
	
	public long getTimestamp(){
		return this.timestamp;
	}
	
	public boolean isCompleted(){
		return this.iscompleted;
	}
	
	public boolean isAdvanced(){
		return this.isadvanced;
	}
	
	public int getDifficultyValue(){
		int diff = 0;
		for(Hero h : player_hero_map.values()) diff += h.getDifficulty();
		diff += villain.getDifficulty() + environment.getDifficulty();
		return diff;
	}
	
	public DifficultyType getDifficultyType(){
		if(getDifficultyValue() < -200) return DifficultyType.EXTREMELY_EASY;
		else if(getDifficultyValue() < -125) return DifficultyType.VERY_EASY;
		else if(getDifficultyValue() < -65) return DifficultyType.EASY;
		else if(getDifficultyValue() < -0) return DifficultyType.TYPICAL;
		else if(getDifficultyValue() < 65) return DifficultyType.CHALLENGING;
		else if(getDifficultyValue() < 100) return DifficultyType.DIFFICULT;
		else if(getDifficultyValue() < 200) return DifficultyType.VERY_HARD;
		else if(getDifficultyValue() < 275) return DifficultyType.EXTREMELY_HARD;
		else return DifficultyType.IMPOSSIBLE;
	}
	
	public int getExperience(){
		return (int) (Extender.getBaseExperience() * this.d_type.getExperienceModifier());
	}
	
	public CompletedGameData getCompletedGameData(){
		return this.cgdata;
	}

	public void complete(boolean won, Map<TableEntity,Boolean> tableentity_stats, Award award, Hero recipient){
		cgdata = new CompletedGameData(this, won, tableentity_stats, award, recipient);
		this.iscompleted = true;
	}
	
}