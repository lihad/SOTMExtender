package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;

public class NGPMakeNormal extends NGPChoose{
	
	private static final long serialVersionUID = 8216193080546514709L;
	
	private NewGamePane ngp;
	
	public NGPMakeNormal(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.MAKE_NORMAL, "the game will be Normal!  press 'Next' to continue.");
		
		this.ngp = ngp;
		
		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setAdvanced(false);
				ngp.step3();
			}
			
		});
	}

	public void setAdvanced(boolean is){
		ngp.setAdvanced(is);
		Extender.getLogger().info(NGPChoose.class, "the Villain will now be 'Normal'!");
	}
}
