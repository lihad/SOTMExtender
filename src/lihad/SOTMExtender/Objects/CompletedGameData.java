package lihad.SOTMExtender.Objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import lihad.SOTMExtender.Extender;

public class CompletedGameData implements Serializable{
	
	private static final long serialVersionUID = -1785318266248999984L;
	
	private Map<TableEntity, Boolean> living_map;
	private Map<Hero, Integer> experience_map;
	private Map<Hero, Integer> gold_map;
	private boolean players_won;
	private Game game;
	private Award award;
	private Hero receipt;
	
	public CompletedGameData(Game game, boolean players_won, Map<TableEntity, Boolean> living_map, Award award, Hero receipt) {
		this.game = game;
		this.players_won = players_won; this.living_map = living_map; this.award = award; this.receipt = receipt;
		
		experience_map = new HashMap<Hero, Integer>();
		gold_map = new HashMap<Hero, Integer>();
		
		adjustDifficulty();
		awardExperienceAndGold();
	}
	
	public boolean isVictorious(){
		return this.players_won;
	}
	
	public Award getAward(){
		return this.award;
	}
	
	public Hero getAwardRecipient(){
		return this.receipt;
	}
	
	public boolean isLiving(TableEntity entity){
		if(!this.living_map.containsKey(entity)) return false;
		return this.living_map.get(entity);
	}
	
	private void awardExperienceAndGold(){
		Extender.getLogger().info(Game.class, "awarding experience and gold (base before modifiers = "+this.game.getExperience()+")");
		for(Hero h : this.game.getHeroes()){
			int experience = this.game.getExperience();
			if(this.getAwardRecipient().equals(h)) experience += (50 + new Random().nextInt(50));
			experience += new Random().nextInt(20);
			if(!this.isVictorious()) experience = (int) (experience*.15);
			
			if(!this.isLiving(h)){
				this.game.getPlayer(h).resetExperience(h);
				Extender.getLogger().info(Game.class, "__ "+h.getName()+" died! Experience was reset for ["+this.game.getPlayer(h).getName()+"] !");
				experience_map.put(h, 0);
			}else{
				this.game.getPlayer(h).addExperience(h, experience);
				Extender.getLogger().info(Game.class, "__ "+h.getName()+" is awarded ["+experience+"] exp!  Current level "+this.game.getPlayer(h).getLevel(h));
				experience_map.put(h, experience);
			}
			
			this.game.getPlayer(h).addGold((int)(experience*.10));
			Extender.getLogger().info(Game.class, "__ "+h.getName()+" is awarded ["+(experience*.10)+"] gold!");
			gold_map.put(h, (int) (experience*.10));
			
			Extender.savePlayerData(this.game.getPlayer(h));
		}
	}
	
	private void adjustDifficulty(){
		//TODO: make this more complex
		Extender.getLogger().info(Game.class, "adjusting difficulty");
		int i = (this.isVictorious() ? -1 : 1);
		
		for(Player p : this.game.getPlayers()){
			this.game.getHero(p).setDifficulty(i+this.game.getHero(p).getDifficulty());
			Extender.getLogger().info(Game.class, " - hero "+this.game.getHero(p).getName()+"'s difficulty is now ["+this.game.getHero(p).getDifficulty()+"] ["+i+"]");
		}
		
		i = -i;
		
		this.game.getVillain().setDifficulty(i+this.game.getVillain().getDifficulty());
		Extender.getLogger().info(Game.class, " - villain "+this.game.getVillain().getName()+"'s difficulty is now ["+this.game.getVillain().getDifficulty()+"] ["+i+"]");

		
	}
}
