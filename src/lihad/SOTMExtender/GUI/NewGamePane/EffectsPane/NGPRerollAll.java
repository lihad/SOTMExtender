package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;

public class NGPRerollAll extends NGPChoose{

	private static final long serialVersionUID = -7069198067088899957L;
	
	private NewGamePane ngp;
	
	public NGPRerollAll(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.REROLL_ALL, "complete re-roll!  press 'Next' to continue.");
		
		this.ngp = ngp;
		
		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reroll();
				ngp.step3();
			}
			
		});
	}

	public void reroll(){
		ngp.resetAll();
		ngp.assignSlots();
		Extender.getLogger().info(NGPChoose.class, "everything is being rerolled!");
	}
}
