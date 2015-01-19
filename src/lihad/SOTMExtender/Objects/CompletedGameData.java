package lihad.SOTMExtender.Objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CompletedGameData implements Serializable{
	
	private static final long serialVersionUID = -1785318266248999984L;

	public enum CGDataType{
		LIVING, HP_END, DMG_DEALT, DMG_MITIGATED, HP_RESTORED, KILLS
	}
	
	private Map<TableEntity, Map<CGDataType, Object>> entity_data;
	private int rounds;
	private boolean players_won;
	private Game game;
	
	public CompletedGameData(Game game, boolean players_won, int rounds, Map<TableEntity, Object[]> tableentity_stats) {
		this.game = game;
		this.players_won = players_won; this.rounds = rounds;
		// [] =	LIVING, HP_END, DMG_DEALT, DMG_MITIGATED, HP_RESTORED, KILLS
		//        0       1        2             3             4         5   
		
		entity_data = new HashMap<TableEntity, Map<CGDataType, Object>>();
		for(Entry<TableEntity, Object[]> entry : tableentity_stats.entrySet()){
			Map<CGDataType, Object> cg_map = new HashMap<CGDataType, Object>();
			System.out.println("deep "+entry.getValue()[0]+" "+(Boolean)entry.getValue()[0]);
			System.out.println("deep "+entry.getValue()[1]+" "+(Integer)entry.getValue()[1]);

			cg_map.put(CGDataType.LIVING, (Boolean)entry.getValue()[0]);
			if(!(boolean)entry.getValue()[0]) cg_map.put(CGDataType.HP_END, 0);
			else cg_map.put(CGDataType.HP_END, (Integer)entry.getValue()[1]);
			if(entry.getValue()[2] !=null) cg_map.put(CGDataType.DMG_DEALT, (int)entry.getValue()[2]);
			if(entry.getValue()[3] !=null) cg_map.put(CGDataType.DMG_MITIGATED, (int)entry.getValue()[3]);
			if(entry.getValue()[4] !=null) cg_map.put(CGDataType.HP_RESTORED, (int)entry.getValue()[4]);
			if(entry.getValue()[5] !=null) cg_map.put(CGDataType.KILLS, (int)entry.getValue()[5]);
			
			entity_data.put(entry.getKey(), cg_map);
		}		
	}

	/**
	 * REQUIRED:
	 * rounds, players_won
	 * {LIVING, HP_END} CGDataType's for entity_data
	 * 
	 * OPTIONAL:
	 * {DMG_DEALT, DMG_MITIGATED, HP_RESTORED, KILLS, MVP} CGDataType's for entity_data
	 * 
	 * 
	 */
	
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
	
	public int getDamageDealt(TableEntity entity){
		if(!hasDataType(entity, CGDataType.DMG_DEALT)) return -1;
		return (int)this.entity_data.get(entity).get(CGDataType.DMG_DEALT);
	}
	
	public int getDamageMitigated(TableEntity entity){
		if(!hasDataType(entity, CGDataType.DMG_MITIGATED)) return -1;
		return (int)this.entity_data.get(entity).get(CGDataType.DMG_MITIGATED);
	}
	
	public int getHitpointsRestored(TableEntity entity){
		if(!hasDataType(entity, CGDataType.HP_RESTORED)) return -1;
		return (int) this.entity_data.get(entity).get(CGDataType.HP_RESTORED);
	}
	
	public int getHitpointsAtEnd(TableEntity entity){
		if(!hasDataType(entity, CGDataType.HP_END)) return -1;
		return (int) this.entity_data.get(entity).get(CGDataType.HP_END);
	}
	
	public int getKills(TableEntity entity){
		if(!hasDataType(entity, CGDataType.KILLS)) return -1;
		return (int) this.entity_data.get(entity).get(CGDataType.KILLS);
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
		return (this.getKills(h) + this.getHitpointsRestored(h) + this.getDamageMitigated(h) + this.getDamageDealt(h) + (100 * (this.getHitpointsAtEnd(h)/h.getHealth())));
	}
	
	public boolean isLiving(TableEntity entity){
		if(!hasDataType(entity, CGDataType.LIVING)) return false;
		return (boolean) this.entity_data.get(entity).get(CGDataType.LIVING);
	}
}
