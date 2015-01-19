package lihad.SOTMExtender.Objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;

import lihad.SOTMExtender.Extender;

public class CompletedGameData implements Serializable{
	
	private static final long serialVersionUID = -1785318266248999984L;

	public enum CGDataType{
		LIVING, HP_END
	}
	
	private Map<TableEntity, Map<CGDataType, Object>> entity_data;
	private int rounds;
	private boolean players_won;
	private Game game;
	
	public CompletedGameData(Game game, boolean players_won, int rounds, Map<TableEntity, Object[]> tableentity_stats) {
		this.game = game;
		this.players_won = players_won; this.rounds = rounds;
		// [] =	LIVING, HP_END
		//        0       1    
		
		entity_data = new HashMap<TableEntity, Map<CGDataType, Object>>();
		for(Entry<TableEntity, Object[]> entry : tableentity_stats.entrySet()){
			Map<CGDataType, Object> cg_map = new HashMap<CGDataType, Object>();
			System.out.println("deep "+entry.getValue()[0]+" "+(Boolean)entry.getValue()[0]);
			System.out.println("deep "+entry.getValue()[1]+" "+(Integer)entry.getValue()[1]);

			cg_map.put(CGDataType.LIVING, (Boolean)entry.getValue()[0]);
			if(!(boolean)entry.getValue()[0]) cg_map.put(CGDataType.HP_END, 0);
			else cg_map.put(CGDataType.HP_END, (Integer)entry.getValue()[1]);
			
			entity_data.put(entry.getKey(), cg_map);
			
			
		}		
		awardExperience();
	}
	
	public int getRounds(){
		return this.rounds;
	}
	
	public boolean isVictorious(){
		return this.players_won;
	}
	
	public boolean hasDataType(TableEntity entity, CGDataType type){
		if(this.entity_data.get(entity).containsKey(type)) return true;
		else return false;
	}

	public int getHitpointsAtEnd(TableEntity entity){
		if(!hasDataType(entity, CGDataType.HP_END)) return -1;
		return (int) this.entity_data.get(entity).get(CGDataType.HP_END);
	}
	
	public Set<Hero> getMVPs(){
		Set<Hero> heroes = new HashSet<Hero>();
		int score = 0;
		
		for(Hero h : this.game.getHeroes()){
			if(heroes.isEmpty()){
				heroes.add(h); score = getScore(h);
			}else if(score < getScore(h)){
				heroes.clear();
				heroes.add(h);
				score = getScore(h);
			}else if(score == getScore(h)){
				heroes.add(h);
			}
		}
		return heroes;
	}
	
	private int getScore(Hero h){
		return ((100 * this.getHitpointsAtEnd(h))/h.getHealth());
	}
	
	public boolean isLiving(TableEntity entity){
		if(!hasDataType(entity, CGDataType.LIVING)) return false;
		return (boolean) this.entity_data.get(entity).get(CGDataType.LIVING);
	}
	
	private void awardExperience(){
		Extender.getLogger().info(Game.class, "awarding experience (base before modifiers = "+this.game.getExperience()+")");
		
		for(Player p : this.game.getPlayers()){
			int experience = 0;
			experience = this.game.getExperience();
			if(this.getMVPs().contains(this.game.getHero(p)))experience += 50;
			experience += new Random().nextInt(20);
			if(!this.isLiving(this.game.getHero(p))) experience = (int) (experience*.50);
			if(!this.isVictorious()) experience = (int) (experience*.15);
			Extender.getLogger().info(Game.class, "__ "+p.getName()+" is awarded ["+experience+"] exp!");
			p.addExperience(experience);
			Extender.savePlayerData(p);
		}
	}
}
