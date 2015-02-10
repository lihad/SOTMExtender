package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;
import lihad.SOTMExtender.Objects.Villain;

public class NGPRerollVillain extends NGPChoose{

	private static final long serialVersionUID = -2634038161916925785L;
	
	private NewGamePane ngp;

	public NGPRerollVillain(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.REROLL_VILLAIN, "["+player.getName()+"] is re-rolling the villain!  press 'Next' to continue.");

		this.ngp = ngp;

		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rerollVillain();
				ngp.step3();
			}

		});
	}

	public void rerollVillain(){
		Villain v = Extender.getVillains().toArray(new Villain[0])[new Random().nextInt(Extender.getVillains().size())];
		ngp.setVillain(v);
	}
}
