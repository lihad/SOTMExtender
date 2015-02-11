package lihad.SOTMExtender.GUI.NewGamePane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPEnvironmentChoose;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPHeroChoose;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPMakeAdvanced;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPMakeNormal;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPRandomLowLevel;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPRerollAll;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPRerollEnvironment;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPRerollHero;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPRerollVillain;
import lihad.SOTMExtender.GUI.NewGamePane.EffectsPane.NGPVillainChoose;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;
import lihad.SOTMExtender.Objects.Potion.PotionRound;
import lihad.SOTMExtender.Objects.Villain;

public class NewGamePane extends JPanel{

	private static final long serialVersionUID = 1004803986986016004L;

	private NewGamePaneInitialPlayerInformation ngpipi;
	private NewGamePaneInitialGroupInformation ngpigi;
	private JPanel center;
	JButton accept;

	private Entry<Player, Potion> current_entry;
	private Map<Player, Potion> potion_queue = new HashMap<Player, Potion>();

	protected Map<Player, Hero> player_hero_map = new HashMap<Player, Hero>();
	protected Villain villain;
	protected Environment environment;
	public boolean isAdvancedGame = false;

	public NewGamePane(){
		super(new BorderLayout());
		
		JPanel northen = new JPanel();
		northen.setLayout(new BoxLayout(northen, BoxLayout.Y_AXIS));

		ngpipi = new NewGamePaneInitialPlayerInformation(this);
		northen.add(ngpipi);

		ngpigi = new NewGamePaneInitialGroupInformation(this);
		northen.add(ngpigi);

		accept = new JButton("accept");
		accept.setActionCommand("one");
		accept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(accept.getActionCommand().equals("one")){
					Extender.getLogger().info(NewGamePane.class, "["+ngpipi.getSelectedPlayers().size()+"] player game initialized.");

					ngpipi.lockPlayerCombos();
					ngpipi.lockPotionCombos();

					for(NewGamePaneInitialPlayerLine line : NewGamePane.this.ngpipi.getPlayerLines()){
						if(line.getPotion() != Potion.NONE){
							Extender.getLogger().info(NewGamePane.class, "adding to potion queue: "+line.getPlayer().getName()+" "+line.getPotion().getName());
							potion_queue.put(line.getPlayer(), line.getPotion());
						}
					}

					step1();
				}else if(accept.getActionCommand().equals("two")){
					Extender.getLogger().info(NewGamePane.class, "continuing with second-round potions.");

					ngpipi.lockPotionCombos();

					for(NewGamePaneInitialPlayerLine line : NewGamePane.this.ngpipi.getPlayerLines()){
						if(line.getPotion() != Potion.NONE){
							Extender.getLogger().info(NewGamePane.class, "adding to potion queue: "+line.getPlayer().getName()+" "+line.getPotion().getName());
							potion_queue.put(line.getPlayer(), line.getPotion());
						}
					}

					step3();
				}
			}
		});
		this.add(northen, BorderLayout.NORTH);
		this.add(accept, BorderLayout.SOUTH);
	}

	protected Set<Player> getSelectedPlayers(){
		return ngpipi.getSelectedPlayers();
	}

	protected void updateGoldLabels(){
		this.ngpigi.updateGoldLabel();
		this.ngpipi.updatePlayerGoldLabels();
	}

	private void loadChoosePane(Player player, Potion potion){
		switch(potion){
		// ONE
		case CHOOSE_VILLAIN: loadChoosePane(new NGPVillainChoose(this, player)); break;
		case CHOOSE_ENVIRONMENT: loadChoosePane(new NGPEnvironmentChoose(this, player)); break;
		case CHOOSE_HERO: loadChoosePane(new NGPHeroChoose(this, player)); break;
		case MAKE_ADVANCED: loadChoosePane(new NGPMakeAdvanced(this, player)); break;
		case RANDOM_LOW_LEVEL: loadChoosePane(new NGPRandomLowLevel(this, player)); break;

		// TWO
		case REROLL_ALL:  loadChoosePane(new NGPRerollAll(this, player)); break;
		case REROLL_HERO:  loadChoosePane(new NGPRerollHero(this, player)); break;
		case REROLL_VILLAIN:  loadChoosePane(new NGPRerollVillain(this, player)); break;
		case REROLL_ENVIRONMENT:  loadChoosePane(new NGPRerollEnvironment(this, player)); break;
		case MAKE_NORMAL:  loadChoosePane(new NGPMakeNormal(this, player)); break;

		// ALL
		default: break;
		}
	}

	private void loadChoosePane(JPanel pane){
		if(center != null)this.remove(center);
		if(accept != null)this.remove(accept);
		JPanel p = new JPanel(new BorderLayout());
		p.add(pane, BorderLayout.NORTH);
		this.center = p;
		this.add(this.center, BorderLayout.CENTER);
		Extender.getGUI().pack();
		Extender.getGUI().repaint();
	}

	private Entry<Player, Potion> popPotionQueue(){
		try{
			Entry<Player, Potion> entry = potion_queue.entrySet().iterator().next(); 
			potion_queue.remove(entry.getKey());
			return entry;
		} catch (NoSuchElementException e){
			return null;
		}
	}

	public void setHero(Player player, Hero hero){
		this.player_hero_map.put(player, hero);
	}

	public void randomLL(Player player){
		List<Hero> lowest = new LinkedList<Hero>();
		int level = 999;

		for(Hero hero : Extender.getHeroes()){
			if(player.getLevel(hero) < level){
				lowest.clear();
				level = player.getLevel(hero);
				lowest.add(hero);
			}else if(player.getLevel(hero)== level){
				lowest.add(hero);
			}
		}

		Collections.shuffle(lowest);
		setHero(player, lowest.get(0));
	}

	public void setVillain(Villain villain){
		this.villain = villain;
	}

	public void setEnvironment(Environment environment){
		this.environment = environment;
	}

	public void setAdvanced(boolean is){
		this.isAdvancedGame = is;
	}

	public boolean hasHero(Hero hero){
		if(this.player_hero_map.containsKey(hero)) return true;
		return false;
	}

	public void resetAll(){
		this.player_hero_map.clear();
		this.villain = null;
		this.environment = null;
	}

	public void assignSlots(){
		for(Player player : ngpipi.getSelectedPlayers()){
			if(!player_hero_map.containsKey(player)){
				Hero h = Extender.getHeroes().toArray(new Hero[0])[new Random().nextInt(Extender.getHeroes().size())];
				while(this.player_hero_map.size() > 0 && this.player_hero_map.values().contains(h)) h = Extender.getHeroes().toArray(new Hero[0])[new Random().nextInt(Extender.getHeroes().size())];
				this.player_hero_map.put(player, h);
			}
		}

		while(this.villain == null)this.villain = Extender.getVillains().toArray(new Villain[0])[new Random().nextInt(Extender.getVillains().size())];
		if(this.environment == null)this.environment = Extender.getEnvironments().toArray(new Environment[0])[new Random().nextInt(Extender.getEnvironments().size())];	
	}

	public void step1(){
		current_entry = popPotionQueue();
		if(current_entry == null){
			step2(); return;
		}

		loadChoosePane(current_entry.getKey(), current_entry.getValue());	
		
		current_entry.getKey().removeGold(current_entry.getValue().getCost());
		Extender.getLogger().info(NewGamePane.class, "player ["+current_entry.getKey().getName()+"] is resolving ["+current_entry.getValue().getName()+"]");
		Extender.getLogger().info(NewGamePane.class, "["+current_entry.getValue().getCost()+"G] was deducted.  leaving ["+current_entry.getKey().getName()+"] with ["+current_entry.getKey().getGold()+"G]");
	}

	public void step2(){
		for(NewGamePaneInitialPlayerLine line : ngpipi.getPlayerLines()){
			line.updateComboBox(PotionRound.TWO);
		}
		ngpipi.unlockPotionCombos();
		if(center != null)this.remove(center);
		if(accept != null)this.remove(accept);

		assignSlots();		

		this.center = new NewGamePanePreliminary(this.player_hero_map, this.villain, this.environment, this.isAdvancedGame);
		this.add(this.center, BorderLayout.CENTER);
		accept.setActionCommand("two");
		this.add(accept, BorderLayout.SOUTH);

	}

	public void step3(){
		current_entry = popPotionQueue();
		if(current_entry == null){
			step4(); return;
		}

		loadChoosePane(current_entry.getKey(), current_entry.getValue());	

		Extender.getLogger().info(NewGamePane.class, "player ["+current_entry.getKey().getName()+"] is resolving ["+current_entry.getValue().getName()+"]");
		Extender.getLogger().info(NewGamePane.class, "["+current_entry.getValue().getCost()+"G] was deducted.  leaving ["+current_entry.getKey().getName()+"] with ["+current_entry.getKey().getGold()+"G]");

	}
	
	private void step4(){
		Extender.getLogger().info(NewGamePane.class, "creating game!!!");

		Game game = new Game(this.player_hero_map, this.villain, this.environment, this.isAdvancedGame);
		Extender.addGame(game);
		Extender.saveGameData(game);
		Extender.getGUI().loadCloseGamePane(game);
	}


}
