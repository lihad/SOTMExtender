package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;
import lihad.SOTMExtender.Objects.Villain;

public class NGPHeroChoose extends NGPChoose{

	private static final long serialVersionUID = -7069198067088899957L;
	
	private NewGamePane ngp;
	private Player player;
	
	public NGPHeroChoose(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.CHOOSE_HERO, "please choose a Hero");
		
		this.ngp = ngp; this.player = player;
		
		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setHero();
				ngp.step1();
			}
			
		});
	}

	public void setHero(){
		ngp.setHero(player, (Hero)super.getCombo().getSelectedItem());
		Extender.getLogger().info(NGPChoose.class, "the Hero ["+((Hero)super.getCombo().getSelectedItem()).getName()+"] was set");
	}
}
