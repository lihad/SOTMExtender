package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;

public class NGPRerollHero extends NGPChoose{

	private static final long serialVersionUID = 8544552077541106110L;
	
	private NewGamePane ngp;
	private Player player;

	public NGPRerollHero(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.REROLL_HERO, "["+player.getName()+"] is re-rolling their hero!  press 'Next' to continue.");

		this.ngp = ngp; this.player = player;

		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rerollHero();
				ngp.step3();
			}

		});
	}

	public void rerollHero(){
		Hero h = Extender.getHeroes().toArray(new Hero[0])[new Random().nextInt(Extender.getHeroes().size())];
		while(ngp.hasHero(h)) h = Extender.getHeroes().toArray(new Hero[0])[new Random().nextInt(Extender.getHeroes().size())];
		ngp.setHero(player, h);
	}
}
