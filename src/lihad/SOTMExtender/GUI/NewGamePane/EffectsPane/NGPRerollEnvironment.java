package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;

public class NGPRerollEnvironment extends NGPChoose{

	private static final long serialVersionUID = -3607414075295412881L;
	
	private NewGamePane ngp;

	public NGPRerollEnvironment(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.REROLL_VILLAIN, "["+player.getName()+"] is re-rolling the environment!  press 'Next' to continue.");

		this.ngp = ngp;

		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rerollEnvironment();
				ngp.step3();
			}

		});
	}

	public void rerollEnvironment(){
		Environment e = Extender.getEnvironments().toArray(new Environment[0])[new Random().nextInt(Extender.getEnvironments().size())];
		ngp.setEnvironment(e);
	}
}
